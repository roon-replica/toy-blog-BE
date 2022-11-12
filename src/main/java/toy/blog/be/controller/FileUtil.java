package toy.blog.be.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

@Component
public class FileUtil {

    @Value("${aws.s3.filePath}")
    private String tmpFilePath;

    private static String filePath;

    @Value("${aws.s3.filePath}")
    public void setFilePath(String tmpFilePath){
        filePath = tmpFilePath;
    }

    public static File multipartToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();

        return file;
    }
}