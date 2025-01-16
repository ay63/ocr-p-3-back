package com.openclassrooms.chatop.services;


import com.amazonaws.services.s3.model.AmazonS3Exception;
import org.springframework.stereotype.Service;
import java.io.InputStream;


@Service
public interface FileService {

     String uploadFile(InputStream inputStream, String fileName) throws AmazonS3Exception;

}