package info.sroman;

import info.sroman.entities.*;
import info.sroman.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

@SpringBootApplication
@EnableJpaRepositories("info.sroman.repositories")  // enables creation and auto-implementation of -Repository<E,ID> interfaces
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner run(PostRepository posts, AttachmentRepository editors, CommentRepository comments) {
        return (args) -> { };
    }
}
