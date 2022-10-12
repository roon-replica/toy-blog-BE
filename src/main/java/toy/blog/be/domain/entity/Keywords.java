package toy.blog.be.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.blog.be.infra.IdGenerator;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Keywords {
    @Id
    private String id;

    private String word;

    private int useCount;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public Keywords(String id, String word) {
        this.id = id;
        this.word = word;

        var now = LocalDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;
    }
}
