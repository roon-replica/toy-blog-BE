package toy.blog.be.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Post {
    @Id
    private String id;

    private String title;

    private String content;

    private String writerId; //todo: writerId 가지고 있으면 되겠지?

    private int viewCount;

    @ElementCollection
    @CollectionTable(name = "keywords", joinColumns = @JoinColumn(name = "post_id"))
    private Set<String> keywordIds = new HashSet<>();

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public Post(String id, String title, String content, String writerId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerId = writerId;

        var now = LocalDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;
    }

}
