package com.bsa.assignment.repository;

import com.bsa.assignment.model.People;
import com.bsa.assignment.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends CrudRepository<People,Integer> {
    public People findByPersonName(String personName);
    public People findByPersonId(Integer personId);
}
