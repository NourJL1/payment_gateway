package com.controller;
import com.model.CARD_LIST;
import com.service.CardListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/card-lists")
public class CardListController {
	@Autowired
    private CardListService cardListService;

    @PostMapping
    public ResponseEntity<CARD_LIST> createCardList(@RequestBody CARD_LIST cardList) {
        return ResponseEntity.ok(cardListService.createCardList(cardList));
    }

    @GetMapping
    public ResponseEntity<List<CARD_LIST>> getAllCardLists() {
        return ResponseEntity.ok(cardListService.getAllCardLists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CARD_LIST> getCardListById(@PathVariable Integer id) {
        Optional<CARD_LIST> cardList = cardListService.getCardListById(id);
        return cardList.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CARD_LIST> updateCardList(@PathVariable Integer id, @RequestBody CARD_LIST cardList) {
        return ResponseEntity.ok(cardListService.updateCardList(id, cardList));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCardList(@PathVariable Integer id) {
        cardListService.deleteCardList(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/search")
    public ResponseEntity<List<CARD_LIST>> searchCardLists(@RequestParam("word") String searchWord) {
        List<CARD_LIST> cardLists = cardListService.searchCardLists(searchWord);
        return ResponseEntity.ok(cardLists);
    }
}
