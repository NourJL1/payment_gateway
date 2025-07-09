package com.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import com.model.OPERATION_DETAILS;

public interface OperationsDetailsRepository extends JpaRepository<OPERATION_DETAILS, Integer> {
 @Query("SELECT od FROM OPERATION_DETAILS od WHERE \r\n"
	 		+ "LOWER(od.odeType) LIKE LOWER(CONCAT('%', :searchWord, '%')) \r\n"
	 		+ "OR LOWER(od.odeValue) LIKE LOWER(CONCAT('%', :searchWord, '%')) \r\n"
	 		+ "OR LOWER(od.odePayMeth) LIKE LOWER(CONCAT('%', :searchWord, '%')) \r\n"
	 		+ "OR LOWER(od.odeRecipientWallet) LIKE LOWER(CONCAT('%', :searchWord, '%')) \r\n"
	 		+ "OR CAST(od.odeCode AS string) LIKE CONCAT('%', :searchWord, '%') \r\n"
	 		+ "OR CAST(od.odeCusCode AS string) LIKE CONCAT('%', :searchWord, '%') \r\n"
	 		+ "OR CAST(od.odeFeeAmount AS string) LIKE CONCAT('%', :searchWord, '%') \r\n"
	 		+ "OR CAST(od.odeIden AS string) LIKE CONCAT('%', :searchWord, '%') \r\n"
	 		+ "OR CAST(od.odeCreatedAt AS string) LIKE CONCAT('%', :searchWord, '%')")
	 
	    List<OPERATION_DETAILS> searchOperationDetails(@Param("searchWord") String searchWord);}






