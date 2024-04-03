package com.example.apisearchpracticebase.Controllers;

import com.example.apisearchpracticebase.Models.CollegePracticeManager;
import com.example.apisearchpracticebase.Repositories.CollegePracticeManagerRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("collegePM")
public class CollegePMController {
    @Autowired
    CollegePracticeManagerRepos collegePracticeManagerRepos;

    @RequestMapping("/all")
    public Iterable<CollegePracticeManager> getAll(){
        return collegePracticeManagerRepos.findAll();
    }
}
