package toy.blog.be.controller.user.dto.request;

import lombok.Data;

@Data
public class NicknameUpdateRequest {
    private String id;
    private String nickname;
}
