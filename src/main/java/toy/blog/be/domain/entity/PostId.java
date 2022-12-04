package toy.blog.be.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Embeddable
public class PostId implements Serializable {
    @Column(columnDefinition = "varchar(10)")
    private String id;

    public PostId(String id){
        if(ObjectUtils.isEmpty(id)){
            throw new IllegalArgumentException("post id must not be null or empty");
        }
        this.id = id;
    }

    public String toString(){
        return id;
    }
}
