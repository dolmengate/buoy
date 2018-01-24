package info.sroman.repositories;

import info.sroman.entities.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findById(Long id);

}
