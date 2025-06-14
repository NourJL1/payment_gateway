package com.servicesImp;
import com.model.CARD_LIST;
import com.repository.CardListRepository;
import com.service.CardListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CardListServiceImp implements CardListService {

	@Autowired
    private CardListRepository cardListRepository;

	@Override
    public CARD_LIST createCardList(CARD_LIST cardList) {
        // Vérifier que cliIden n'est pas nul avant de créer
        if (cardList.getCliIden() == null) {
            throw new IllegalArgumentException("cliIden ne peut pas être nul lors de la création.");
        }
        
       
        return cardListRepository.save(cardList);
    }


    @Override
    public List<CARD_LIST> getAllCardLists() {
        return cardListRepository.findAll();
    }

    @Override
    public Optional<CARD_LIST> getCardListById(Integer cliCode) {
        return cardListRepository.findById(cliCode);
    }

    @Override
    public CARD_LIST updateCardList(Integer cliCode, CARD_LIST cardList) {
        return cardListRepository.findById(cliCode)
                .map(existingCardList -> {
                    existingCardList.setCliIden(cardList.getCliIden());
                    existingCardList.setCliLabe(cardList.getCliLabe());
                    return cardListRepository.save(existingCardList);
                })
                .orElseThrow(() -> new RuntimeException("CardList not found"));
    }

    @Override
    public void deleteCardList(Integer cliCode) {
        cardListRepository.deleteById(cliCode);
    }


	@Override
	public Optional<CARD_LIST> findById(Integer cliCode) {
        return cardListRepository.findById(cliCode);

	}
  @Override
    public List<CARD_LIST> searchCardLists(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return cardListRepository.findAll();
        }
        return cardListRepository.searchCardLists(searchWord);
    }

	
}
