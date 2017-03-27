package pl.budyn.other.run;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import pl.budyn.eman_app.file_upload.StorageProperties;
import pl.budyn.eman_app.file_upload.StorageService;

/**
 * Created by hlibe on 16.12.2016.
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@ComponentScan("pl")
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
