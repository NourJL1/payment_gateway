package com.controller;
import com.model.CARD;
import com.model.CARD_LIST;
import com.model.CARD_TYPE;
import com.service.CardListService;
import com.service.CardService;
import com.service.CardTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cards")

public class CardController {

	@Autowired
    private CardService cardService;
	
	@Autowired CardTypeService cardTypeService;
	@Autowired
    private CardListService cardListService;


    @GetMapping
    public List<CARD> getAllCards() {
        return cardService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CARD> getCardById(@PathVariable Integer id) {
        Optional<CARD> card = cardService.findById(id);
        return card.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CARD> createCard(@RequestBody CARD card) {
        // Vérifier si le CARD_TYPE existe
        Integer cardTypeId = card.getCardType() != null ? card.getCardType().getCtypCode() : null;
        if (cardTypeId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Optional<CARD_TYPE> cardTypeOptional = cardTypeService.findById(cardTypeId);
        if (!cardTypeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // CARD_TYPE non trouvé
        }

        // Vérifier si le CARD_LIST existe via cliCode
        Integer cliCode = card.getCardList() != null ? card.getCardList().getCliCode() : null;
        if (cliCode == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Optional<CARD_LIST> cardListOptional = cardListService.findById(cliCode);
        if (!cardListOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // CARD_LIST non trouvé
        }

        // Injecter les objets récupérés
        card.setCardType(cardTypeOptional.get());
        card.setCardList(cardListOptional.get());

        // Sauvegarder la carte
        CARD createdCard = cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCard);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CARD> updateCard(@PathVariable Integer id, @RequestBody CARD card) {
        if (!cardService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        CARD updatedCard = cardService.save(card);
        return ResponseEntity.ok(updatedCard);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable Integer id) {
        if (!cardService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<CARD>> searchCards(@RequestParam("word") String searchWord) {
        List<CARD> cards = cardService.searchCards(searchWord);
        return ResponseEntity.ok(cards);
    }
}
