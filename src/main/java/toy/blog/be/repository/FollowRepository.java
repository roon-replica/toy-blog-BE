package toy.blog.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.blog.be.controller.follow.dto.FollowID;
import toy.blog.be.domain.entity.Follow;

@Repository
public interface FollowRepository extends JpaRepository<Follow, FollowID> {
    Long countFollowByFollowerId(String followerId);
    Long countFollowByFolloweeId(String followeeId);
//    List<Follow> findAllByFollowerId(String followerId);
//    List<Follow> findAllByFolloweeId(String followeeId);
}
