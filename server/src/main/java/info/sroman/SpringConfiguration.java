package info.sroman;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("info.sroman.repositories")
public class SpringConfiguration { }
