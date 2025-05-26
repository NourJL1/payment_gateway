package com.servicesImp;
import com.model.*;
import com.repository.FeeSchemaRepository;
import com.service.FeeSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.repository.FeeSchemaRepository;
import com.service.FeeSchemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service

public class FeeSchemaServiceImp implements FeeSchemaService {

    @Autowired
    private FeeSchemaRepository feeSchemaRepository;

	@Override
	public List<FEE_SCHEMA> findAll() {
		  return feeSchemaRepository.findAll();
	}

	@Override
	public Optional<FEE_SCHEMA> findById(Integer id) {
        return feeSchemaRepository.findById(id);

	}

	@Override
	public FEE_SCHEMA save(FEE_SCHEMA feeSchema) {
        return feeSchemaRepository.save(feeSchema);

	}

	@Override
	public void deleteById(Integer id) {
        feeSchemaRepository.deleteById(id);
		
	}

	
    


}
