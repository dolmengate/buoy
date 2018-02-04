package info.sroman.repositories;

import info.sroman.entities.Editor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface EditorRepository extends CrudRepository<Editor, Long>{

    @Query("SELECT e FROM Editor e " +
            "INNER JOIN e.content c " +
            "INNER JOIN c.post p " +
            "WHERE p.postId = :pId")
    Editor findOneByPostId(@Param("pId") Long postId);
}
