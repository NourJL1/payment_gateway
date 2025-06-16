package com.service;
import com.model.CUSTOMER_CONTACTS;
import java.util.List;
import java.util.Optional;

public interface CustomerContactsService {
	 List<CUSTOMER_CONTACTS> getAllContacts();
	    Optional<CUSTOMER_CONTACTS> getContactById(Integer id);
	    CUSTOMER_CONTACTS createContact(CUSTOMER_CONTACTS contact);
	    CUSTOMER_CONTACTS updateContact(Integer id, CUSTOMER_CONTACTS updatedContact);
	    void deleteContact(Integer id);
        List<CUSTOMER_CONTACTS> searchCustomerContacts(String searchWord);

}
