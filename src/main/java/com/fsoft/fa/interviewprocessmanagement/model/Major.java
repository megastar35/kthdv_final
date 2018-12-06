/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fsoft.fa.interviewprocessmanagement.model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Major {

    @Expose
    @Id
    @Column(name = "major_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Expose
    @Column(name = "major_name")
    private String name;

    @Expose
    @Column(name = "major_description")
    private String description;

    @OneToMany(mappedBy = "major")
    private Set<Position> positions = new HashSet<>();

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "major", orphanRemoval = true)
//    private Set<Skill> skills = new HashSet<>();

    public Major() {
    }

    public void addPosition(Position position) {
        position.setMajor(this);
        positions.add(position);
    }

    public void removePosition(Position position) {
        positions.remove(position);
        if (position != null) {
            position.setMajor(null);
        }
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPositions(Set<Position> positions) {
        this.positions = positions;
    }

    public Set<Position> getPositions() {
        return positions;
    }

//    public Set<Skill> getSkills() {
//        return skills;
//    }
//
//    public void setSkills(Set<Skill> skills) {
//        this.skills = skills;
//    }
//
//    public void addSkill(Skill skill) {
//        skill.setMajor(this);
//        skills.add(skill);
//    }
//
//    public void removeSkill(Skill skill) {
//        skill.setMajor(null);
//        skills.remove(skill);
//    }
}
