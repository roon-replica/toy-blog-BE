package toy.blog.be.controller.post.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy.blog.be.controller.PagedRequest;
import toy.blog.be.controller.PagedResponse;
import toy.blog.be.controller.post.dto.request.PostCreateRequest;
import toy.blog.be.controller.post.dto.request.PostUpdateRequest;
import toy.blog.be.controller.post.dto.response.PostResponse;
import toy.blog.be.domain.Post;
import toy.blog.be.service.PostService;

import javax.validation.Valid;

@Tag(name = "post apis")
@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class PostApiController {
    private final PostService postService;

    @Operation(summary = "get paged posts")
    @GetMapping
    public ResponseEntity<PagedResponse<PostResponse, Post>> getPosts(@RequestBody @Valid PagedRequest pagedRequest) {
        return ResponseEntity.ok()
                .body(postService.getPosts(pagedRequest));
    }

    @Operation(summary = "get single post by id")
    @GetMapping("/post")
    public ResponseEntity<PostResponse> getPost(String postId) {
        return ResponseEntity.ok()
                .body(postService.getPost(postId));
    }

    @Operation(summary = "create post")
    @PostMapping("/create-post")
    public ResponseEntity<String> createPost(@RequestBody @Valid PostCreateRequest requestDTO) {
        return ResponseEntity.ok()
                .body(postService.createPost(
                                requestDTO.getTitle(),
                                requestDTO.getContent(),
                                requestDTO.getWriterId(),
                                requestDTO.getKeywords()
                        )
                );
    }

    @Operation(summary = "update post")
    @PostMapping("/update-post")
    public ResponseEntity<String> updatePost(@RequestBody @Valid PostUpdateRequest requestDTO) {
        return ResponseEntity.ok()
                .body(postService.updatePost(
                        requestDTO.getPostId(),
                        requestDTO.getTitle(),
                        requestDTO.getContent(),
                        requestDTO.getWriterId(),
                        requestDTO.getKeywords())
                );
    }

    // todo:
    //  soft delete?
    // post 삭제 시 댓글도 모두 삭제 필요
    // 이벤트 기반으로 구현?
    @Operation(summary = "delete post")
    @PostMapping("/delete-post")
    public ResponseEntity<Void> deletePost(String postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}
