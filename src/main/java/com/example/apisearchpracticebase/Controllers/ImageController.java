package com.example.apisearchpracticebase.Controllers;

import com.example.apisearchpracticebase.Models.PracticeBase;
import com.example.apisearchpracticebase.Models.Student;
import com.example.apisearchpracticebase.Repositories.PracticeBaseRepos;
import com.example.apisearchpracticebase.Repositories.StudentRepos;
import com.example.apisearchpracticebase.Services.ImageService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Controller
@RequestMapping("/images")
public class ImageController {
    @Autowired
    PracticeBaseRepos practiceBaseRepos;

    @Autowired
    StudentRepos studentRepos;

    @Autowired
    ImageService imageService;

    private final Storage storage;

    public ImageController() throws IOException {
        FileInputStream serviceAccount = new FileInputStream("C:\\Users\\gusdi\\IdeaProjects\\ApiSearchPracticeBase\\src\\main\\resources\\searchpracticebaseproject-firebase-adminsdk-60xwx-7514dc3fb5.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        StorageOptions options = StorageOptions.newBuilder().setCredentials(credentials).build();
        storage = options.getService();
    }

    @GetMapping("upload")
    public String getPage(){
        return "uploadImageTest";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam("isPracticeBase") boolean isBase,
                                              @RequestParam("uploadingID") long id) {
        try {
            String fileName = generateFilename(Objects.requireNonNull(file.getOriginalFilename()));
            String storagePath = isBase ? "practiceBaseImages/" : "profileImages/";

            InputStream content = file.getInputStream();

            BlobId blobId = BlobId.of("searchpracticebaseproject.appspot.com", storagePath + fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            storage.create(blobInfo, content);

            if (isBase) {
                PracticeBase practiceBase = practiceBaseRepos.findById(id).orElseThrow(() -> new RuntimeException("PracticeBase not found"));
                practiceBase.setPhotoPlace(fileName);
                practiceBaseRepos.save(practiceBase);
            } else {
                Student student = studentRepos.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
                student.getResume().setPhotoStudent(fileName);
                studentRepos.save(student);
            }

            return ResponseEntity.ok("Image uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload image");
        }
    }

    private String generateFilename(String originalFilename) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        return DigestUtils.md5Hex(originalFilename + timestamp) + getFileExtension(originalFilename);
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }

    @GetMapping("/get")
    public ResponseEntity<byte[]> getImage(@RequestParam("isPracticeBase") boolean isBase, @RequestParam("filename") String filename) {
        String bucketName = "searchpracticebaseproject.appspot.com";
        String storagePath = isBase ? "practiceBaseImages/" : "profileImages/";
        String storageFileName = storagePath + filename;
        Blob blob = storage.get(bucketName, storageFileName);

        byte[] imageBytes = blob.getContent();

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }
}
