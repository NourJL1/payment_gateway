package com.model;
import com.fasterxml.jackson.annotation.JsonProperty;

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
	    private Integer frtIden;

	    @Column(name = "FRT_LABE", nullable = false)
	    private String frtLabe;

	

		public Integer getFrtCode() {
			return frtCode;
		}



		public void setFrtCode(Integer frtCode) {
			this.frtCode = frtCode;
		}



		public Integer getFrtIden() {
			return frtIden;
		}



		public void setFrtIden(Integer frtIden) {
			this.frtIden = frtIden;
		}



		public String getFrtLabe() {
			return frtLabe;
		}



		public void setFrtLabe(String frtLabe) {
			this.frtLabe = frtLabe;
		}



		public FEE_RULE_TYPE(Integer frtCode, Integer frtIden, String frtLabe) {
			super();
			this.frtCode = frtCode;
			this.frtIden = frtIden;
			this.frtLabe = frtLabe;
		}



		public FEE_RULE_TYPE() {}
	    

}
