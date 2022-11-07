package toy.blog.be.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.blog.be.controller.follow.dto.FollowID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@IdClass(FollowID.class)
public class Follow implements Serializable {
    @Id
    private String followerId;
    @Id
    private String followeeId;
}
