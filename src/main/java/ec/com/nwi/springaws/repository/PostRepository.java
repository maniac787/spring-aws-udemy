package ec.com.nwi.springaws.repository;

import ec.com.nwi.springaws.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

}