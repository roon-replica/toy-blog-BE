package toy.blog.be.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode // todo: 정확히 모름
@Entity
public class Keywords {
    @Id
    private String id;

    private String word;

    private int useCount;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
