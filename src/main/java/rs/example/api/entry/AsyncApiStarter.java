package rs.example.api.entry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.AsyncRestTemplate;

@SpringBootApplication
@ComponentScan("rs.example.api")
@EnableAsync
public class AsyncApiStarter {

    public static void main(String[] args) {
        SpringApplication.run(AsyncApiStarter.class, args);
    }

    @Bean
    public AsyncRestTemplate getRestTemplate() {
        return new AsyncRestTemplate();
    }
}
