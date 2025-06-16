package com.servicesImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.model.CUSTOMER_CONTACTS;

import com.repository.CustomerContactsRepository;
import com.service.CustomerContactsService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerContactsServiceImp implements CustomerContactsService{
	 @Autowired
	    private CustomerContactsRepository repository;

	@Override
	public List<CUSTOMER_CONTACTS> getAllContacts() {
		 return repository.findAll();
	}

	@Override
	public Optional<CUSTOMER_CONTACTS> getContactById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public CUSTOMER_CONTACTS createContact(CUSTOMER_CONTACTS contact) {
		 return repository.save(contact);
	}

	@Override
	public CUSTOMER_CONTACTS updateContact(Integer id, CUSTOMER_CONTACTS updatedContact) {
		return repository.findById(id).map(contact -> {
            contact.setCcoIden(updatedContact.getCcoIden());
            contact.setCcoContactName(updatedContact.getCcoContactName());
            contact.setCcoContactMail(updatedContact.getCcoContactMail());
            contact.setCcoContactPhone(updatedContact.getCcoContactPhone());
            //contact.setCcoContactWalletId(updatedContact.getCcoContactWalletId());
            contact.setCcoLastInteraction(updatedContact.getCcoLastInteraction());
            return repository.save(contact);
        }).orElseThrow(() -> new RuntimeException("Contact not found"));
	}

	@Override
	public void deleteContact(Integer id) {
		 repository.deleteById(id);
		
	}

	    @Override
	    public List<CUSTOMER_CONTACTS> searchCustomerContacts(String searchWord) {
	        if (searchWord == null || searchWord.trim().isEmpty()) {
	            return repository.findAll();
	        }
	        return repository.searchCustomerContacts(searchWord);
	    }

}
