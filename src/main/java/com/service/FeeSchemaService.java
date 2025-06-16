package com.service;

import com.model.*;
import java.util.List;
import java.util.Optional;

public interface FeeSchemaService {
	   List<FEE_SCHEMA> findAll();
	    Optional<FEE_SCHEMA> findById(Integer id);
	    FEE_SCHEMA save(FEE_SCHEMA feeSchema);
	    void deleteById(Integer id);
      List<FEE_SCHEMA> searchFeeSchemas(String searchWord);

}
