package toy.blog.be.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import toy.blog.be.controller.PagedRequest;
import toy.blog.be.controller.PagedResponse;
import toy.blog.be.controller.post.dto.response.PostResponse;
import toy.blog.be.domain.Post;
import toy.blog.be.repository.PostRepository;

import javax.persistence.EntityNotFoundException;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;


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
    public String createPost(){
        return null;
    }

    @Transactional
    public String updatePost(){
        return null;
    }

    @Transactional
    public String deletePost(){
        return null;
    }
}
