package com.service;
import com.model.DOC_TYPE;
import java.util.List;
import java.util.Optional;

public interface DocTypeService {
	List<DOC_TYPE> findAll();
    Optional<DOC_TYPE> findById(Integer id);
    DOC_TYPE save(DOC_TYPE docType);
    void deleteById(Integer id);
    List<DOC_TYPE> searchDocTypes(String searchWord);

}
