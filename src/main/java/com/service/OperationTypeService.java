package com.service;
import com.model.OPERATION_TYPE;
import java.util.List;
import java.util.Optional;

public interface OperationTypeService {
	List<OPERATION_TYPE> getAllOperationTypes();
    OPERATION_TYPE getOperationTypeById(Integer id);
    OPERATION_TYPE createOperationType(OPERATION_TYPE operationType);
    OPERATION_TYPE updateOperationType(Integer id, OPERATION_TYPE operationType);
    void deleteOperationType(Integer id);
      List<OPERATION_TYPE> searchOperationTypes(String searchWord);
}
