package toy.blog.be.controller.post.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class PostCreateRequest {
    @NotBlank
    private String title;

    private String content;

    @NotBlank
    private String writerId;
    private List<String> keywords;
}
