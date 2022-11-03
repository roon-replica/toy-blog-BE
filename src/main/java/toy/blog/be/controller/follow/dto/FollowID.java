package toy.blog.be.controller.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class FollowID implements Serializable {
    private String followerId;
    private String followeeId;
}
