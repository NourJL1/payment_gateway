package com.service;
import com.model.CARD;
import java.util.List;
import java.util.Optional;

public interface CardService {
	List<CARD> findAll();
    Optional<CARD> findById(Integer id);
    CARD save(CARD card);
    void deleteById(Integer id);
    List<CARD> searchCards(String searchWord);

}
