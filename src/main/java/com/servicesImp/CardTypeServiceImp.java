package com.servicesImp;
import com.model.CARD_TYPE;
import com.repository.CardTypeRepository;
import com.service.CardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CardTypeServiceImp implements CardTypeService {

    @Autowired
    private CardTypeRepository cardTypeRepository;

	@Override
	public List<CARD_TYPE> findAll() {
		 return cardTypeRepository.findAll();
	}

	@Override
	public Optional<CARD_TYPE> findById(Integer id) {
		return cardTypeRepository.findById(id);
	}

	@Override
	public CARD_TYPE save(CARD_TYPE cardType) {
		 return cardTypeRepository.save(cardType);
	}

	@Override
	public void deleteById(Integer id) {
		   cardTypeRepository.deleteById(id);		
	}

   @Override
	    public List<CARD_TYPE> searchCardTypes(String searchWord) {
	        if (searchWord == null || searchWord.trim().isEmpty()) {
	            return cardTypeRepository.findAll();
	        }
	        return cardTypeRepository.searchCardTypes(searchWord);
	    }

}
