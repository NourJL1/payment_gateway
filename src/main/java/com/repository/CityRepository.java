package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.model.CITY;

public interface CityRepository extends JpaRepository<CITY, Integer> {

}
