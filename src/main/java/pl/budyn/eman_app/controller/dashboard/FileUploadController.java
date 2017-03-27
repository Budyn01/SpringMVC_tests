package pl.budyn.eman_app.controller.dashboard;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.budyn.eman_app.file_upload.StorageFileNotFoundException;
import pl.budyn.eman_app.file_upload.StorageService;
import pl.budyn.eman_app.model.entity.Photo;
import pl.budyn.eman_app.model.repository.PhotoRepository;
import pl.budyn.eman_app.security.domain.entity.User;
import pl.budyn.eman_app.security.repository.UserRepository;
import pl.budyn.eman_app.security.service.user.UserService;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hlibe on 16.12.2016.
 */
@Controller
public class FileUploadController {

    private static final Log logger = LogFactory.getLog(FileUploadController.class);

    private final StorageService storageService;
    private final UserRepository userRepository;

    @Autowired
    public FileUploadController(StorageService storageService, UserService userService, UserRepository userRepository, PhotoRepository photoRepository) {
        this.storageService = storageService;
        this.userRepository = userRepository;
    }

    @GetMapping("/upload")
    public String listUploadedFiles(Model model) throws IOException{
        model.addAttribute("files", storageService.loadAll()
        .map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class, "serveFile",
                path.getFileName().toString()).build().toString()).collect(Collectors.toList()));
        return "uploadForm";
    }
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename){
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+ file.getFilename()+"\"").body(file);
    }
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, Authentication authentication){
        System.out.println("File uploaded by: " + authentication.getPrincipal().toString() + "\n And:" + authentication.getName());
        storageService.save(file, userRepository.findOneByEmail(authentication.getName()).get());
        redirectAttributes.addFlashAttribute("message", "You sucessfully uploaded" + file.getOriginalFilename() + "!");
        return "redirect:/";
    }
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException exc){
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/photo")
    public String checkUserPhoto(Authentication authentication){
        User user = userRepository.findOneByEmail(authentication.getName()).get();
        user.getPhotos().forEach((a) -> logger.info(a.getFilePath()));
        return "redirect:/";
    }
}
