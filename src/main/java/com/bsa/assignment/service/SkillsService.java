package com.bsa.assignment.service;

import com.bsa.assignment.model.Skills;
import com.bsa.assignment.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Skills updateSkill(Skills updatedSkill) {
        return skillsRepository.save(updatedSkill);
    }

    /**
     *Deletes a skill from the repository
     * @param deleteSkill skill to be deleted
     */
    public void deleteSkill(Skills deleteSkill) {
        skillsRepository.delete(deleteSkill);
    }

    /**
     * Returns a Skill based on skill id
     * @param skillId skillId to be found
     * @return Skill
     */
    public Optional<Skills> getSkillBySkillId(Integer skillId) {
        return skillsRepository.findById(skillId);
    }
}
