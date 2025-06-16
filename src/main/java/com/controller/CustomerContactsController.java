package com.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.model.CUSTOMER_CONTACTS;
import com.service.CustomerContactsService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer-contacts")
public class CustomerContactsController {
	@Autowired
    private CustomerContactsService service;

    @GetMapping
    public List<CUSTOMER_CONTACTS> getAllContacts() {
        return service.getAllContacts();
    }

    @GetMapping("/{CCO_CODE}")
    public ResponseEntity<CUSTOMER_CONTACTS> getContactById(@PathVariable Integer CCO_CODE) {
        Optional<CUSTOMER_CONTACTS> contact = service.getContactById(CCO_CODE);
        return contact.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public CUSTOMER_CONTACTS createContact(@RequestBody CUSTOMER_CONTACTS contact) {
        return service.createContact(contact);
    }

    @PutMapping("/{CCO_CODE}")
    public ResponseEntity<CUSTOMER_CONTACTS> updateContact(@PathVariable Integer CCO_CODE, @RequestBody CUSTOMER_CONTACTS contactDetails) {
        try {
        	CUSTOMER_CONTACTS updatedContact = service.updateContact(CCO_CODE, contactDetails);
            return ResponseEntity.ok(updatedContact);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{CCO_CODE}")
    public ResponseEntity<Void> deleteContact(@PathVariable Integer CCO_CODE) {
        service.deleteContact(CCO_CODE);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CUSTOMER_CONTACTS>> searchCustomerContacts(@RequestParam("word") String searchWord) {
        List<CUSTOMER_CONTACTS> customerContacts = service.searchCustomerContacts(searchWord);
        return ResponseEntity.ok(customerContacts);
    }
}
