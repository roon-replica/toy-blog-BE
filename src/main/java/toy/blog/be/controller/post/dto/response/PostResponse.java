package toy.blog.be.controller.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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

    private Set<String> keywords;   //todo: set<keywordDTO>를 반환하도록 나중에 바꿔야 할 듯(키워드 사용 횟수 정보도 반환하려면)

    private int viewCount;

    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;


}
