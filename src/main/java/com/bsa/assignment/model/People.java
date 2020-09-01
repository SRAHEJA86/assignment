package com.bsa.assignment.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class People {
    protected People(){
    }

    public People(Integer personId,String personName,List<Skills> skills){
        super();
        this.personId = personId;
        this.skills = skills;
        this.personName = personName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer personId;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    private String personName;

    @ManyToMany(fetch=FetchType.LAZY)
    @NotFound(
            action = NotFoundAction.IGNORE)
    @JoinTable(name = "PEOPLE_SKILLS",joinColumns = @JoinColumn (name="PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name="SKILL_ID"))
    private List<Skills> skills = new ArrayList<>();

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public List<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "People [id=" + personId + ", name=" + personName + ", skills=" + skills + "]";
    }
}
