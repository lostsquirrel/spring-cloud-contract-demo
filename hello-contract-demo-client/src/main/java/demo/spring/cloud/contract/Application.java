package demo.spring.cloud.contract;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(Application.class).run(args);
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
