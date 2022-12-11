package toy.blog.be.domain.value;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class KeywordId implements Serializable {
    @Column(columnDefinition = "varchar(10)")
    private String id;

    public KeywordId(String id){
        if(ObjectUtils.isEmpty(id)){
            throw new IllegalArgumentException("keyword must not be null nor empty");
        }
        this.id = id;
    }

    public String toString(){
        return id;
    }
}
