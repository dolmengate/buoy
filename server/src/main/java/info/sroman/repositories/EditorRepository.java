package info.sroman.repositories;

import info.sroman.entities.Editor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EditorRepository extends CrudRepository<Editor, Long>{

    @Query("SELECT e FROM Editor e " +
            "JOIN e.content c " +
            "JOIN c.post p " +
            "WHERE p.postId = :pId")
    Editor findOneByPostId(@Param("pId") Long postId);
}
