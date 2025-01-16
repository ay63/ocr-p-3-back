package com.openclassrooms.chatop.services.implementation;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.openclassrooms.chatop.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {

    private final AmazonS3 s3Client;
    private final String bucketName;

    FileServiceImpl(
            AmazonS3 s3Client,
            @Value("${aws.bucket.name}") String bucketName
    ) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    /**
     * Upload file into S3 bucket
     * Build key with uuid to make sure the file is uniq in bucket
     * @param inputStream InputStream
     * @param fileName    String
     * @return url S3
     * @throws AmazonS3Exception
     */
    @Override
    public String uploadFile(InputStream inputStream, String fileName) throws AmazonS3Exception {
        String key = UUID.randomUUID() + "_" + fileName;
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, null);
        s3Client.putObject(putObjectRequest);
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, Regions.EU_NORTH_1.getName(), key);
    }
}
