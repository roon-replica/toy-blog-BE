package toy.blog.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toy.blog.be.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
}
