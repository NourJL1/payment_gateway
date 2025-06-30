package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "CUSTOMER_IDENTITY")
@Data


public class CUSTOMER_IDENTITY {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "CID_CODE", nullable = false, unique = true)
	    private Integer cidCode;

	    @Column(name = "CID_NUM", nullable = false)
	    private String cidNum;

	    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	    @JoinColumn(name = "CID_CIT_CODE", nullable = false)
	    @NotNull(message = "Customer Identity Type is required")
	    private CUSTOMER_IDENTITY_TYPE customerIdentityType;


	    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
	    @JoinColumn(name = "CID_CDL_CODE", nullable = false)
	    @NotNull(message = "Customer Document List is required")
	    private CUSTOMER_DOC_LISTE customerDocListe;
	    
	    public boolean isEmpty() {
	    	 return this.cidNum == null || this.cidNum.isEmpty();	    }
	
		

		public Integer getCidCode() {
			return cidCode;
		}



		public void setCidCode(Integer cidCode) {
			this.cidCode = cidCode;
		}



		public String getCidNum() {
			return cidNum;
		}

		public void setCidNum(String cidNum) {
			this.cidNum = cidNum;
		}

		public CUSTOMER_IDENTITY_TYPE getCustomerIdentityType() {
			return customerIdentityType;
		}

		public void setCustomerIdentityType(CUSTOMER_IDENTITY_TYPE customerIdentityType) {
			this.customerIdentityType = customerIdentityType;
		}

		

		
		
		public CUSTOMER_DOC_LISTE getCustomerDocListe() {
			return customerDocListe;
		}

		public void setCustomerDocListe(CUSTOMER_DOC_LISTE customerDocListe) {
			this.customerDocListe = customerDocListe;
		}
		
		

		
		

		public CUSTOMER_IDENTITY(Integer cidCode, String cidNum,
				@NotNull(message = "Customer Identity Type is required") CUSTOMER_IDENTITY_TYPE customerIdentityType,
				@NotNull(message = "Customer Document List is required") CUSTOMER_DOC_LISTE customerDocListe) {
			super();
			this.cidCode = cidCode;
			this.cidNum = cidNum;
			this.customerIdentityType = customerIdentityType;
			this.customerDocListe = customerDocListe;
		}



		public CUSTOMER_IDENTITY() {}
	    
	    
    
	

}
