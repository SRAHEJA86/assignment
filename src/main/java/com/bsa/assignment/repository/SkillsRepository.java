package com.bsa.assignment.repository;

import com.bsa.assignment.model.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepository extends CrudRepository<Skills,Integer> {
   Skills findBySkillName(String skillName);
   void deleteBySkillName(String skillName);
   Skills findBySkillId(Integer skillId);

}
