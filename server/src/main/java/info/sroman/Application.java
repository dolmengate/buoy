package info.sroman;

import info.sroman.entities.Content;
import info.sroman.entities.Editor;
import info.sroman.entities.Post;
import info.sroman.entities.Type;
import info.sroman.model.PostDTO;
import info.sroman.repositories.ContentRepository;
import info.sroman.repositories.EditorRepository;
import info.sroman.repositories.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("info.sroman.repositories")  // enables creation and auto-implementation of -Repository<E,ID> interfaces
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner run(PostRepository posts, ContentRepository contents, EditorRepository editors) {
        return (args) -> {

            // simulated request object
            PostDTO pdto = new PostDTO("chad", "titel", "descipshun", Type.CODE, 1.1F, "", "codezdoezdodezodceodezodcezo");

            Post p = new Post(pdto.getTitle(), pdto.getAuthor(), pdto.getDescription());
            Editor e = new Editor(pdto.getEditorText());
            Content c = new Content(pdto.getType(), pdto.getContentText(), pdto.getVersion());

            // establish relationships through generated IDs and save records
            Long editorId = editors.save(e).getId();
            c.setAttachmentId(editorId);
            Long contentId = contents.save(c).getId();
            p.setContentId(contentId);

            log.info(posts.save(p).toString());
        };
    }
}
