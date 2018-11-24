package info.sroman.repositories;

import info.sroman.entities.Attachment;
import info.sroman.entities.Editor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface AttachmentRepository extends CrudRepository<Attachment, UUID>{

    @Query("SELECT a FROM Attachment a " +
            "JOIN a.post p " +
            "WHERE p.postId = :pId " +
            "AND a.attachmentType.name = 'EDITOR' ")
    List<Editor> findEditorByPostId(@Param("pId") String postId);
}
