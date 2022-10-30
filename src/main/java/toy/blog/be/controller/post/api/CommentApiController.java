package toy.blog.be.controller.post.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toy.blog.be.controller.PagedRequest;
import toy.blog.be.controller.PagedResponse;
import toy.blog.be.controller.post.dto.request.CommentCreateRequest;
import toy.blog.be.controller.post.dto.request.CommentUpdateRequest;
import toy.blog.be.controller.post.dto.response.CommentResponse;
import toy.blog.be.domain.entity.Comment;
import toy.blog.be.service.CommentService;

import javax.validation.Valid;

@Tag(name = "comment apis")
@RequiredArgsConstructor
@RequestMapping("/comments")
@RestController
public class CommentApiController {
    private final CommentService commentService;

    @Operation(summary = "create a comment")
    @PostMapping("/create-comment")
    public ResponseEntity<String> createComment(@Valid @RequestBody CommentCreateRequest createRequest) {
        var id = commentService.createComment(
                createRequest.getContent(),
                createRequest.getWriterId()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(id);
    }

    @Operation(summary = "update a comment")
    @PostMapping("/update-comment")
    public ResponseEntity<String> updateComment(@Valid @RequestBody CommentUpdateRequest updateRequest) {
        var id = commentService.updateComment(
                updateRequest.getId(),
                updateRequest.getContent(),
                updateRequest.getWriterId()
        );

        return ResponseEntity.ok()
                .body(id);
    }

    @Operation(summary = "delete a comment")
    @PostMapping("/delete-comment")
    public ResponseEntity<Void> deleteComment(String id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "get a comment")
    @GetMapping("/comment")
    public ResponseEntity<CommentResponse> getComment(String id) {
        return ResponseEntity.ok()
                .body(commentService.getComment(id));
    }

    @Operation(summary = "get paged comments")
    @GetMapping
    public ResponseEntity<PagedResponse<CommentResponse, Comment>> getComments(@RequestBody @Valid PagedRequest pagedRequest) {
        return ResponseEntity.ok()
                .body(commentService.getComments(pagedRequest));
    }
}
