package toy.blog.be.controller.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import toy.blog.be.domain.entity.UserInfo;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class FollowResponse {
    List<UserInfo> userInfos;
}
