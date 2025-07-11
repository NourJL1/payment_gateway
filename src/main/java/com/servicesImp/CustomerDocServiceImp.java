package com.servicesImp;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.model.CUSTOMER_DOC;
import com.repository.CustomerDocRepository;
import com.service.CustomerDocService;

import java.io.File;

@Service

public class CustomerDocServiceImp implements CustomerDocService {

    @Autowired
    private CustomerDocRepository customerDocRepository;

    @Override
    public List<CUSTOMER_DOC> findAll() {
        return customerDocRepository.findAll();
    }

    @Override
    public Optional<CUSTOMER_DOC> findById(Integer id) {
        return customerDocRepository.findById(id);
    }

    @Value("${document.storage.path}")
    private String storageDir;

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
            }

            // encryption
            byte[] encryptedFile;
            encryptedFile = encrypt(file);

            File filePath = new File(customerDocListePath + "/" + customerDoc.getCdoLabe() + ".enc");

            System.out.println("----file path: " + filePath);

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

    @Override
    public List<CUSTOMER_DOC> searchCustomerDocs(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return customerDocRepository.findAll();
        }
        return customerDocRepository.searchCustomerDocs(searchWord);
    }

}
