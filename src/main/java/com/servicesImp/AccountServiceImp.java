package com.servicesImp;


import com.model.*;
import com.repository.*;
import com.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImp implements AccountService {

	@Autowired
    private AccountRepository accountRepository;
	
	@Autowired 
	private AccountTypeRepository accountTypeRepository;
	
	@Autowired 
	private AccountListRepository accountListRepository;
	

	@Override
	public ACCOUNT createAccount(ACCOUNT account) {
	    // Vérifier que l'objet ACCOUNT_TYPE n'est pas nul
	    if (account.getAccountType() == null || account.getAccountType().getAtyCode() == null) {
	        throw new IllegalArgumentException("L'ACCOUNT_TYPE ou son atyCode ne peut pas être null.");
	    }

	    // Récupérer le type de compte par son atyCode (clé primaire)
	    Integer atyCode = account.getAccountType().getAtyCode();
	    ACCOUNT_TYPE accountType = accountTypeRepository.findById(atyCode)
	            .orElseThrow(() -> new IllegalArgumentException("L'ACCOUNT_TYPE avec atyCode " + atyCode + " est introuvable."));

	    // Affecter le type complet au compte
	    account.setAccountType(accountType);

	    // Vérifier que l'objet ACCOUNT_LIST (aliCode) n'est pas nul
	    if (account.getAccountList() == null || account.getAccountList().getAliCode() == null) {
	        throw new IllegalArgumentException("L'ACCOUNT_LIST ou son aliCode ne peut pas être null.");
	    }

	    // Récupérer l'ACCOUNT_LIST par son aliCode (clé primaire)
	    Integer aliCode = account.getAccountList().getAliCode();
	    ACCOUNT_LIST accountList = accountListRepository.findById(aliCode)
	            .orElseThrow(() -> new IllegalArgumentException("L'ACCOUNT_LIST avec aliCode " + aliCode + " est introuvable."));

	    // Affecter l'ACCOUNT_LIST complet au compte
	    account.setAccountList(accountList);

	    // Sauvegarder le compte
	    return accountRepository.save(account);
	}

    @Override
    public ACCOUNT getAccountById(Integer id) {
        return accountRepository.findById(id).orElse(null);
    }

    @Override
    public List<ACCOUNT> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public ACCOUNT updateAccount(Integer id, ACCOUNT accountData) {
        // Trouver l'account existant
        ACCOUNT existingAccount = accountRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Account not found"));

        // Vérifier si l'accountType est présent et valide
        if (accountData.getAccountType() != null && accountData.getAccountType().getAtyCode() != null) {
            ACCOUNT_TYPE accountType = accountTypeRepository.findById(accountData.getAccountType().getAtyCode())
                .orElseThrow(() -> new RuntimeException("Account Type not found"));
            existingAccount.setAccountType(accountType);
        }

        // Mettre à jour les autres attributs
        if (accountData.getAccRib() != null) {
            existingAccount.setAccRib(accountData.getAccRib());
        }
        if (accountData.getAccIden() != null) {
            existingAccount.setAccIden(accountData.getAccIden());
        }
        if (accountData.getAccKey() != null) {
            existingAccount.setAccKey(accountData.getAccKey());
        }

        // Sauvegarder et retourner l'account mis à jour
        return accountRepository.save(existingAccount);
    }

    @Override
    public void deleteAccount(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public List<ACCOUNT> searchAccounts(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return accountRepository.findAll();
        }
        return accountRepository.searchAccounts(searchWord);
    }
}
