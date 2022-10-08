package toy.blog.be.controller.post.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PostUpdateRequest {
    @NotNull
    private String postId;

    @NotBlank
    private String title;

    private String content;
    private String writerId;
    private List<String> keywords;
}
