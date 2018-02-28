package info.sroman;

import info.sroman.entities.*;
import info.sroman.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("info.sroman.repositories")  // enables creation and auto-implementation of -Repository<E,ID> interfaces
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner run(PostRepository posts, ContentRepository contents, EditorRepository editors, CommentRepository comments) {
        return (args) -> {
            Comment c1 = new Comment("virginia", "comment text");
            Post p = posts.save(
                    new Post(
                            "my titel",
                            "chadimus",
                            "the description",
                            new Content(new Editor("codezcodezcodezcodezcodez"), Type.EDITOR, null, 1.0F),
                            c1
                    )
            );

            c1 = comments.findOne(c1.getCommentId());

            Comment c2 = new Comment("me", "hi dot com", c1);

            p.getComments().add(c2);
            posts.save(p);

            System.out.println("replies: " + comments.getAllByReplyTo(c1.getCommentId()).size());

            System.out.println("post: " + posts.findOne(p.getPostId()));
        };
    }
}
