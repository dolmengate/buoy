package info.sroman.repositories;

import info.sroman.entities.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends CrudRepository<Comment, UUID> {

    @Query("SELECT c FROM Comment c " +
            "WHERE c.replyTo.commentId = :cId")
    List<Comment> getAllByReplyTo(@Param("cId") String commentId);
}
