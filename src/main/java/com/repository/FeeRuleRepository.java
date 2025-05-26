package com.repository;
import com.model.FEE_RULE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FeeRuleRepository extends JpaRepository <FEE_RULE, Integer>{

}
