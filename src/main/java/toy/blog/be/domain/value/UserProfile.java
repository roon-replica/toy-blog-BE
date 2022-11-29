package toy.blog.be.domain.value;

import lombok.Getter;
import lombok.Setter;
import toy.blog.be.domain.entity.UserInfo;

@Getter
@Setter
public class UserProfile {
    private String name;
    private String email;
    private String provider;
    private String nickname;

    public UserInfo toOAuthUserInfo() {
        return UserInfo.builder()
                            .name(name)
                            .email(email)
                            .provider(provider)
                            .build();
    }
}
