package com.bsa.assignment.service;

import com.bsa.assignment.enums.SkillLevel;
import com.bsa.assignment.model.People;
import com.bsa.assignment.model.Skills;
import com.bsa.assignment.repository.PeopleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PeopleServiceTest {
    @Autowired
    PeopleRepository peopleRepository;

    People savedPeople;

    List<Skills> skills;

    @BeforeEach
    void setUp(){
        skills =  Arrays.asList(new Skills(456,"Java", SkillLevel.WORKING),
                new Skills(457,"C++", SkillLevel.AWARENESS));
        People people = new People(123,"Sadhana",skills);
        savedPeople = peopleRepository.save(people);
    }

    @Test
    void getAllPeople() {
        List<People> savedPeople = (List<People>) peopleRepository.findAll();
        Assert.isTrue(savedPeople.size() > 0 , "The people list is not empty");
    }

    @Test
    void when_a_person_is_updated_it_updates_people_and_any_related_fields() {
        String personName = "Sadhana";
        People savedPeople = peopleRepository.findByPersonName(personName);
        assertEquals(savedPeople.getPersonName(), personName);
    }

    @Test
    void when_a_person_is_added_it_adds_the_person_and_the_skills() {
        Assert.notNull(savedPeople,"The people saved is not null");
    }

    @Test
    void when_a_person_is_deleted_it_deletes_the_people_and_associated_skills() {
        String personName = "Sadhana";
        Assert.notNull(savedPeople,"People saved not null before delete");
        peopleRepository.deleteById(savedPeople.getPersonId());
        People peopleSaved = peopleRepository.findByPersonName(personName);
        Assert.isNull(peopleSaved,"The saved people is null");

    }

    @Test
    void when_personId_and_skill_id_are_passed_it_returns_skill() {
        People retrievedPeople = peopleRepository.findByPersonId(savedPeople.getPersonId());
        Integer skillId = retrievedPeople.getSkills().get(0).getSkillId();
        assertEquals(skillId,skills.get(0).getSkillId());
    }

    @Test
    void when_personId_is_passed_it_returns_listOfSkills() {
        People retrievedPeople = peopleRepository.findByPersonId(savedPeople.getPersonId());
        Assert.isTrue(retrievedPeople.getSkills().size() > 0 , "The skillsList is retrieved");
    }
}