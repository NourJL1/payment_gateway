package com.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "FEE_RULE_TYPE")
@Data
public class FEE_RULE_TYPE {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "FRT_CODE", nullable = false, unique = true)
	    private Integer frtCode;

	    @Column(name = "FRT_IDEN", nullable = false)
	    private String frtIden;

	    @Column(name = "FRT_LABE", nullable = false)
	    private String frtLabe;

	

		public Integer getFrtCode() {
			return frtCode;
		}



		public void setFrtCode(Integer frtCode) {
			this.frtCode = frtCode;
		}



		public String getFrtIden() {
			return frtIden;
		}



		public void setFrtIden(String frtIden) {
			this.frtIden = frtIden;
		}

		@PrePersist
		public void setFrtIden() {
			this.frtIden = "FRT-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm")) + "-"
				+ UUID.randomUUID().toString().substring(0, 4).toUpperCase();
		}



		public String getFrtLabe() {
			return frtLabe;
		}



		public void setFrtLabe(String frtLabe) {
			this.frtLabe = frtLabe;
		}



		public FEE_RULE_TYPE(Integer frtCode, String frtIden, String frtLabe) {
			super();
			this.frtCode = frtCode;
			this.frtIden = frtIden;
			this.frtLabe = frtLabe;
		}



		public FEE_RULE_TYPE() {}
	    

}
