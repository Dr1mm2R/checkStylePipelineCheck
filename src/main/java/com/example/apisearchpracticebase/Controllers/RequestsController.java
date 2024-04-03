package com.example.apisearchpracticebase.Controllers;

import com.example.apisearchpracticebase.Models.RequestStatus;
import com.example.apisearchpracticebase.Models.RequestSubmitted;
import com.example.apisearchpracticebase.Repositories.RequestStatusRepos;
import com.example.apisearchpracticebase.Repositories.RequestSubmittedRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("requests")
public class RequestsController {
    @Autowired
    RequestSubmittedRepos requestSubmittedRepos;

    @Autowired
    RequestStatusRepos requestStatusRepos;

    @RequestMapping("/get")
    public Iterable<RequestSubmitted> getAllRequests (@RequestParam("id") long id, @RequestParam("sortParam") String param){
        Iterable<RequestStatus> allStatuses = requestStatusRepos.findAll();
        for (RequestStatus status : allStatuses) {
            if(status.getStatusName().equalsIgnoreCase(param) || status.getStatusName().contains(param)){
                return requestSubmittedRepos.getRequestSubmittedByStatus(status);
            }
        }
        if(param.equals("all")) return requestSubmittedRepos.getRequestSubmittedByStudentId(id);
        if(param.equals("true")) return requestSubmittedRepos.findAllByIsCanceledEquals(true);
        if(param.equals("false")) return requestSubmittedRepos.findAllByIsCanceledEquals(false);
        return null;
    }

    @PostMapping("/save")
    public ResponseEntity<String> saveRequest(@RequestBody RequestSubmitted requestSubmitted){
        try{
            requestSubmittedRepos.save(requestSubmitted);
            return ResponseEntity.ok().body("Успешно");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @RequestMapping("getAtNumber/{name}")
    public Iterable<RequestSubmitted> getAtName(@PathVariable("name") String value){
        if(value.equals("zero")){
            return requestSubmittedRepos.findAll();
        }else{
            return requestSubmittedRepos.getRequestSubmittedById(Integer.parseInt(value));
        }
    }
}
