package toy.blog.be.controller.post.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class PostUpdateRequest {
    private String postId;
    private String title;
    private String content;
    private String writerId;
    private List<String> keywords;
}
