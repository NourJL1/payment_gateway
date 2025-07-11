package com.controller;
import com.model.WALLET_CATEGORY_OPERATION_TYPE_MAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.service.*;

import java.util.List;

@RestController
@RequestMapping("/api/wallet-category-operation-type-map")
public class WalletCategoryOperationTypeMapController {
	 @Autowired
	    private WalletCategoryOperationTypeMapService service;

	    @GetMapping
	    public List<WALLET_CATEGORY_OPERATION_TYPE_MAP> getAll() {
	        return service.getAll();
	    }

	    @GetMapping("/{id}")
	    public WALLET_CATEGORY_OPERATION_TYPE_MAP getById(@PathVariable Integer id) {
	        return service.getById(id);
	    }

	    @PostMapping
	    public WALLET_CATEGORY_OPERATION_TYPE_MAP create(@RequestBody WALLET_CATEGORY_OPERATION_TYPE_MAP mapping) {
	        return service.create(mapping);
	    }

	    @PutMapping("/{id}")
	    public WALLET_CATEGORY_OPERATION_TYPE_MAP update(@PathVariable Integer id, @RequestBody WALLET_CATEGORY_OPERATION_TYPE_MAP mapping) {
	        return service.update(id, mapping);
	    }

	    @DeleteMapping("/{id}")
	    public void delete(@PathVariable Integer id) {
	        service.delete(id);
	    }

      @GetMapping("/search")
	    public ResponseEntity<List<WALLET_CATEGORY_OPERATION_TYPE_MAP>> searchWalletCategoryOperationTypeMaps(@RequestParam("word") String searchWord) {
	        List<WALLET_CATEGORY_OPERATION_TYPE_MAP> walletCategoryOperationTypeMaps = service.searchWalletCategoryOperationTypeMaps(searchWord);
	        return ResponseEntity.ok(walletCategoryOperationTypeMaps);
	    }
}
