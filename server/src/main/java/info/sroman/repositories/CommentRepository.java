package info.sroman.repositories;

import info.sroman.entities.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    public List<Comment> findCommentByPostId(Long postId);

}
