package toy.blog.be.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import toy.blog.be.domain.value.KeywordId;
import toy.blog.be.domain.value.PostId;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "posts",
        indexes = {
                @Index(name = "idx_title", columnList = "title"),
                @Index(name = "idx_created_at_modified_at", columnList = "createdAt, modifiedAt")
        }
)
@Entity
public class Post {
    @EmbeddedId
    private PostId id;

    @Column(name = "title", columnDefinition = "varchar(200)", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "longtext")
    private String content;

    @Column(columnDefinition = "varchar(10)")
    private String writerId; //todo: writerId 가지고 있으면 되겠지?

    @Column(columnDefinition = "int")
    private int viewCount;

    @ElementCollection
    @CollectionTable(name = "keyword_ids", joinColumns = @JoinColumn(name = "post_id", columnDefinition = "varchar(10)"))
    private Set<KeywordId> keywordIds = new HashSet<>();

    @Column(columnDefinition = "datetime(6)")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "datetime(6)")
    private LocalDateTime modifiedAt;

    @Builder
    public Post(PostId id, String title, String content, String writerId, Set<KeywordId> keywordIds) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.keywordIds = keywordIds;

        var now = LocalDateTime.now();
        this.createdAt = now;
        this.modifiedAt = now;
    }

    public void update(String title, String content, String writerId, Set<KeywordId> keywordIds) {
        this.title = title;
        this.content = content;
        this.writerId = writerId;
        this.keywordIds = keywordIds;

        this.modifiedAt = LocalDateTime.now();

    }

    public void increaseViewCount() {
        viewCount++;
    }

}
