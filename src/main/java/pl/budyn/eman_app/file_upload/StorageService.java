package pl.budyn.eman_app.file_upload;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pl.budyn.eman_app.security.domain.entity.User;

import java.nio.file.Path;
import java.util.stream.Stream;

@Component
public interface StorageService {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();

    void save(MultipartFile multipartFile, User user);

}
