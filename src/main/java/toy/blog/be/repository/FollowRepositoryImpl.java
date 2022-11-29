package toy.blog.be.repository;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import toy.blog.be.domain.entity.UserInfo;
import toy.blog.be.domain.entity.QFollow;
import toy.blog.be.domain.entity.QOAuthUserInfo;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class FollowRepositoryImpl {
    private final JPAQueryFactory jpaQueryFactory;

    QFollow qFollow = QFollow.follow;
    QOAuthUserInfo qoAuthUserInfo = QOAuthUserInfo.oAuthUserInfo;

    public List<UserInfo> getFollower(String followeeId) {
        return jpaQueryFactory
                .selectFrom(qoAuthUserInfo)
                .where(qoAuthUserInfo.id.in(JPAExpressions
                        .select(qFollow.followerId)
                        .from(qFollow)
                        .where(qFollow.followeeId.eq(followeeId))))
                .fetch();
    }

    public List<UserInfo> getFollowee(String followerId) {
        return jpaQueryFactory
                .selectFrom(qoAuthUserInfo)
                .where(qoAuthUserInfo.id.in(JPAExpressions
                        .select(qFollow.followerId)
                        .from(qFollow)
                        .where(qFollow.followeeId.eq(followerId))))
                .fetch();
    }
}
