package com.controller;
import com.model.CARD_TYPE;
import com.service.CardTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/card-types")

public class CardTypeController {
	@Autowired
    private CardTypeService cardTypeService;

    @GetMapping
    public List<CARD_TYPE> getAllCardTypes() {
        return cardTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CARD_TYPE> getCardTypeById(@PathVariable Integer id) {
        Optional<CARD_TYPE> cardType = cardTypeService.findById(id);
        return cardType.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CARD_TYPE> createCardType(@RequestBody CARD_TYPE cardType) {
        CARD_TYPE createdCardType = cardTypeService.save(cardType);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCardType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CARD_TYPE> updateCardType(@PathVariable Integer id, @RequestBody CARD_TYPE cardType) {
        if (!cardTypeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        CARD_TYPE updatedCardType = cardTypeService.save(cardType);
        return ResponseEntity.ok(updatedCardType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardType(@PathVariable Integer id) {
        if (!cardTypeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        cardTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<CARD_TYPE>> searchCardTypes(@RequestParam("word") String searchWord) {
        List<CARD_TYPE> cardTypes = cardTypeService.searchCardTypes(searchWord);
        return ResponseEntity.ok(cardTypes);
    }


}
