package toy.blog.be.controller.follow.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toy.blog.be.controller.follow.dto.FollowID;
import toy.blog.be.domain.entity.OAuthUserInfo;
import toy.blog.be.service.FollowService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/follow")
@RequiredArgsConstructor
@RestController
public class FollowApiController {
    private final FollowService followService;
    @GetMapping("/create-follow")
    public ResponseEntity<Void> createFollow(@RequestBody @Valid FollowID followID) {
        var id = followService.createFollow(
                followID.getFollowerId(),
                followID.getFolloweeId()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cancel-follow")
    public ResponseEntity<Void> cancelFollow(@RequestBody @Valid FollowID followID) {
        followService.deleteFollow(
                followID.getFollowerId(),
                followID.getFolloweeId()
        );
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get-follower")
    public ResponseEntity<List<OAuthUserInfo>> getFollower(String userId) {
        var followers = followService.getFollower(userId);
        return ResponseEntity.ok().body(followers);
    }

    @GetMapping("/get-followee")
    public ResponseEntity<List<OAuthUserInfo>> getFollowee(String userId) {
        var followees = followService.getFollower(userId);
        return ResponseEntity.ok().body(followees);
    }

    @GetMapping("/count-follower")
    public ResponseEntity<Integer> countFollower(String userId) {
        var count = followService.countFollower(userId);
        return ResponseEntity.ok().body(count);
    }

    @GetMapping("/count-followee")
    public ResponseEntity<Integer> countFollowee(String userId) {
        var count = followService.countFollowee(userId);
        return ResponseEntity.ok().body(count);
    }


}
