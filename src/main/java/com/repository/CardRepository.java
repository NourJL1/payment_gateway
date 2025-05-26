package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.model.CARD;

public interface CardRepository extends JpaRepository<CARD, Integer> {

}
