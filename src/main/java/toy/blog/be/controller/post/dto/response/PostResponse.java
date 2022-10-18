package toy.blog.be.controller.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import toy.blog.be.domain.entity.Keywords;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@AllArgsConstructor
@Getter
public class PostResponse {
    private String id;
    private String title;
    private String content;
    private String writerId;

    private Set<Keywords> keywords;

    private int viewCount;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


}
