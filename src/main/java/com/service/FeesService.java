package com.service;
import com.model.*;
import java.util.List;
import java.util.Optional;


public interface FeesService {
	FEES create(FEES fees);
    FEES update(Integer id, FEES fees);
    void delete(Integer id);
    FEES getById(Integer id);
    List<FEES> getAll();

}
