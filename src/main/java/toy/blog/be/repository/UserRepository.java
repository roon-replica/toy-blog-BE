package toy.blog.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import toy.blog.be.domain.entity.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, String> {
}
