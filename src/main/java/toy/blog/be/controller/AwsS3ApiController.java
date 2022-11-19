package toy.blog.be.controller;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import toy.blog.be.service.AwsS3Service;

import java.io.IOException;

//todo: 이미지 전용 bucket이 정해져있고, 이미지만 업로드한다고 가정한 상태.
// bucket에 이미지 파일이 아닌 다른것도 업로드 하거나 이미지를 용도별로 분류해서 여러 버켓이 필요해진다면 코드의 수정이 많이 필요해져서.. 안 좋은 설계인 것 같음.
@Tag(name = "aws s3 apis")
@RequiredArgsConstructor
@RequestMapping("/images")
@RestController
public class AwsS3ApiController {

    private final AwsS3Service s3Service;

    @Operation(summary = "get a file from s3")
    @GetMapping("/image")
    public S3Object getFile(String objectKey) {
        return s3Service.getFile(objectKey);
    }

    @Operation(summary = "get all files from s3")
    @GetMapping
    public ObjectListing getAll() {
        return s3Service.getAllInBucket();
    } // todo: paging 필요할듯

    @Operation(summary = "upload a file to s3")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(String filename, @RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {
        s3Service.uploadFile(filename, FileUtil.multipartToFile(multipartFile));
    }
}
