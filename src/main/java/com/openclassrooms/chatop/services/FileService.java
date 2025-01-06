package com.openclassrooms.chatop.services;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

@Service
public class FileService {

    private final AmazonS3 s3Client;
    private final String bucketName;

    FileService(
            AmazonS3 s3Client,
            @Value("${aws.bucket.name}") String bucketName
    ) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    public String uploadFile(InputStream inputStream, String fileName, String contentType) {

        try {
            String key = UUID.randomUUID().toString() + "_" + fileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, null);
            s3Client.putObject(putObjectRequest);
            return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, Regions.EU_NORTH_1.getName(), key);

        } catch (AmazonS3Exception e) {
            throw new AmazonS3Exception(e.getMessage());
        }
    }
}