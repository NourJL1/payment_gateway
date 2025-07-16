package com.servicesImp;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.model.CUSTOMER_DOC;
import com.model.CUSTOMER_DOC_LISTE;
import com.repository.CustomerDocListeRepository;
import com.repository.CustomerDocRepository;
import com.service.CustomerDocService;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@Service

public class CustomerDocServiceImp implements CustomerDocService {

    @Autowired
    private CustomerDocRepository customerDocRepository;

    @Autowired
    private CustomerDocListeRepository customerDocListeRepository;

    @Value("${document.storage.path}")
    private String storageDir;

    public String getStorageDir() {
        return storageDir;
    }

    @Override
    public List<CUSTOMER_DOC> findAll() {
        return customerDocRepository.findAll();
    }

    @Override
    public ResponseEntity<?> findFileById(Integer id) {
        Optional<CUSTOMER_DOC> customerDoc = customerDocRepository.findById(id);
        if (customerDoc.isPresent()) {
            String path = storageDir + "/" + customerDoc.get().getCustomerDocListe().getCdlLabe() + "/"
                    + customerDoc.get().getCdoLabe() + ".enc";
            try {
                // decrypt attempt
                byte[] file;
                byte[] encryptedFile = Files.readAllBytes(new File(path).toPath());
                file = decrypt(encryptedFile);

                // Create secure inline preview headers
                return ResponseEntity.ok()
                        .header("Content-Type", customerDoc.get().getDocType().getDtyIden())/* 
                        .header("Content-Disposition", "inline; filename=\"" + customerDoc.get().getCdoLabe() + "\"")
                        .header("Content-Security-Policy", "default-src 'none'; sandbox")
                        .header("X-Content-Type-Options", "nosniff")
                        .header("X-Frame-Options", "DENY")
                        .header("Cache-Control", "no-store, max-age=0")
                        .header("Pragma", "no-cache") */
                        .body(file);

            } catch (InvalidKeyException e) {
                return ResponseEntity.badRequest().body(e.getClass().toString() + ":\n" + e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getClass().toString() + ":\n" + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Optional<CUSTOMER_DOC> findById(Integer id) {
        return customerDocRepository.findById(id);
    }

    private static SecretKey secretKey;
    static {
        try {
            /*
             * KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
             * keyGenerator.init(128);
             * secretKey = keyGenerator.generateKey();
             */
            String key = "@myEncryptionKey";
            secretKey = new SecretKeySpec(key.getBytes(), "AES");
        } catch (Exception e) {
            System.out.println(e.getClass().toString() + ":\n" + e.getMessage());
        }
    }

    public static byte[] encrypt(MultipartFile data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data.getBytes());
    }

    public static byte[] decrypt(byte[] data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    @Override
    public CUSTOMER_DOC save(CUSTOMER_DOC customerDoc, MultipartFile file) {

        try {
            Path customerDocListePath = Paths.get(storageDir + "/" + customerDoc.getCustomerDocListe().getCdlLabe());
            // Create directory if it doesn't exist
            if (!Files.exists(customerDocListePath)) {
                Files.createDirectories(customerDocListePath);
                System.out.println("--------------" + customerDocListePath);
            }

            // encryption
            byte[] encryptedFile;
            encryptedFile = encrypt(file);

            File filePath = new File(customerDocListePath + "/" + customerDoc.getCdoLabe() + ".enc");

            System.out.println("--------------" + filePath.getAbsolutePath());

            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(encryptedFile);
            fos.close();

            return customerDocRepository.save(customerDoc);
        } catch (Exception e) {
            System.out.println(e.getClass() + "---" + e.getMessage());
            return null;
            // return ResponseEntity.badRequest().body("file storage error: " +
            // e.getClass().toString() + ":\n" + e.getMessage());
        }

    }

    @Override
    public void deleteById(Integer id) {
        customerDocRepository.deleteById(id);

    }

    @Data
    @AllArgsConstructor
    public class CustomerDocDTO {
        private CUSTOMER_DOC customerDoc;
        private byte[] file;
    }

    @Override
    public ResponseEntity<?> findByCustomerDocListe(Integer cdlCode) {
        CUSTOMER_DOC_LISTE customerDocListe = customerDocListeRepository.findById(cdlCode).orElseThrow();
        List<CUSTOMER_DOC> customerDocs = customerDocRepository.findByCustomerDocListe(customerDocListe);
        return ResponseEntity.ok(customerDocs);
    }
    /*
     * public ResponseEntity<?> findByCustomerDocListe(Integer cdlCode) {
     * 
     * List<CustomerDocDTO> customerDocuments = new ArrayList<>();
     * 
     * CUSTOMER_DOC_LISTE customerDocListe =
     * customerDocListeRepository.findById(cdlCode).orElseThrow();
     * List<CUSTOMER_DOC> customerDocs =
     * customerDocRepository.findByCustomerDocListe(customerDocListe);
     * // if(document == null)
     * // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("file doesn't
     * // exist");
     * 
     * // System.out.println(customerDocs.toString());
     * 
     * for (CUSTOMER_DOC customerDoc : customerDocs) {
     * 
     * // if(customerDoc == null)
     * // return ResponseEntity.status(HttpStatus.NOT_FOUND).body("file doesn't
     * // exist");
     * 
     * String path = storageDir + "/" +
     * customerDoc.getCustomerDocListe().getCdlLabe() + "/"
     * + customerDoc.getCdoLabe() + ".enc";
     * 
     * // decrypt attempt
     * byte[] file;
     * try {
     * byte[] encryptedFile = Files.readAllBytes(new File(path).toPath());
     * file = decrypt(encryptedFile);
     * } catch (InvalidKeyException e) {
     * return ResponseEntity.badRequest().body(e.getClass().toString() + ":\n" +
     * e.getMessage());
     * } catch (Exception e) {
     * return ResponseEntity.badRequest().body(e.getClass().toString() + ":\n" +
     * e.getMessage());
     * }
     * 
     * customerDocuments.add(new CustomerDocDTO(customerDoc, file));
     * }
     * 
     * return ResponseEntity.ok().body(customerDocuments);
     * }
     */

    @Override
    public List<CUSTOMER_DOC> searchCustomerDocs(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return customerDocRepository.findAll();
        }
        return customerDocRepository.searchCustomerDocs(searchWord);
    }

}
