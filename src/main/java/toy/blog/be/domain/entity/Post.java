package toy.blog.be.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
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
    public Post(String id, String title, String content, String writerId, Set<String> keywordIds) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.keywordIds = keywordIds;

        var now = LocalDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;
    }

    //todo: keyword 아직 구현 안 됨
    public void update(String title, String content, String writerId, Set<String> keywordIds){
        this.title =title;
        this.content = content;
        this.writerId = writerId;
        this.keywordIds = keywordIds;

        this.modifiedAt = LocalDateTime.now();

    }

}
