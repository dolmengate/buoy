package info.sroman.repositories;

import info.sroman.entities.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, String> {

    @Query("SELECT c FROM Comment c " +
            "WHERE c.replyTo.commentId = :cId")
    List<Comment> getAllByReplyTo(@Param("cId") Long commentId);
}
