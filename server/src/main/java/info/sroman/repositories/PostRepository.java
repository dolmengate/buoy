package info.sroman.repositories;

import info.sroman.entities.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends CrudRepository<Post, UUID> {

    List<Post> findByPostId(String id);
    List<Post> findByTitle(String title);
    List<Post> findByAuthor(String author);
}
