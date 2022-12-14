package toy.blog.be.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Comment {
    @Id
    private String id;

    private String content;
    private String writerId;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public Comment(String id, String content, String writerId) {
        this.id = id;
        this.content = content;
        this.writerId = writerId;

        var now = LocalDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;
    }

    public void update(String content, String writerId){
        this.content = content;
        this.writerId = writerId;
        this.modifiedAt = LocalDateTime.now();
    }
}
