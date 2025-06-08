package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.model.COUNTRY;

public interface CountryRepository extends JpaRepository<COUNTRY, Integer>{
	
	public boolean existsByCtrLabe (String ctrLabe);

}
