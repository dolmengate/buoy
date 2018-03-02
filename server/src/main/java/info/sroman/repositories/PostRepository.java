package info.sroman.repositories;

import info.sroman.entities.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {

    @Query("SELECT p FROM Post p " +
            "INNER JOIN p.content c " +
            "INNER JOIN c.editor e " +
            "WHERE e.attachmentId = :eId")
    Post findOneByEditorId(@Param("eId") Long editorId);
    List<Post> findByPostId(Long id);
    List<Post> findByTitle(String title);
    List<Post> findByAuthor(String author);
}
