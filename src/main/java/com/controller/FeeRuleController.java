package com.controller;

import com.model.FEE_RULE;
import com.model.FEE_RULE_TYPE;
import com.model.FEE_SCHEMA;
import com.model.VatRate;
import com.service.FeeRuleService;
import com.service.FeeRuleTypeService;
import com.service.FeeSchemaService;
import com.service.VatRateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fee-rule")
public class FeeRuleController {

    @Autowired
    private FeeRuleService feeRuleService;

    @Autowired
    private FeeRuleTypeService feeRuleTypeService;

    @Autowired
    private FeeSchemaService feeSchemaService;
    
    @Autowired 
    private VatRateService vatRateService;

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<FEE_RULE>> getAllFeeRules() {
        return ResponseEntity.ok(feeRuleService.findAll());
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<FEE_RULE> getFeeRuleById(@PathVariable Integer id) {
        return feeRuleService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createFeeRule(@RequestBody FEE_RULE feeRule) {
        try {
            System.out.println("Received feeRule = " + feeRule);
            System.out.println("feeRuleType = " + feeRule.getFeeRuleType());
            System.out.println("feeSchema = " + feeRule.getFeeSchema());
            System.out.println("vateRate="+feeRule.getFruTva());

            Integer ruleTypeId = feeRule.getFeeRuleType() != null ? feeRule.getFeeRuleType().getFrtCode() : null;
            Integer schemaId = feeRule.getFeeSchema() != null ? feeRule.getFeeSchema().getFscCode() : null;
            Integer vatId = feeRule.getFruTva()!= null ? feeRule.getFruTva().getVatCode() : null ;

            if (ruleTypeId == null) {
                return ResponseEntity.badRequest().body("Missing feeRuleType.frtCode");
            }
            if (schemaId == null) {
                return ResponseEntity.badRequest().body("Missing feeSchema.fscCode");
            }
            
            if (vatId == null) {
                return ResponseEntity.badRequest().body("Missing feeSchema.vatCode");
            }

            Optional<FEE_RULE_TYPE> ruleTypeOpt = feeRuleTypeService.findById(ruleTypeId);
            Optional<FEE_SCHEMA> schemaOpt = feeSchemaService.findById(schemaId);
            Optional<VatRate> vatOpt = vatRateService.findById(vatId);

            if (!ruleTypeOpt.isPresent()) {
                return ResponseEntity.badRequest().body("Invalid FeeRuleType ID.");
            }
            if (!schemaOpt.isPresent()) {
                return ResponseEntity.badRequest().body("Invalid FeeSchema ID.");
            }
            
            if (!vatOpt.isPresent()) {
                return ResponseEntity.badRequest().body("Invalid vatRate ID.");
            }

            feeRule.setFeeRuleType(ruleTypeOpt.get());
            feeRule.setFeeSchema(schemaOpt.get());
            feeRule.setFruTva(vatOpt.get());

            FEE_RULE savedFeeRule = feeRuleService.save(feeRule); // La logique est maintenant dans le service
            return ResponseEntity.ok(savedFeeRule);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
    }

 // ✅ UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFeeRule(@PathVariable Integer id, @RequestBody FEE_RULE updatedRule) {
        Optional<FEE_RULE> existing = feeRuleService.findById(id);

        if (!existing.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<FEE_RULE_TYPE> ruleType = feeRuleTypeService.findById(updatedRule.getFeeRuleType().getFrtCode());
        Optional<FEE_SCHEMA> schema = feeSchemaService.findById(updatedRule.getFeeSchema().getFscCode());

        if (!ruleType.isPresent()) return ResponseEntity.badRequest().body("Invalid FeeRuleType.");
        if (!schema.isPresent()) return ResponseEntity.badRequest().body("Invalid FeeSchema.");

        // Validation et récupération de VatRate
        if (updatedRule.getFruTva() == null || updatedRule.getFruTva().getVatCode() == null) {
            return ResponseEntity.badRequest().body("VatRate is required.");
        }
        Optional<VatRate> vatRateOpt = vatRateService.findById(updatedRule.getFruTva().getVatCode());
        if (!vatRateOpt.isPresent()) {
            return ResponseEntity.badRequest().body("Invalid VatRate ID.");
        }

        FEE_RULE rule = existing.get();
        rule.setFruIden(updatedRule.getFruIden());
        rule.setFruLabe(updatedRule.getFruLabe());
        rule.setFruPrimaryWalletId(updatedRule.getFruPrimaryWalletId());
        rule.setFruPrimaryAmount(updatedRule.getFruPrimaryAmount());
        rule.setFruPrimaryFeesId(updatedRule.getFruPrimaryFeesId());
        rule.setFruFeesWalletId(updatedRule.getFruFeesWalletId());
        rule.setFruFeesAmount(updatedRule.getFruFeesAmount());
        rule.setFruTva(vatRateOpt.get()); // Utilise l'entité VatRate complète
        rule.setFruTvaWalletId(updatedRule.getFruTvaWalletId());
        rule.setFruTvaAmount(updatedRule.getFruTvaAmount());
        rule.setFruSens(updatedRule.getFruSens());
        rule.setFeeRuleType(ruleType.get());
        rule.setFeeSchema(schema.get());

        FEE_RULE saved = feeRuleService.save(rule);
        return ResponseEntity.ok(saved);
    }
    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeeRule(@PathVariable Integer id) {
        Optional<FEE_RULE> rule = feeRuleService.findById(id);
        if (!rule.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        feeRuleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<FEE_RULE>> searchFeeRules(@RequestParam("word") String searchWord) {
        List<FEE_RULE> feeRules = feeRuleService.searchFeeRules(searchWord);
        return ResponseEntity.ok(feeRules);
    }
}
