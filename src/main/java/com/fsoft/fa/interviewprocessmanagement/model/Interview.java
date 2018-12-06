package com.fsoft.fa.interviewprocessmanagement.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Interview {

    @Id
    @Column(name = "interview_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne
    @JoinColumn(name = "interviewer_id")
    private User interviewer;

    @OneToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidate;

    @Column(name = "interview_date")
    private LocalDate interviewDate;

    @Column(name = "interview_description")
    private String description;

    @Column
    private double interviewScore;

    public Interview() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getInterviewScore() {
        return interviewScore;
    }

    public void setInterviewScore(double interviewScore) {
        this.interviewScore = interviewScore;
    }

    public LocalDate getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(LocalDate interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(User interviewer) {
        this.interviewer = interviewer;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
}
