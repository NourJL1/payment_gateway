package com.service;
import com.model.CARD_TYPE;
import java.util.List;
import java.util.Optional;

public interface CardTypeService {
	List<CARD_TYPE> findAll();
    Optional<CARD_TYPE> findById(Integer id);
    CARD_TYPE save(CARD_TYPE cardType);
    void deleteById(Integer id);
    List<CARD_TYPE> searchCardTypes(String searchWord);
}
