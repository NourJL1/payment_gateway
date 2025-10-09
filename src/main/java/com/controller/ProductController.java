package com.controller;

import com.model.PRODUCT;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    @GetMapping
    public ResponseEntity<List<PRODUCT>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<PRODUCT>> getActiveProducts() {
        return ResponseEntity.ok(productService.getActiveProducts());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PRODUCT> getProductById(@PathVariable Integer id) {
        Optional<PRODUCT> product = productService.getProductById(id);
        return product.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<PRODUCT> createProduct(@Valid @RequestBody PRODUCT product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<PRODUCT> updateProduct(@PathVariable Integer id, 
                                               @Valid @RequestBody PRODUCT product) {
        PRODUCT updatedProduct = productService.updateProduct(id, product);
        return updatedProduct != null ? ResponseEntity.ok(updatedProduct) 
                                    : ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{id}/purchase")
    public ResponseEntity<String> purchaseProduct(@PathVariable Integer id,
                                                 @RequestParam String customerWalletIden) {
        boolean success = productService.purchaseProduct(id, customerWalletIden);
        return success ? ResponseEntity.ok("Purchase successful") 
                      : ResponseEntity.badRequest().body("Purchase failed");
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<PRODUCT>> searchProducts(@RequestParam String q) {
        return ResponseEntity.ok(productService.searchProducts(q));
    }
    
    @GetMapping("/wallet/{walCode}")
    public ResponseEntity<List<PRODUCT>> getProductsByWallet(@PathVariable Integer walCode) {
        return ResponseEntity.ok(productService.getProductsByWallet(walCode));
    }
}