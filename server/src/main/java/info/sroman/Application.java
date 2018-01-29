package info.sroman;

import info.sroman.entities.*;
import info.sroman.model.PostDTO;
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

            // simulated request object
            PostDTO pdto = new PostDTO(
                    "chad", "titel", "descipshun", Type.CODE, 1.1F, "",
                    "codezdoezdodezodceodezodcezo");

            Post p = new Post(pdto.getTitle(), pdto.getAuthor(), pdto.getDescription());
            Editor e = new Editor(pdto.getEditorText());
            Content c = new Content(pdto.getType(), pdto.getContentText(), pdto.getVersion());

            // establish relationships through generated IDs and save records
            Long editorId = editors.save(e).getId();
            c.setAttachmentId(editorId);
            Long contentId = contents.save(c).getId();
            p.setContentId(contentId);

            Post savedPost = posts.save(p);

            // simulated request object
            pdto = new PostDTO(
                    "caesar", "my title", "burn all gauls", Type.TEXT, 1.0F, "this would be text that would be here text",
                    "");

            p = new Post(pdto.getTitle(), pdto.getAuthor(), pdto.getDescription());
            c = new Content(pdto.getType(), pdto.getContentText(), pdto.getVersion());

            // establish relationships through generated IDs and save records
            contentId = contents.save(c).getId();
            p.setContentId(contentId);

            savedPost = posts.save(p);

            comments.save(new Comment("this is the text for a comment", "post author", savedPost.getId()));
            comments.save(new Comment("this is the text for another comment", "chad", savedPost.getId()));
        };
    }
}
