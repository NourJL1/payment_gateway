package com.service;
import com.model.ACCOUNT_LIST;
import java.util.List;

public interface AccountListService {
	 ACCOUNT_LIST create(ACCOUNT_LIST accountList);
	    ACCOUNT_LIST update(Integer id, ACCOUNT_LIST accountList);
	    void delete(Integer id);
	    ACCOUNT_LIST getById(Integer id);
	    List<ACCOUNT_LIST> getAll();
      List<ACCOUNT_LIST> searchAccountLists(String searchWord);

}
