package toy.blog.be.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.blog.be.infra.IdGenerator;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "keywords")
@Entity
public class Keywords {
    @EmbeddedId
    private KeywordId id;

    private String word;

    private int useCount;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public Keywords(KeywordId id, String word) {
        this.id = id;
        this.word = word;

        var now = LocalDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;
    }

    public void increaseCount() {
        useCount++;
    }

    public void decreaseCount() {
        useCount--;
    }
}
