package toy.blog.be.controller.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class FollowList {
    @NotNull
    String nickname;
}
