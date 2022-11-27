package toy.blog.be.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.blog.be.controller.follow.dto.FollowID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@IdClass(FollowID.class)
public class Follow implements Serializable {
    @Id
    private String followerId;
    @Id
    private String followeeId;
    private LocalDateTime followedAt;

    @Builder
    Follow(String followerId, String followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
        this.followedAt = LocalDateTime.now();
    }
}
