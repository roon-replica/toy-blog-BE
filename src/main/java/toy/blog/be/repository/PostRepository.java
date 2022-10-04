package toy.blog.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.blog.be.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, String> {
}
