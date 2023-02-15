package es.nfq.jsonconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JsonConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsonConverterApplication.class, args);
    }

}
