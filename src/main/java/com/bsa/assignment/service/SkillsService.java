package com.bsa.assignment.service;

import com.bsa.assignment.model.Skills;
import com.bsa.assignment.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillsService {
    @Autowired
    SkillsRepository skillsRepository;

    /**
     * Returns list of all skills
     * @return list of skills
     */
    public List<Skills> getAllSkills() {
        return (List<Skills>) skillsRepository.findAll();
    }

    /**
     *Adds a new skill to the repository
     * @param newSkill new skill to be added
     * @return Skill that has been added
     */
    public Skills addSkill(Skills newSkill) {
        return skillsRepository.save(newSkill);
    }

    /**
     * Updates the skill in the repository
     * @param updatedSkill skill to be updated
     * @return Skill that has been updated
     */
    public Skills updateSkill(Integer skillId,Skills updatedSkill) {
        return skillsRepository.save(updatedSkill);
    }

    /**
     *Deletes a skill from the repository
     * @param skillId skill to be deleted
     */
    public void deleteSkill(Integer skillId) {
        skillsRepository.deleteById(skillId);
    }

    /**
     * Returns a Skill based on skill id
     * @param skillId skillId to be found
     * @return Skill
     */
    public Skills getSkillBySkillId(Integer skillId) {
        return skillsRepository.findBySkillId(skillId);
    }
}
