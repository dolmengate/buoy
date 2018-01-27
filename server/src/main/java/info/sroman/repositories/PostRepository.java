package info.sroman.repositories;

import info.sroman.entities.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// automagically creates REST endpoints for data access from DB!
public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findById(Long id);
    List<Post> findByTitle(String title);
    List<Post> findByAuthor(String author);
}
