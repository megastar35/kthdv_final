package com.fsoft.fa.interviewprocessmanagement.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "candidate_id")
    private int id;

    @Column(name = "candidate_name")
    private String name;

    @Column
    private String address;

    @Column
    private String email;

    @Column(name = "phone_number")
    private long phoneNumber;

    @ElementCollection
    @JoinTable(joinColumns = @JoinColumn(name = "candidate_id"))
    private Set<String> certificates;

    @ElementCollection
    @JoinTable(joinColumns = @JoinColumn(name = "candidate_id"))
    private Set<String> experience;

    @Column(name = "day_of_birth", columnDefinition = "TIMESTAMP")
    private LocalDate dayOfBirth;

    @Column
    private boolean suitable = false;

    @Column
    private boolean potential = false;

    @Column
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id"))
    private Set<Position> positions;

    @Column
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "entry_test_id"))
    private Set<EntryTest> entryTests = new HashSet<>();

    @OneToOne(mappedBy = "candidate", fetch = FetchType.LAZY)
    @JoinColumn(name = "interview_id")
    private transient Interview interview;

    @ElementCollection
    @JoinTable(joinColumns = @JoinColumn(name = "candidate_id"))
    private Set<Double> testScores = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private UploadFile curriculumVitae;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private UploadFile image;

    public UploadFile getImage() {
        return image;
    }

    public void setImage(UploadFile image) {
        this.image = image;
    }

    public Candidate() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void enrollInterview(Interview interview) {
        interview.setCandidate(this);
        setInterview(interview);
    }

    public Interview getInterview() {
        return interview;
    }

    public void setInterview(Interview interview) {
        this.interview = interview;
    }

    public UploadFile getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(UploadFile curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    public boolean isSuitable() {
        return suitable;
    }

    public void setSuitable(boolean suitable) {
        this.suitable = suitable;
    }

    public Set<Double> getTestScores() {
        return testScores;
    }

    public void setTestScores(Set<Double> testScores) {
        this.testScores = testScores;
    }

    public Set<EntryTest> getEntryTests() {
        return entryTests;
    }

    public void setEntryTests(Set<EntryTest> entryTests) {
        this.entryTests = entryTests;
    }

    public boolean isPotential() {
        return potential;
    }

    public void setPotential(boolean potential) {
        this.potential = potential;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<String> getCertificates() {
        return certificates;
    }

    public void setCertificates(Set<String> certificates) {
        this.certificates = certificates;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    public Set<String> getExperience() {
        return experience;
    }

    public void setExperience(Set<String> experience) {
        this.experience = experience;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Recruitment getRecruitment() {
        return recruitment;
    }

    public void setRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
    }

}