package com.controller;

import com.model.DOC_TYPE;
import com.service.DocTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/doc-type")

public class DocTypeController {
	@Autowired
    private DocTypeService docTypeService;

    @GetMapping
    public List<DOC_TYPE> getAllDocTypes() {
        return docTypeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DOC_TYPE> getDocTypeById(@PathVariable Integer id) {
        Optional<DOC_TYPE> docType = docTypeService.findById(id);
        return docType.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DOC_TYPE> createDocType(@RequestBody DOC_TYPE docType) {
        DOC_TYPE createdDocType = docTypeService.save(docType);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDocType);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DOC_TYPE> updateDocType(@PathVariable Integer id, @RequestBody DOC_TYPE docType) {
        if (!docTypeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        DOC_TYPE updatedDocType = docTypeService.save(docType);
        return ResponseEntity.ok(updatedDocType);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocType(@PathVariable Integer id) {
        if (!docTypeService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        docTypeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<DOC_TYPE>> searchDocTypes(@RequestParam("word") String searchWord) {
        List<DOC_TYPE> docTypes = docTypeService.searchDocTypes(searchWord);
        return ResponseEntity.ok(docTypes);
    }
}
