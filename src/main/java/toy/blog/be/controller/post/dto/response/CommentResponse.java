package toy.blog.be.controller.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class CommentResponse {
    private String id;
    private String content;
    private String writerId;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
