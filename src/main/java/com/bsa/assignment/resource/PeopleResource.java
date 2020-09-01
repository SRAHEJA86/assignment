package com.bsa.assignment.resource;

import com.bsa.assignment.model.People;
import com.bsa.assignment.model.Skills;
import com.bsa.assignment.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PeopleResource {

    @Autowired
    PeopleService peopleService;

    /**
     *Returns list of people
     * @return List of People
     */
    @GetMapping("/people")
    public List<People> getAllPeople(){
        return peopleService.getAllPeople();
    }

    /**
     * Returns People based on personId
     * @param personId person Id
     * @return People
     */
    @GetMapping("/people/{personId}")
    public People getPeopleById(@PathVariable Integer personId){
        return peopleService.getPeopleById(personId);
    }

    /**
     *Returns list of skills associated with a person
     * @param personId person Id
     * @return list of skills
     */
    @GetMapping("/people/{personId}/skills")
    public List<Skills> getSkillsForPeople(@PathVariable Integer personId){
        return peopleService.getSkillsForPeople(personId);
    }

    /**
     *Return Skill
     * @param personId person Id
     * @param skillId skill Id
     * @return returns a specific associated with a person
     */
    @GetMapping("/people/{personId}/skills/{skillId}")
    public Skills getDetailsForSkills(@PathVariable Integer personId,
                                      @PathVariable Integer skillId) {
        return peopleService.getDetailsForASkill(personId, skillId);
    }


    /**
     *Updates people in the repository
     * @param updatedPeople people to be updated
     * @return Updated people
     */
    @PutMapping("/people/{personId}")
    public People updatePeople(@PathVariable Integer personId,
                               @RequestBody People updatedPeople){
        return peopleService.updatePeople(personId,updatedPeople);
    }

    /**
     *Adds people to the repository
     * @param newPeople new people to be added
     * @return People added to the repository
     */
    @PostMapping("/people")
    public People addPeople(@RequestBody People newPeople){
        System.out.print("I am being called");
        return peopleService.addPeople(newPeople);
    }

    /**
     *Deletes people from the repository
     * @param personId people to be deleted
     */
   @DeleteMapping("/people/{personId}")
    public void deletePeople(@PathVariable Integer personId){
        peopleService.deletePeople(personId);
   }


    /**
     *
     * @param personId person Id
     * @param newSkills new skills to be added
     * @return ResponseEntity
     */

    @PostMapping("/people/{personId}/skills")
    public ResponseEntity<Void> registerSkillsForPeople(
            @PathVariable String personId, @RequestBody Skills newSkills) {

        Skills skills = peopleService.addSkillsForAPerson(personId, newSkills);

        if (skills == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/people/{personId}/skills").buildAndExpand(skills.getSkillId()).toUri();

        return ResponseEntity.created(location).build();
    }
}
