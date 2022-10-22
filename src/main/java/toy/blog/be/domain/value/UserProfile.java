package toy.blog.be.domain.value;

import lombok.Getter;
import lombok.Setter;
import toy.blog.be.domain.entity.OAuthUserInfo;

@Getter
@Setter
public class UserProfile {
    private String name;
    private String email;
    private String provider;
    private String nickname;

    public OAuthUserInfo toOAuthUserInfo() {
        return OAuthUserInfo.builder()
                            .name(name)
                            .email(email)
                            .provider(provider)
                            .build();
    }
}
