package toy.blog.be.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Embeddable;

@Getter
@EqualsAndHashCode // todo: 정확히 모름
@Embeddable
public class Keyword {
    private String word;

    private int useCount;
}
