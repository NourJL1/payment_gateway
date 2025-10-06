package com.servicesImp;

import com.model.*;
import com.repository.*;
import com.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private WalletRepository walletRepository;
    
    @Autowired
    private WalletBalanceHistoryRepository walletBalanceHistoryRepository;
    
    @Override
    public PRODUCT createProduct(PRODUCT product) {
        return productRepository.save(product);
    }
    
    @Override
    public Optional<PRODUCT> getProductById(Integer proCode) {
        return productRepository.findById(proCode);
    }
    
    @Override
    public List<PRODUCT> getAllProducts() {
        return productRepository.findAll();
    }
    
    @Override
    public List<PRODUCT> getActiveProducts() {
        return productRepository.findByProIsActiveTrue();
    }
    
    @Override
    public PRODUCT updateProduct(Integer proCode, PRODUCT product) {
        Optional<PRODUCT> existingProduct = productRepository.findById(proCode);
        if (existingProduct.isPresent()) {
            PRODUCT updatedProduct = existingProduct.get();
            updatedProduct.setProName(product.getProName());
            updatedProduct.setProDescription(product.getProDescription());
            updatedProduct.setProPrice(product.getProPrice());
            updatedProduct.setProStockQuantity(product.getProStockQuantity());
            updatedProduct.setProIsActive(product.getProIsActive());
            return productRepository.save(updatedProduct);
        }
        return null;
    }
    
    @Override
    public void deleteProduct(Integer proCode) {
        productRepository.deleteById(proCode);
    }
    
    @Override
    @Transactional
    public boolean purchaseProduct(Integer proCode, String customerWalletIden) {
        try {
            // Get product
            Optional<PRODUCT> productOpt = productRepository.findById(proCode);
            if (productOpt.isEmpty() || !productOpt.get().getProIsActive()) {
                return false;
            }
            
            PRODUCT product = productOpt.get();
            
            // Check stock
            if (product.getProStockQuantity() != null && product.getProStockQuantity() <= 0) {
                return false;
            }
            
            // Get customer wallet
            WALLET customerWallet = walletRepository.findByWalIden(customerWalletIden).stream()
                    .findFirst()
                    .orElse(null);
            
            if (customerWallet == null) {
                return false;
            }
            
            // Check if customer has sufficient balance
            if (customerWallet.getWalLogicBalance() < product.getProPrice().floatValue()) {
                return false;
            }
            
            // Get fees wallet
            WALLET feesWallet = walletRepository.findByWalIden("WAL_FEES").stream()
                    .findFirst()
                    .orElse(null);
            
            if (feesWallet == null) {
                return false;
            }
            
            // Perform transaction
            BigDecimal productPrice = product.getProPrice();
            
            // Deduct from customer wallet
            customerWallet.setWalLogicBalance(customerWallet.getWalLogicBalance() - productPrice.floatValue());
            walletRepository.save(customerWallet);
            
            // Add to fees wallet
            feesWallet.setWalLogicBalance(feesWallet.getWalLogicBalance() + productPrice.floatValue());
            walletRepository.save(feesWallet);
            
            // Update stock
            if (product.getProStockQuantity() != null) {
                product.setProStockQuantity(product.getProStockQuantity() - 1);
                productRepository.save(product);
            }
            
            // Create balance history for customer wallet
            createBalanceHistory(customerWallet, -productPrice.floatValue());
            
            // Create balance history for fees wallet
            createBalanceHistory(feesWallet, productPrice.floatValue());
            
            return true;
            
        } catch (Exception e) {
            throw new RuntimeException("Purchase failed: " + e.getMessage());
        }
    }
    
    private void createBalanceHistory(WALLET wallet, Float amountChange) {
        WALLET_BALANCE_HISTORY history = new WALLET_BALANCE_HISTORY();
        history.setWallet(wallet);
        history.setWbhEffBal(wallet.getWalEffBal());
        history.setWbhLogicBalance(wallet.getWalLogicBalance());
        history.setWbhSpecificBalance(wallet.getWalSpecificBalance());
        history.setWbhLastUpdated(new Date());
        history.setWbhIden(0);
        
        walletBalanceHistoryRepository.save(history);
        
        // Update wallet's last balance history reference
        wallet.setLastBalanceHistory(history);
        walletRepository.save(wallet);
    }
    
    @Override
    public List<PRODUCT> getProductsByWallet(Integer walCode) {
        return productRepository.findByWalletWalCode(walCode);
    }
    
    @Override
    public List<PRODUCT> searchProducts(String searchTerm) {
        return productRepository.searchActiveProducts(searchTerm);
    }
}