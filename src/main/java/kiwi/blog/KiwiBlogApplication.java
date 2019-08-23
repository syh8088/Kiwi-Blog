package kiwi.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
@EnableResourceServer
public class KiwiBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(KiwiBlogApplication.class, args);
    }

}
