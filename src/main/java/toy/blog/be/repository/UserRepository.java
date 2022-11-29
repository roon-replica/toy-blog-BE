package toy.blog.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.blog.be.domain.entity.UserInfo;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, String> {
    Optional<UserInfo> findByEmailAndProvider(String email, String provider);
    boolean existsByNickname(String nickname);
}
