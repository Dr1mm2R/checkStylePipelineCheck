package com.example.apisearchpracticebase.Repositories;

import com.example.apisearchpracticebase.Models.CollegePracticeManager;
import com.example.apisearchpracticebase.Models.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollegePracticeManagerRepos extends CrudRepository<CollegePracticeManager, Long> {
    Optional<CollegePracticeManager> findByCollegeManagerLogin(String login);
}