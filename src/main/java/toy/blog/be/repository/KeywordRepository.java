package toy.blog.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.blog.be.domain.entity.Keywords;

public interface KeywordRepository extends JpaRepository<Keywords, String> {
}
