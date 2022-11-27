package toy.blog.be.controller.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import toy.blog.be.domain.entity.OAuthUserInfo;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class FollowResponse {
    List<OAuthUserInfo> oAuthUserInfos;
}
