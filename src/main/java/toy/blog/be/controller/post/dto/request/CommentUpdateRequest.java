package toy.blog.be.controller.post.dto.request;

import lombok.Data;

@Data
public class CommentUpdateRequest {
    private String id;
    private String content;
    private String writerId;
}
