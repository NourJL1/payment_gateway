package com.service;

import com.model.PRODUCT;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    PRODUCT createProduct(PRODUCT product);
    Optional<PRODUCT> getProductById(Integer proCode);
    List<PRODUCT> getAllProducts();
    List<PRODUCT> getActiveProducts();
    PRODUCT updateProduct(Integer proCode, PRODUCT product);
    void deleteProduct(Integer proCode);
    boolean purchaseProduct(Integer proCode, String customerWalletIden);
    List<PRODUCT> getProductsByWallet(Integer walCode);
    List<PRODUCT> searchProducts(String searchTerm);
}