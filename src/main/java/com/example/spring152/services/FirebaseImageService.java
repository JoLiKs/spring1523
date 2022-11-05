package com.example.spring152.services;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

@Service
public class FirebaseImageService {

private Storage storage;
    public String save(MultipartFile multipartFile){
        try {
            String imageName = String.valueOf(System.currentTimeMillis());
            BlobId blobId = BlobId.of("randomizer-523a6.appspot.com", imageName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(multipartFile.getContentType())
                    .build();
            storage = StorageOptions.newBuilder()
                    .setCredentials(ServiceAccountCredentials.fromStream(new FileInputStream("firebase.json")))
                    .build()
                    .getService();
            storage.create(blobInfo, multipartFile.getInputStream());
            return imageName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String getImgUrl(String fileName) {
        //https://firebasestorage.googleapis.com/v0/b/randomizer-523a6.appspot.com/o/ThePriseOfParadise.jpg?alt=media&token=30d8560d-21db-41ed-b43d-b6fb0af08d94
    return "https://firebasestorage.googleapis.com/v0/b/randomizer-523a6.appspot.com/o/"+fileName+"?alt=media&token=30d8560d-21db-41ed-b43d-b6fb0af08d94";
    }
}
