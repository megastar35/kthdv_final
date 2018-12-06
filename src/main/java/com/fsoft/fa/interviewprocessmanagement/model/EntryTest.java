package com.fsoft.fa.interviewprocessmanagement.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class EntryTest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "entry_test_id")
    private int id;

    @Column(name = "entry_test_name")
    private String name;

    @Column(name = "content")
    private String content;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "entry_test_id"),
            inverseJoinColumns = @JoinColumn(name = "position_id"))
    private Set<Position> positions;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "entry_test_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills;

    public EntryTest() {
    }

    public EntryTest(int id, String name, String content, Set<Position> positions, Set<Skill> skills) {
        super();
        this.id = id;
        this.name = name;
        this.content = content;
        this.positions = positions;
        this.skills = skills;
    }

    public EntryTest(String name, String content, Set<Position> positions, Set<Skill> skills) {
        super();
        this.name = name;
        this.content = content;
        this.positions = positions;
        this.skills = skills;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "EntryTest [name=" + name + ", content=" + content + ", position=" + positions
                + ", skill=" + skills + "]";
    }
}
