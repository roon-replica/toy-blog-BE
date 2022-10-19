package toy.blog.be.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.blog.be.controller.PagedRequest;
import toy.blog.be.controller.PagedResponse;
import toy.blog.be.controller.post.dto.response.PostResponse;
import toy.blog.be.domain.entity.Keywords;
import toy.blog.be.domain.entity.Post;
import toy.blog.be.infra.IdGenerator;
import toy.blog.be.repository.KeywordRepository;
import toy.blog.be.repository.PostRepository;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final KeywordRepository keywordRepository;

    Function<Post, PostResponse> converter = post ->    //todo: keyword 추가 필요
            PostResponse.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .writerId(post.getWriterId())
                    .viewCount(post.getViewCount())
                    .createdAt(post.getCreatedAt())
                    .modifiedAt(post.getModifiedAt())
                    .keywords(getKeywords(post.getKeywordIds()))
                    .build();

    @Transactional(readOnly = true)
    public PagedResponse<PostResponse, Post> getPosts(PagedRequest pagedRequest) {
        var pageable = pagedRequest.getPageable(Sort.by("modifiedAt").descending());
        var response = postRepository.findAll(pageable);

        return new PagedResponse<>(response, converter);
    }

    @Transactional // readOnly 속성주면 post.increaseViewCount() 동작 안 함!!
    public PostResponse getPost(String id) {
        var post = postRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        post.increaseViewCount();

        return converter.apply(post);
    }

    @Transactional
    public String createPost(String title, String content, String writerId, List<String> keywordStrings) {
        var keywords = makeKeywords(keywordStrings);
        keywords.forEach(Keywords::increaseCount);
        keywordRepository.saveAll(keywords);

        var keywordIds = keywords.stream()
                .map(Keywords::getId)
                .collect(Collectors.toSet());

        var post = Post.builder()
                .id(IdGenerator.newId())
                .title(title)
                .content(content)
                .writerId(writerId)
                .keywordIds(keywordIds)
                .build();

        return postRepository.save(post).getId();   //todo: 명시적으로 저장을 꼭 해야 하던가?
    }

    @Transactional
    public String updatePost(String id, String title, String content, String writerId, List<String> keywords) {
        var post = postRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        var oldKeywords = getKeywords(post.getKeywordIds());
        oldKeywords.forEach(Keywords::decreaseCount);

        var newKeywords = makeKeywords(keywords);
        newKeywords.forEach(Keywords::increaseCount);
        keywordRepository.saveAll(newKeywords);

        var newKeywordIds = newKeywords.stream()
                .map(Keywords::getId)
                .collect(Collectors.toSet());

        post.update(title, content, writerId, newKeywordIds);

        return post.getId();
    }

    // todo: 삭제된 글의 comment도 모두 삭제해야 함.
    @Transactional
    public void deletePost(String id) {
        var post = postRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        var keywords = getKeywords(post.getKeywordIds());
        keywords.forEach(Keywords::decreaseCount);

        postRepository.delete(post);
    }

    private Set<Keywords> makeKeywords(List<String> words) {
        var keywords = new HashSet<Keywords>();

        for (String word : words) {
            var keyword = keywordRepository.findKeywordsByWord(word)
                    .orElseGet(() -> {
                                var newKeyword = Keywords.builder()
                                        .id(IdGenerator.newId())
                                        .word(word)
                                        .build();

                                keywordRepository.save(newKeyword);

                                return newKeyword;
                            }
                    );

            keywords.add(keyword);
        }

        return keywords;

    }

    private Set<Keywords> getKeywords(Set<String> keywordIds) {
        return keywordIds.stream()
                .map(id -> keywordRepository.findById(id).orElseThrow(EntityNotFoundException::new))
                .collect(Collectors.toSet());
    }

}
