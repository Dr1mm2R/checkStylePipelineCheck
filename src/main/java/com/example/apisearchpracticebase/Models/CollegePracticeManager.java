package com.example.apisearchpracticebase.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "CollegePracticeManager")
public class CollegePracticeManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CollegeManager_ID")
    private Long id;

    @Column(name = "PostManager", nullable = false)
    private String postManager;

    @ManyToOne
    @JoinColumn(name = "Contact_ID", nullable = false)
    private Contact contact;

    @Column(name = "College_Manager_Login", nullable = false, unique = true)
    private String collegeManagerLogin;

    @Column(name = "College_Manager_Password", nullable = false)
    private String collegeManagerPassword;

    public CollegePracticeManager(Long id, String postManager, Contact contact, String login, String password) {
        this.id = id;
        this.postManager = postManager;
        this.contact = contact;
        this.collegeManagerLogin = login;
        this.collegeManagerPassword = password;
    }

    public CollegePracticeManager() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostManager() {
        return postManager;
    }

    public void setPostManager(String postManager) {
        this.postManager = postManager;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getCollegeManagerLogin() {
        return collegeManagerLogin;
    }

    public void setCollegeManagerLogin(String collegeManagerLogin) {
        this.collegeManagerLogin = collegeManagerLogin;
    }

    public String getCollegeManagerPassword() {
        return collegeManagerPassword;
    }

    public void setCollegeManagerPassword(String collegeManagerPassword) {
        this.collegeManagerPassword = collegeManagerPassword;
    }
}
