package com.service;
import com.model.CUSTOMER_DOC;
import java.util.List;
import java.util.Optional;

public interface CustomerDocService {
	
	 List<CUSTOMER_DOC> findAll();
	    Optional<CUSTOMER_DOC> findById(Integer id);
	    CUSTOMER_DOC save(CUSTOMER_DOC customerDoc);
	    void deleteById(Integer id);

}
