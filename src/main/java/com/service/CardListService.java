package com.service;

import com.model.CARD_LIST;
import java.util.List;
import java.util.Optional;
public interface CardListService {
	CARD_LIST createCardList(CARD_LIST cardList);
    List<CARD_LIST> getAllCardLists();
    Optional<CARD_LIST> getCardListById(Integer cliCode);
    CARD_LIST updateCardList(Integer cliCode, CARD_LIST cardList);
    void deleteCardList(Integer cliCode);
    Optional<CARD_LIST> findById(Integer cliCode);

    }


