package com.fsoft.fa.interviewprocessmanagement.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Recruitment {

    @Id
    @Column(name = "recruitment_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "finish_date")
    private LocalDate finishDate;

    @Column(name = "number_of_recruit")
    private int numberOfRecruits;

    @OneToMany(mappedBy = "recruitment")
    private Set<Candidate> candidates;

    @Column(name = "recruitment_description")
    private String description;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "recruitment_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id"))
    private Set<Position> positions;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "recruitment_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "recruitment_id"),
            inverseJoinColumns = @JoinColumn(name = "major_id"))
    private Set<Major> majors;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private UploadFile image;

    public Recruitment() {
    }

    public UploadFile getImage() {
        return image;
    }

    public void setImage(UploadFile image) {
        this.image = image;
    }

    public void addCandidate(Candidate candidate) {
        candidate.setRecruitment(this);
        candidates.add(candidate);
    }

    public void removeCandidate(Candidate candidate) {
        candidate.setRecruitment(null);
        candidates.remove(candidate);
    }

    public Set<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(Set<Candidate> candidates) {
        this.candidates = candidates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public int getNumberOfRecruits() {
        return numberOfRecruits;
    }

    public void setNumberOfRecruits(int numberOfRecruits) {
        this.numberOfRecruits = numberOfRecruits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<Major> getMajors() {
        return majors;
    }

    public void setMajors(Set<Major> majors) {
        this.majors = majors;
    }

}
