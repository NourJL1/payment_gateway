package com.controller;
import com.model.WALLET_TYPE;
import com.service.WalletTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wallet-types")
public class WalletTypeController {
    @Autowired
    private WalletTypeService walletTypeService;

    @PostMapping
    public ResponseEntity<WALLET_TYPE> createWalletType(@RequestBody WALLET_TYPE walletType) {
        return ResponseEntity.ok(walletTypeService.createWalletType(walletType));
    }

    @GetMapping
    public ResponseEntity<List<WALLET_TYPE>> getAllWalletTypes() {
        return ResponseEntity.ok(walletTypeService.getAllWalletTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WALLET_TYPE> getWalletTypeById(@PathVariable Integer id) {
        Optional<WALLET_TYPE> walletType = walletTypeService.getWalletTypeById(id);
        return walletType.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WALLET_TYPE> updateWalletType(@PathVariable Integer id, @RequestBody WALLET_TYPE walletType) {
        return ResponseEntity.ok(walletTypeService.updateWalletType(id, walletType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWalletType(@PathVariable Integer id) {
        walletTypeService.deleteWalletType(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<WALLET_TYPE>> searchWalletTypes(@RequestParam("word") String searchWord) {
        List<WALLET_TYPE> walletTypes = walletTypeService.searchWalletTypes(searchWord);
        return ResponseEntity.ok(walletTypes);
    }
}
