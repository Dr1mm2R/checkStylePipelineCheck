package com.example.apisearchpracticebase.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "StudentBaseChangeLog")
public class StudentBaseChangeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Log_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Student_ID", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "Old_Base_ID", nullable = false)
    private PracticeBase oldBase;

    @ManyToOne
    @JoinColumn(name = "New_Base_ID", nullable = false)
    private PracticeBase newBase;

    @ManyToOne
    @JoinColumn(name = "Manager_ID", nullable = false)
    private Contact manager;

    @Column(name = "PracticePlace", nullable = false)
    private String practicePlace;

    @Column(name = "ChangeDateTime", nullable = false)
    private LocalDateTime changeDateTime;

    public StudentBaseChangeLog() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public PracticeBase getOldBase() {
        return oldBase;
    }

    public void setOldBase(PracticeBase oldBase) {
        this.oldBase = oldBase;
    }

    public PracticeBase getNewBase() {
        return newBase;
    }

    public void setNewBase(PracticeBase newBase) {
        this.newBase = newBase;
    }

    public Contact getManager() {
        return manager;
    }

    public void setManager(Contact manager) {
        this.manager = manager;
    }

    public String getPracticePlace() {
        return practicePlace;
    }

    public void setPracticePlace(String practicePlace) {
        this.practicePlace = practicePlace;
    }

    public LocalDateTime getChangeDateTime() {
        return changeDateTime;
    }

    public void setChangeDateTime(LocalDateTime changeDateTime) {
        this.changeDateTime = changeDateTime;
    }

    public StudentBaseChangeLog(Long id, Student student, PracticeBase oldBase, PracticeBase newBase, Contact manager, String practicePlace, LocalDateTime changeDateTime) {
        this.id = id;
        this.student = student;
        this.oldBase = oldBase;
        this.newBase = newBase;
        this.manager = manager;
        this.practicePlace = practicePlace;
        this.changeDateTime = changeDateTime;
    }
}
