package toy.blog.be.controller.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import toy.blog.be.domain.entity.Keywords;

import java.time.LocalDateTime;

@Getter
public class KeywordDTO {
    private String id;

    private String word;

    private int useCount;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public KeywordDTO(String id, String word, int useCount, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.word = word;
        this.useCount = useCount;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public KeywordDTO(Keywords keyword){
        this.id = keyword.getId().toString();
        this.word = keyword.getWord();
        this.useCount = keyword.getUseCount();
        this.createdAt = keyword.getCreatedAt();
        this.modifiedAt = keyword.getModifiedAt();
    }
}
