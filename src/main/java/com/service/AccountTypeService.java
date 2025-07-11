package com.service;
import com.model.ACCOUNT_TYPE;
import java.util.List;

public interface AccountTypeService {
	ACCOUNT_TYPE create(ACCOUNT_TYPE accountType);
    ACCOUNT_TYPE update(Integer id, ACCOUNT_TYPE accountType);
    void delete(Integer id);
    ACCOUNT_TYPE getById(Integer id);
    List<ACCOUNT_TYPE> getAll();
    List<ACCOUNT_TYPE> searchAccountTypes(String searchWord);

}
