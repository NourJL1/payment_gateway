package com.controller;

import com.model.COUNTRY;
import com.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<COUNTRY> getAllCountries() {
        return countryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<COUNTRY> getCountryById(@PathVariable Integer id) {
        return countryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<COUNTRY> createCountry(@RequestBody COUNTRY country) {
        COUNTRY createdCountry = countryService.save(country);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCountry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<COUNTRY> updateCountry(@PathVariable Integer id, @RequestBody COUNTRY country) {
        Optional<COUNTRY> existingCountryOpt = countryService.findById(id);
        if (existingCountryOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        COUNTRY existingCountry = existingCountryOpt.get();
        if (!id.equals(country.getCtrIden())) {
            return ResponseEntity.badRequest().build(); // Empêche la modification d’un mauvais ID
        }

        existingCountry.setCtrLabe(country.getCtrLabe());

        COUNTRY updatedCountry = countryService.save(existingCountry);
        return ResponseEntity.ok(updatedCountry);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Integer id) {
        if (countryService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        countryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
