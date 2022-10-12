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

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final KeywordRepository keywordRepository;

    Function<Post, PostResponse> converter = post ->    //todo: keyword 추가 필요
            PostResponse.builder()
                    .id(post.getId())
                    .content(post.getContent())
                    .writerId(post.getWriterId())
                    .viewCount(post.getViewCount())
                    .createdAt(post.getCreatedAt())
                    .modifiedAt(post.getModifiedAt())
                    .build();

    @Transactional(readOnly = true)
    public PagedResponse<PostResponse, Post> getPosts(PagedRequest pagedRequest) {
        var pageable = pagedRequest.getPageable(Sort.by("modifiedAt").descending());
        var response = postRepository.findAll(pageable);

        return new PagedResponse<>(response, converter);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(String id) {
        var post = postRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return converter.apply(post);
    }

    @Transactional
    public String createPost(String title, String content, String writerId, List<String> keywords) {
        var post = Post.builder()
                .id(IdGenerator.newId())
                .title(title)
                .content(content)
                .writerId(writerId)
                .keywordIds(getKeywordIds(keywords))
                .build();

        return postRepository.save(post).getId();   //todo: 명시적으로 저장을 꼭 해야 하던가?
    }

    @Transactional
    public String updatePost(String id, String title, String content, String writerId, List<String> keywords) {
        var post = postRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        var keywordIds = getKeywordIds(keywords);

        post.update(title, content, writerId, keywordIds);

        return post.getId();
    }

    @Transactional
    public void deletePost(String id) {
        var post = postRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        postRepository.delete(post);
    }

    private Set<String> getKeywordIds(List<String> words) {
        var keywordIds = new HashSet<String>();

        for (String word : words) {
            var keywordId = keywordRepository.findKeywordsByWord(word)
                    .orElseGet(() -> {
                                var newKeyword = Keywords.builder()
                                        .id(IdGenerator.newId())
                                        .word(word)
                                        .build();

                                keywordRepository.save(newKeyword);

                                return newKeyword;
                            }
                    )
                    .getId();

            keywordIds.add(keywordId);
        }

        return keywordIds;

    }

}
