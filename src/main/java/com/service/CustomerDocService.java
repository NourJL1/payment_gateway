package com.service;

import com.model.CUSTOMER_DOC;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CustomerDocService {

	List<CUSTOMER_DOC> findAll();

	Optional<CUSTOMER_DOC> findById(Integer id);

	ResponseEntity<?> findFileById(Integer id);

	CUSTOMER_DOC save(CUSTOMER_DOC customerDoc , MultipartFile file );

	void deleteById(Integer id);

	List<CUSTOMER_DOC> searchCustomerDocs(String searchWord);

    ResponseEntity<?> findByCustomerDocListe(Integer cdlCode);

}
