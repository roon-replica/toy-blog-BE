package toy.blog.be.controller.user.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class NicknameCheckRequest {
    @NotEmpty
    private String nickname;
}
