package toy.blog.be.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AwsS3Service {
    @Value("${aws.s3.bucket-names.toy-be-image}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3client;

    public S3Object getFile(String objectKey) { // todo: file vs object
        return s3client.getObject(bucketName, objectKey);
    }

    public ObjectListing getAllInBucket() {
        return s3client.listObjects(bucketName);
    }

    public void uploadFile(String key, File file) {
        s3client.putObject(bucketName, key, file);
    }
}
