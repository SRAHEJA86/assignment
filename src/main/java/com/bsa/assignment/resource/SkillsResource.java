package com.bsa.assignment.resource;

import com.bsa.assignment.model.Skills;
import com.bsa.assignment.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SkillsResource {

    @Autowired
    SkillsService skillsService;

    /**
     *Gets the list of all skills
     * @return returns list of skills
     */
    @GetMapping("/skills")
    public List<Skills> getAllSkills() { return skillsService.getAllSkills();}

    /**
     * Gets a skill based on skill id
     * @param skillId skillId
     * @return skills
     */
    @GetMapping("/skills/{skillId}")
    public Skills getSkillBySkillId(@PathVariable("skillId") Integer skillId){
        return skillsService.getSkillBySkillId(skillId);
    }

    /**
     *Creates a skill in the repository
     * @param newSkill new skill to be added
     * @return skills added to the repo
     */
    @PostMapping("/skills")
    public Skills addSkill(@RequestBody Skills newSkill){
        return skillsService.addSkill(newSkill);
    }

    /**
     *Updates a skill
     * @param updatedSkill skill to be updated
     * @return skills that has been updated
     */
    @PutMapping("/skills/{skillId}")
    public Skills updateSkill(@PathVariable Integer skillId,@RequestBody Skills updatedSkill){
        return skillsService.updateSkill(skillId,updatedSkill);
    }

    /**
     * Deletes a skill from the repository
     * @param skillId skill Id to be deleted
     */
    @DeleteMapping("/skills/{skillId}")
    public void deleteSkill(@PathVariable Integer skillId){
        skillsService.deleteSkill(skillId);
    }


}
