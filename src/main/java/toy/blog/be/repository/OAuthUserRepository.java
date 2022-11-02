package toy.blog.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.blog.be.domain.entity.OAuthUserInfo;

import java.util.Optional;

public interface OAuthUserRepository extends JpaRepository<OAuthUserInfo, String> {
    Optional<OAuthUserInfo> findByEmailAndProvider(String email, String provider);
    boolean existsByNickname(String nickname);
}
