package com.bsa.assignment.resource;

import com.bsa.assignment.model.Skills;
import com.bsa.assignment.service.SkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/skills")
public class SkillsResource {

    @Autowired
    SkillsService skillsService;

    /**
     *Gets the list of all skills
     * @return
     */
    @GetMapping
    public List<Skills> getAllSkills() { return skillsService.getAllSkills();};

    /**
     * Gets a skill based on skill id
     * @param skillId
     * @return
     */
    @GetMapping("{skillId}")
    public Optional<Skills> getSkillBySkillId(@PathVariable("skillId") Integer skillId){
        return skillsService.getSkillBySkillId(skillId);
    }

    /**
     *Creates a skill in the repository
     * @param newSkill
     * @return
     */
    @PostMapping("/skill")
    public Skills addSkill(@RequestBody Skills newSkill){
        return skillsService.addSkill(newSkill);
    }

    /**
     *Updates a skill
     * @param updatedSkill
     * @return
     */
    @PutMapping("/skill")
    public Skills updateSkill(@RequestBody Skills updatedSkill){
        return skillsService.updateSkill(updatedSkill);
    }

    /**
     * Deletes a skill from the repository
     * @param deleteSkill
     * @return
     */
    @DeleteMapping
    public void deleteSkill(@RequestBody Skills deleteSkill){
        skillsService.deleteSkill(deleteSkill);
    }


}
