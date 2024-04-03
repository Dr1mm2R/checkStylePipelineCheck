package com.example.apisearchpracticebase.Repositories;

import com.example.apisearchpracticebase.Models.CollegePracticeManager;
import com.example.apisearchpracticebase.Models.StudentBaseChangeLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentBaseChangeLogRepos extends CrudRepository<StudentBaseChangeLog, Long> {
}