package com.service;
import com.model.PERIODICITY;
import java.util.List;
import java.util.Optional;

public interface PeriodicityService {
	 PERIODICITY createPeriodicity(PERIODICITY periodicity);
	    List<PERIODICITY> getAll();
	    Optional<PERIODICITY> getById(Integer id);
	    PERIODICITY update(Integer id, PERIODICITY periodicity);
	    void delete(Integer id);
}
