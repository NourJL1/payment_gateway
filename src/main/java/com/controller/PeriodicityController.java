package com.controller;
import com.model.PERIODICITY;
import com.service.PeriodicityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/periodicities")

public class PeriodicityController {
	@Autowired
    private PeriodicityService service;

    @PostMapping
    public PERIODICITY create(@RequestBody PERIODICITY periodicity) {
        return service.createPeriodicity(periodicity);
    }

    @GetMapping
    public List<PERIODICITY> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Optional<PERIODICITY> getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public PERIODICITY update(@PathVariable Integer id, @RequestBody PERIODICITY periodicity) {
        return service.update(id, periodicity);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PERIODICITY>> searchPeriodicities(@RequestParam("word") String searchWord) {
        List<PERIODICITY> periodicities = service.searchPeriodicities(searchWord);
        return ResponseEntity.ok(periodicities);
    }

}
