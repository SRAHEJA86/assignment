package com.bsa.assignment.service;

import com.bsa.assignment.model.People;
import com.bsa.assignment.model.Skills;
import com.bsa.assignment.repository.PeopleRepository;
import com.bsa.assignment.repository.SkillsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PeopleService {

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    SkillsRepository skillsRepository;

    List<People> peopleList ;

    /**
     * Returns list of all people
     * @return list of people in th repos
     */
    public List<People> getAllPeople() {
        peopleList = (List<People>) peopleRepository.findAll();
        return peopleList;
    }

    /**
     * Updates people in the repository
     * @param updatedPeople people
     * @return the updated people
     */
    public People updatePeople(Integer personId,People updatedPeople) {
        return peopleRepository.save(updatedPeople);
    }

    /**
     * Adds people to the repository
     * @param newPeople newPeople
     * @return People added to the repository
     */
    public People addPeople(People newPeople) {
        return peopleRepository.save(newPeople);
    }

    /**
     * Deletes people from the repository
     * @param people people to be deleted
     */
    public void deletePeople(Integer people) {
        peopleRepository.deleteById(people);
    }

    /**
     * Retrieves skills for people
     * @param personId personId
     * @return returns skills recorded for people
     */
    public List<Skills> retrieveSkillsForPeople(Integer personId) {
        Optional<People> people = retrievePeople(personId);

        if (people == null) {
            return null;
        }
        return peopleRepository.findByPersonId(personId).getSkills();
    }

    /**
     *
     * @param personId
     * @return
     */
    public Optional<People> retrievePeople(Integer personId) {
        return peopleRepository.findById(personId);
    }

    /**
     * Returns skill for a person
     * @param personId personId
     * @param skillId skillId
     * @return skills
     */
    public Skills retrieveSkill(Integer personId, Integer skillId) {
        Optional<People> people = retrievePeople(personId);

        if (people == null) {
            return null;
        }

        List<Skills> skills = peopleRepository.findByPersonId(personId).getSkills();
        List<Skills> matchedSkill = skills.stream().filter
                (skill -> skill.getSkillId().equals(skillId)).collect(Collectors.toList());
        //Picked first element since one skill Id will only have one skill associated
        return matchedSkill.get(0);
    }

    /**
     *
     * @param personId
     * @param newSkills
     * @return
     */
    public Skills addSkillsForAPerson(String personId, Skills newSkills) {
        return null;
    }

    /**
     * Return the people by Id
     * @param personId personId
     * @return people based on Id
     */
    public People getPeopleById(Integer personId) {
        return peopleRepository.findByPersonId(personId);
    }
}
