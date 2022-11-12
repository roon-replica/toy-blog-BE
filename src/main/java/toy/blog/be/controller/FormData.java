package toy.blog.be.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class FormData {
    private String filename;
    private MultipartFile file;
}