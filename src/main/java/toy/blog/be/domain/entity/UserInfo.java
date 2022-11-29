package toy.blog.be.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import toy.blog.be.infra.IdGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@DynamicUpdate
@Entity
@Getter
public class UserInfo {
    @Id
    private String id;

    private String name;
    private String email;
    private String provider;
    private String nickname;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;

    @Builder
    public UserInfo(String name, String email, String provider, String nickname) {
        this.id = IdGenerator.newId();
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.nickname = nickname;
        var now = LocalDateTime.now();
        this.createAt = now;
        this.modifiedAt = now;
    }

    public UserInfo update(String name, String email) {
        this.name = name;
        this.email = email;
        this.modifiedAt = LocalDateTime.now();
        return this;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
        this.modifiedAt = LocalDateTime.now();
    }
}
