package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.VatRate;
import com.service.VatRateService;

@RestController
@RequestMapping("/api/vat-rates")

public class VatRateController {
	@Autowired
    private VatRateService vatRateService;

    // GET: Retrieve all VatRates
    @GetMapping
    public List<VatRate> getAllVatRates() {
        return vatRateService.findAll();
    }

    // GET: Retrieve a VatRate by vatCode and vatActive
    @GetMapping("/{vatCode}")
    public ResponseEntity<VatRate> getVatRateByCode(
            @PathVariable Integer vatCode,
            @RequestParam(required = false) Integer vatActive) {
        VatRate vatRate = vatRateService.findByVatCodeAndVatActive(vatCode, vatActive != null ? vatActive : 1);
        return vatRate != null ? ResponseEntity.ok(vatRate) : ResponseEntity.notFound().build();
    }

    // POST: Create a new VatRate
    @PostMapping
    public ResponseEntity<VatRate> createVatRate(@RequestBody VatRate vatRate) {
        VatRate savedVatRate = vatRateService.save(vatRate);
        return ResponseEntity.status(201).body(savedVatRate);
    }

    // PUT: Update an existing VatRate by vatCode
    @PutMapping("/{vatCode}")
    public ResponseEntity<VatRate> updateVatRate(
            @PathVariable Integer vatCode,
            @RequestBody VatRate vatRateDetails) {
        VatRate existingVatRate = vatRateService.findByVatCodeAndVatActive(vatCode, 1);
        if (existingVatRate == null) {
            return ResponseEntity.notFound().build();
        }
        existingVatRate.setVatRate(vatRateDetails.getVatRate());
        existingVatRate.setVatLabe(vatRateDetails.getVatLabe());
        existingVatRate.setVatActive(vatRateDetails.getVatActive());
        VatRate updatedVatRate = vatRateService.save(existingVatRate);
        return ResponseEntity.ok(updatedVatRate);
    }

    // DELETE: Delete a VatRate by vatCode
    @DeleteMapping("/{vatCode}")
    public ResponseEntity<Void> deleteVatRate(@PathVariable Integer vatCode) {
        VatRate existingVatRate = vatRateService.findByVatCodeAndVatActive(vatCode, 1);
        if (existingVatRate == null) {
            return ResponseEntity.notFound().build();
        }
        vatRateService.deleteByVatCode(vatCode);
        return ResponseEntity.ok().build();
    }

    // Exception handler for invalid input (e.g., non-integer vatCode)
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormatException(NumberFormatException ex) {
        return ResponseEntity.badRequest().body("Invalid vatCode format: " + ex.getMessage());
    }

}
