package toy.blog.be.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import toy.blog.be.domain.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Long> {

	Optional<UserInfo> findByEmail(String email);
}
