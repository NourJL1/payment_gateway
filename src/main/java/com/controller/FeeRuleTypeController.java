package com.controller;
import com.model.FEE_RULE_TYPE;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fee-rule-types")

public class FeeRuleTypeController {
	@Autowired
    private FeeRuleTypeService feeRuleTypeService;

    @GetMapping
    public List<FEE_RULE_TYPE> getAllFeeRuleTypes() {
        return feeRuleTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FEE_RULE_TYPE> getFeeRuleTypeById(@PathVariable Integer id) {
        Optional<FEE_RULE_TYPE> feeRuleType = feeRuleTypeService.findById(id);
        return feeRuleType.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FEE_RULE_TYPE> createFeeRuleType(@RequestBody FEE_RULE_TYPE feeRuleType) {
        FEE_RULE_TYPE createdFeeRuleType = feeRuleTypeService.save(feeRuleType);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFeeRuleType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FEE_RULE_TYPE> updateFeeRuleType(@PathVariable Integer id, @RequestBody FEE_RULE_TYPE feeRuleType) {
        Optional<FEE_RULE_TYPE> existingFeeRuleType = feeRuleTypeService.findById(id);

        if (!existingFeeRuleType.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Met à jour uniquement les champs nécessaires
        FEE_RULE_TYPE feeRuleToUpdate = existingFeeRuleType.get();
        feeRuleToUpdate.setFrtIden(feeRuleType.getFrtIden());
        feeRuleToUpdate.setFrtLabe(feeRuleType.getFrtLabe());

        FEE_RULE_TYPE updatedFeeRuleType = feeRuleTypeService.save(feeRuleToUpdate);
        return ResponseEntity.ok(updatedFeeRuleType);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeeRuleType(@PathVariable Integer id) {
        if (!feeRuleTypeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        feeRuleTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

      @GetMapping("/search")
    public ResponseEntity<List<FEE_RULE_TYPE>> searchFeeRuleTypes(@RequestParam("word") String searchWord) {
        List<FEE_RULE_TYPE> feeRuleTypes = feeRuleTypeService.searchFeeRuleTypes(searchWord);
        return ResponseEntity.ok(feeRuleTypes);
    }

}
