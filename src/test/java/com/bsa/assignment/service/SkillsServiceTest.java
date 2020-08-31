package com.bsa.assignment.service;

import com.bsa.assignment.enums.SkillLevel;
import com.bsa.assignment.model.Skills;
import com.bsa.assignment.repository.SkillsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SkillsServiceTest {
    @Autowired
    SkillsRepository skillsRepository;

    @Test
    void getAllSkills() throws Exception{
        Skills skills = new Skills(457,"Java", SkillLevel.EXPERT);
        skillsRepository.save(skills);
        List<Skills> savedSkills = (List<Skills>) skillsRepository.findAll();
        System.out.print(savedSkills.size());
        Assert.isTrue(savedSkills.size()  > 0,"Skills list is not zero");
    }

    @Test
    void addSkill() throws Exception{
        Skills skills = new Skills(458,"Java", SkillLevel.EXPERT);
        Skills savedSkill =skillsRepository.save(skills);
        Assert.notNull(savedSkill,"The saved skill is not null");

    }

    @Test
    void updateSkill() throws Exception{
        String skillName = "Java";
        Skills skills = new Skills(123,"Java", SkillLevel.EXPERT);
        skillsRepository.save(skills);
        Skills updatedSkills = skillsRepository.findBySkillName(skillName);
        assertEquals(skillName, updatedSkills.getSkillName());
    }

    @Test
    void deleteSkill() throws Exception{
        Skills skills = new Skills(456,"Java/J2EE", SkillLevel.EXPERT);
        skillsRepository.save(skills);
        String skillName = "Java/J2EE";
        Skills skillBeforeDelete = skillsRepository.findBySkillName(skillName);
        skillsRepository.deleteBySkillName(skillName);
        Skills skillAfterDelete = skillsRepository.findBySkillName(skillName);
        Assert.notNull(skillBeforeDelete,"The skill exists before delete");
        Assert.isNull(skillAfterDelete,"The skill is null after delete");
    }
}