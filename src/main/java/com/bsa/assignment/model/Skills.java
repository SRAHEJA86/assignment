package com.bsa.assignment.model;

import com.bsa.assignment.enums.SkillLevel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer skillId;

    private String skillName;

    public SkillLevel getSkillLevel() {
        return skillLevel;
    }

    @ManyToMany
    List<People> people = new ArrayList<People>();

    public void setSkillLevel(SkillLevel skillLevel) {
        this.skillLevel = skillLevel;
    }

    @Enumerated(EnumType.STRING)
    private SkillLevel skillLevel;
    protected Skills(){

    }

    public Skills(Integer skillId,String skillName,SkillLevel skillLevel){
        super();
        this.skillId = skillId;
        this.skillName = skillName;
        this.skillLevel = skillLevel;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }


}
