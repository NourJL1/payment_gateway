package com.service;

import com.model.CUSTOMER_DOC_LISTE;
import java.util.List;
import java.util.Optional;

public interface CustomerDocListeService {
	List<CUSTOMER_DOC_LISTE> findAll();
    Optional<CUSTOMER_DOC_LISTE> findById(Integer id);
    CUSTOMER_DOC_LISTE save(CUSTOMER_DOC_LISTE customerDocListe);
    void deleteById(Integer id);

}
