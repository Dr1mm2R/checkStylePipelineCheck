package com.example.apisearchpracticebase.Repositories;

import com.example.apisearchpracticebase.Models.CollegePracticeManager;
import com.example.apisearchpracticebase.Models.VisitLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitLogRepos extends CrudRepository<VisitLog, Long> {

    Iterable<VisitLog> findAllByStudentId(int id);
}