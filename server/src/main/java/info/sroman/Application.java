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
            Post p = posts.save(new Post(
                    "my titel",
                    "chadimus",
                    "the description",
                    new Content(new Editor("codezcodezcodezcodezcodez"), Type.EDITOR, null, 1.0F)
            ));
            comments.save(new Comment("comment text", "the virgin", p));
        };
    }
}
