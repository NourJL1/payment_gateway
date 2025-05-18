package com.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CUSTOMER_DOC_LISTE")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})



public class CUSTOMER_DOC_LISTE {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "CDL_CODE", nullable = false, unique = true)
	    private Integer cdlCode;

	    @Column(name = "CDL_IDEN", nullable = false)
	    private Integer cdlIden;

	    @Column(name = "CDL_LABE", nullable = false)
	    private String cdlLabe;
	    @JsonIgnore

	    
	    @OneToMany(mappedBy = "customerDocListe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    

	    
	    private List<CUSTOMER_DOC> customerDocs;
	    
	    


		public Integer getCdlIden() {
			return cdlIden;
		}

		public void setCdlIden(Integer cdlIden) {
			this.cdlIden = cdlIden;
		}

		public String getCdlLabe() {
			return cdlLabe;
		}

		public void setCdlLabe(String cdlLabe) {
			this.cdlLabe = cdlLabe;
		}

		public List<CUSTOMER_DOC> getCustomerDocs() {
			return customerDocs;
		}
		

		public void setCustomerDocs(List<CUSTOMER_DOC> customerDocs) {
			this.customerDocs = customerDocs;
		}
		

		public Integer getCdlCode() {
			return cdlCode;
		}

		public void setCdlCode(Integer cdlCode) {
			this.cdlCode = cdlCode;
		}

		
		
		public CUSTOMER_DOC_LISTE(Integer cdlCode, Integer cdlIden, String cdlLabe, List<CUSTOMER_DOC> customerDocs) {
			super();
			this.cdlCode = cdlCode;
			this.cdlIden = cdlIden;
			this.cdlLabe = cdlLabe;
			this.customerDocs = customerDocs;
		}

		public CUSTOMER_DOC_LISTE() {}

	    
}
