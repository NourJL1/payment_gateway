package com.servicesImp;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.model.CUSTOMER_DOC;
import com.repository.CustomerDocRepository;
import com.service.CustomerDocService;

import org.springframework.web.multipart.MultipartFile;
import org.aspectj.util.FileUtil;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileOutputStream;

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
    private String storageDir ;

	private static SecretKey secretKey;
        static{
            try{
                /* KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
                keyGenerator.init(128);
                secretKey = keyGenerator.generateKey(); */
                String key = "@myEncryptionKey"; 
                secretKey = new SecretKeySpec(key.getBytes(), "AES");
        }
        catch(Exception e)
        {System.out.println(e.getClass().toString() + ":\n" + e.getMessage());}
    }

	public static byte[] encrypt(MultipartFile data) throws Exception 
    {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data.getBytes());
    }

    public static byte[] decrypt(byte[] data) throws Exception 
    {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

	@Override
	public CUSTOMER_DOC save(CUSTOMER_DOC customerDoc) {

		/* String userDir = storageDir + "/" + user.getRole()  + "/" + File.separator + user.getId() + File.separator;

        try
        {
            // create directory if it doesn't exist
            if(!Files.exists(Paths.get(userDir)))
                Files.createDirectory(Paths.get(userDir));

            // delete document if it already exist
            if(customerDocRepository.existsByUserAndType(user, type))
            {
                Files.deleteIfExists(new File(userDir+type+getFileExtension(file.getOriginalFilename())).toPath());
                documentRepository.delete(documentRepository.findByUserAndType(user, type));
            }
       
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("file storage directory error: " + e.getClass().toString() + ":\n" + e.getMessage());
        }

        // check if file is attached
        if(file == null || file.isEmpty())
            return ResponseEntity.badRequest().body("no file was attached");

        Document document = new Document(
            null,
            user,
            getFileExtension(file.getOriginalFilename()),
            type,
            false
        );

        // encryption
        byte[] encryptedFile;
        try
        {
            encryptedFile = encrypt(file); 
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("file encryption error: " + e.getClass().toString() + ":\n" + e.getMessage());
        }

        File filePath = new File(userDir + type + ".enc");
        try 
        {
            FileOutputStream fos = new FileOutputStream(filePath);
            fos.write(encryptedFile); 
            fos.close();
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("file storage error: " + e.getClass().toString() + ":\n" + e.getMessage());
        }

        // save credentials in db
        documentRepository.save(document); */
        //return ResponseEntity.ok().body("file stored successfully");
		 return customerDocRepository.save(customerDoc);
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
