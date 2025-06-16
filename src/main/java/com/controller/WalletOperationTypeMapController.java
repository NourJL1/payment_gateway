package com.controller;
import com.model.*;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/wallet-operation-type-map")

public class WalletOperationTypeMapController {
	
	@Autowired
    private WalletOperationTypeMapService service;

	 @PostMapping
	    public ResponseEntity<WALLET_OPERATION_TYPE_MAP> create(@RequestBody WALLET_OPERATION_TYPE_MAP walletOperationTypeMap) {
	    	WALLET_OPERATION_TYPE_MAP created = service.create(walletOperationTypeMap);
	        return new ResponseEntity<>(created, HttpStatus.CREATED);
	    }

    @PutMapping("/{id}")
    public WALLET_OPERATION_TYPE_MAP update(@PathVariable Integer id, @RequestBody WALLET_OPERATION_TYPE_MAP walletOperationTypeMap) {
        return service.update(id, walletOperationTypeMap);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public WALLET_OPERATION_TYPE_MAP getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping
    public List<WALLET_OPERATION_TYPE_MAP> getAll() {
        return service.getAll();
    }

    @GetMapping("/search")
    public ResponseEntity<List<WALLET_OPERATION_TYPE_MAP>> searchWalletOperationTypeMaps(@RequestParam("word") String searchWord) {
        List<WALLET_OPERATION_TYPE_MAP> walletOperationTypeMaps = service.searchWalletOperationTypeMaps(searchWord);
        return ResponseEntity.ok(walletOperationTypeMaps);
    }
}
