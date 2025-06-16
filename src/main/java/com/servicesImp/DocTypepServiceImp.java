package com.servicesImp;
import com.model.DOC_TYPE;
import com.repository.DocTypeRepository;
import com.service.DocTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class DocTypepServiceImp implements DocTypeService {

    @Autowired
    private DocTypeRepository docTypeRepository;

	@Override
	public List<DOC_TYPE> findAll() {
		return docTypeRepository.findAll();
	}

	@Override
	public Optional<DOC_TYPE> findById(Integer id) {
		 return docTypeRepository.findById(id);
		
		 
	}

	@Override
	public DOC_TYPE save(DOC_TYPE docType) {
		return docTypeRepository.save(docType);
	}

	@Override
	public void deleteById(Integer id) {
		docTypeRepository.deleteById(id);
		
	}

  @Override
    public List<DOC_TYPE> searchDocTypes(String searchWord) {
        if (searchWord == null || searchWord.trim().isEmpty()) {
            return docTypeRepository.findAll();
        }
        return docTypeRepository.searchDocTypes(searchWord);
    }

}
