package com.example.apisearchpracticebase.Controllers;

import com.example.apisearchpracticebase.Models.Student;
import com.example.apisearchpracticebase.Models.VisitLog;
import com.example.apisearchpracticebase.Repositories.VisitLogRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("visitLog")
public class VisitLogController {
    @Autowired
    VisitLogRepos visitLogRepos;

    @RequestMapping("/get")
    public Iterable<VisitLog> getLogsAtId(@RequestParam("id") int id)
    {
        return visitLogRepos.findAllByStudentId(id);
    }

    @PostMapping("/save")
    public ResponseEntity<VisitLog> saveVisitLog(@RequestBody VisitLog visitLog){
        try{
            visitLogRepos.save(visitLog);
            return ResponseEntity.ok(visitLog);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
