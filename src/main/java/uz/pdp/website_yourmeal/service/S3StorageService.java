package uz.pdp.website_yourmeal.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class S3StorageService {

    private final AmazonS3 client;
    private final String bucketName = "yourmealg40bucket";

    public S3StorageService(AmazonS3 client) {
        this.client = client;
    }

    public String uploadToPublic(MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            File file1 = convertMultiPartToFile(file);
            client.putObject(bucketName, "public/" + originalFilename, file1);
            return originalFilename;
        } catch (AmazonServiceException e) {
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public InputStream download(String keyName) {
        S3Object o = client.getObject(bucketName, keyName);
        S3ObjectInputStream objectContent = o.getObjectContent();
        return objectContent;
    }

    public void delete() {

    }


    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}