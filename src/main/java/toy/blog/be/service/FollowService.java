package toy.blog.be.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import toy.blog.be.controller.follow.dto.FollowID;
import toy.blog.be.domain.entity.Follow;
import toy.blog.be.domain.entity.OAuthUserInfo;
import toy.blog.be.repository.FollowRepository;
import toy.blog.be.repository.FollowRepositoryImpl;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final FollowRepositoryImpl followRepositoryImpl;

    @Transactional
    public String createFollow(String followerId, String followeeId) {
        var follow = Follow.builder()
                .followerId(followerId)
                .followeeId(followeeId)
                .build();

        return followRepository.save(follow).getFollowerId();
    }

    @Transactional
    public void deleteFollow(String followerId, String followeeId) {
        FollowID followId = new FollowID(followerId, followeeId);
        var follow = followRepository.findById(followId)
                .orElseThrow(EntityNotFoundException::new);
        followRepository.delete(follow);
    }

    public int countFollower(String userId) {
        var count = followRepository.countFollowByFolloweeId(userId);
        return Math.toIntExact(count);
    }

    public int countFollowee(String userId) {
        var count = followRepository.countFollowByFollowerId(userId);
        return Math.toIntExact(count);
    }

    @Transactional
    public List<OAuthUserInfo> getFollower(String followeeId) {
        var followers = followRepositoryImpl.getFollower(followeeId);
        return followers; // TODO: list size 0 일때 처리 해줘야함
    }


    @Transactional
    public List<OAuthUserInfo> getFollowee(String followerId) {
        var followees = followRepositoryImpl.getFollowee(followerId);
        return followees; // TODO: list size 0 일때 처리 해줘야함
    }

}
