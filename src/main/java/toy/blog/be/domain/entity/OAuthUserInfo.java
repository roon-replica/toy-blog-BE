package toy.blog.be.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import toy.blog.be.infra.IdGenerator;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@DynamicUpdate
@Entity
@Getter
public class OAuthUserInfo {
    @Id
    private String id;

    private String name;
    private String email;
    private String provider;
    private String nickname;

    @Builder
    public OAuthUserInfo(String name, String email, String provider, String nickname) {
        this.id = IdGenerator.newId();
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.nickname = nickname;
    }

    public OAuthUserInfo update(String name, String email) {
        this.name = name;
        this.email = email;
        return this;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
}
