package com.service;
import com.model.*;
import java.util.List;


public interface FeesService {
	FEES create(FEES fees);
    FEES update(Integer id, FEES fees);
    void delete(Integer id);
    FEES getById(Integer id);
    List<FEES> getAll();
    List<FEES> searchFees(String searchWord);

}
