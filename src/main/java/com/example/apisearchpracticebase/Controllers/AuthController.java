package com.example.apisearchpracticebase.Controllers;

import com.example.apisearchpracticebase.Models.Contact;
import com.example.apisearchpracticebase.Models.ResumeStudent;
import com.example.apisearchpracticebase.Models.Student;
import com.example.apisearchpracticebase.Repositories.*;
import com.example.apisearchpracticebase.Security.JWTProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/authentication")
public class AuthController {

    private final JWTProvider jwtProvider;

    public AuthController(JWTProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Autowired
    StudentRepos studentRepos;
    @Autowired
    CollegePracticeManagerRepos collegePracticeManagerRepos;
    @Autowired
    PracticeManagerRepos practiceManagerRepos;
    @Autowired
    ResumeStudentRepos resumeStudentRepos;
    @Autowired
    ContactRepos contactRepos;

    @PostMapping("/student")
    public ResponseEntity<Map<String, Object>> authentication(@RequestBody Map<String, String> requestData){
        String email = requestData.get("email");
        String first = "";
        String second = "";
        String middle = "";

        try{
            first = requestData.get("first");
            second = requestData.get("second");
            middle = requestData.get("middle");
        }catch (Exception e){

        }

        Student studentTemp = new Student();
        if(studentRepos.findByStudentLogin(email).isEmpty()){
            studentTemp.setStudentLogin(email);
            studentTemp.setFirstName(first);
            studentTemp.setLastName(second);
            studentTemp.setMiddleName(middle);

            String uniqueKey = "tokenUnique";
            Contact emptyContact = new Contact();
            emptyContact.setAddress(uniqueKey);
            emptyContact.setEmail(email);
            emptyContact.setPhoneNumber("");
            emptyContact.setTelegramData("");
            emptyContact.setVkPageData("");
            emptyContact.setWhatsAppData("");
            contactRepos.save(emptyContact);
            emptyContact = contactRepos.findByAddress(uniqueKey).get();
            emptyContact.setAddress("");
            contactRepos.save(emptyContact);

            ResumeStudent emptyResume = new ResumeStudent();
            emptyResume.setContact(emptyContact);
            emptyResume.setPurposeInternship("");
            emptyResume.setPersonalQualities("");
            emptyResume.setPreferredLanguages("");
            emptyResume.setProfessionalSkills("");
            emptyResume.setEducation("");
            emptyResume.setPhotoStudent("");
            resumeStudentRepos.save(emptyResume);

            studentTemp.setResume(resumeStudentRepos.findByContact(emptyContact).get());
            studentRepos.save(studentTemp);
        }else{
            studentTemp = studentRepos.findByStudentLogin(email).get();
        }

        Map<String, Object> map = new HashMap<>();
        map.put("student", studentTemp);
        map.put("token", jwtProvider.createToken(studentTemp.getStudentLogin(), List.of("STUDENT")));

        return ResponseEntity.ok(map);
    }
}
