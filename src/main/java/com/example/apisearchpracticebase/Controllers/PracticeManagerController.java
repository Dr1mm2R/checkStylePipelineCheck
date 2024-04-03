package com.example.apisearchpracticebase.Controllers;

import com.example.apisearchpracticebase.Models.PracticeManager;
import com.example.apisearchpracticebase.Repositories.PracticeManagerRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("practice_Manager")
public class PracticeManagerController {
    @Autowired
    PracticeManagerRepos practiceManagerRepos;

    @RequestMapping("/all")
    public Iterable<PracticeManager> getAll(){
        return practiceManagerRepos.findAll();
    }
}
