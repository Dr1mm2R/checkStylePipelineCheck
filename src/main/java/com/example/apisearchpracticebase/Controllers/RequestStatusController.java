package com.example.apisearchpracticebase.Controllers;

import com.example.apisearchpracticebase.Models.RequestStatus;
import com.example.apisearchpracticebase.Models.RequestSubmitted;
import com.example.apisearchpracticebase.Repositories.RequestStatusRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("request_status")
public class RequestStatusController {
    @Autowired
    RequestStatusRepos requestStatusRepos;

    @RequestMapping("/all")
    public Iterable<RequestStatus> getAllRequestStatus (){
        return requestStatusRepos.findAll();
    }
}
