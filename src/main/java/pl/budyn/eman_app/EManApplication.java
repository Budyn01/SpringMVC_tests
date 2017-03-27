package pl.budyn.eman_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import pl.budyn.eman_app.file_upload.StorageProperties;
import pl.budyn.eman_app.file_upload.StorageService;
import pl.budyn.eman_app.model.entity.Employee;
import pl.budyn.eman_app.model.entity.Job;
import pl.budyn.eman_app.model.repository.EmployeeRepository;
import pl.budyn.eman_app.model.repository.JobRepository;
import pl.budyn.eman_app.model.service.MailService;
import pl.budyn.eman_app.security.domain.entity.Authority;
import pl.budyn.eman_app.security.domain.Role;
import pl.budyn.eman_app.security.domain.UserCreateForm;
import pl.budyn.eman_app.security.domain.entity.User;
import pl.budyn.eman_app.security.service.user.UserService;

import java.util.Arrays;

/**
 * Created by hlibe on 29.12.2016.
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class EManApplication {

    public static void main(String[] args) {
        SpringApplication.run(EManApplication.class, args);
    }

    @Bean
    CommandLineRunner init(EmployeeRepository employeeRepository, JobRepository jobRepository, UserService userService, MailService mailService, StorageService storageService){
        return args -> {
            Arrays.asList("job1,job2,job3,job4".split(",")).forEach(
                    a -> {
                        Job job = new Job(a, 13.5f);
                        jobRepository.save(job);
                    }
            );
            for (int i = 0; i < 4; i++) {
            Arrays.asList("wojtek,damian,andrzej,zdzislaw".split(",")).forEach(
                    a -> {
                        Employee employee = new Employee(a, "surname", "email@gmail.com", "8848378135", "43-400 Cieszyn Zamkowa 5", (long) 1);
                        employeeRepository.save(employee);
                    });
            }

            UserCreateForm user = new UserCreateForm();
            user.setName("Kudyniuk");
            user.setSurname("Wojciech");
            user.setEmail("h@gm.com");
            user.setPassword("123");
            user.setPasswordRepeated("123");
            user.addAuthority(new Authority(Role.ADMIN));
            User user1 = userService.createUser(user);
            userService.enableUser(user1);
            System.out.println(userService.getUserByEmail("h@gm.com").toString());

            storageService.deleteAll();
            storageService.init();

        };
    }


}
