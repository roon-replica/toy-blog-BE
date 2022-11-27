package toy.blog.be.controller.follow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowID implements Serializable {
    @NotNull
    private String followerId;
    @NotNull
    private String followeeId;
}
