package com.servicesImp;

import com.model.CARD;
import com.repository.CardRepository;
import com.service.CardService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImp implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    public List<CARD> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public Optional<CARD> findById(Integer id) {
        return cardRepository.findById(id);
    }

    @Override
    public CARD save(CARD card) {
        // Check for duplicate card number
        if (card.getCarNumb() != null) {
            Optional<CARD> existingCard = cardRepository.findByCarNumb(card.getCarNumb());
            if (existingCard.isPresent()) {
                throw new RuntimeException("Card with number " + card.getCarNumb() + " already exists");
            }
        }
        
        return cardRepository.save(card);
    }

    @Override
    public void deleteById(Integer id) {
        if (!cardRepository.existsById(id)) {
            throw new EntityNotFoundException("La carte avec l'ID " + id + " n'existe pas.");
        }
        try {
            cardRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Impossible de supprimer cette carte car elle est référencée ailleurs.");
        }
    }

    @Override
    public List<CARD> searchCards(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return cardRepository.findAll();
        }
        return cardRepository.searchCards(searchWord);
    }
}