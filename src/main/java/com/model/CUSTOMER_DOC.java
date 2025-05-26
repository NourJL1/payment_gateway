package com.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "CUSTOMER_DOC")
@Data
public class CUSTOMER_DOC {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "CDO_CODE", nullable = false, unique = true)
	    private Integer cdoCode;

	    @Column(name = "CDO_IDEN", nullable = false)
	    private Integer cdoIden;

	    @Column(name = "CDO_LABE", nullable = false)
	    private String cdoLabe;

	    

	    
	    @ManyToOne
	    @JoinColumn(name = "CDO_CDL_CODE", referencedColumnName = "CDL_CODE", nullable = false)
	    @JsonIgnore

	    private CUSTOMER_DOC_LISTE customerDocListe;
	    
	    @ManyToOne
	    @JoinColumn(name = "CDO_DTY_CODE", referencedColumnName = "DTY_CODE", nullable = false)
	    @JsonIgnore

	    private DOC_TYPE docType;

		public Integer getCdoIden() {
			return cdoIden;
		}

		public void setCdoIden(Integer cdoIden) {
			this.cdoIden = cdoIden;
		}

		public String getCdoLabe() {
			return cdoLabe;
		}

		public void setCdoLabe(String cdoLabe) {
			this.cdoLabe = cdoLabe;
		}

		

		public CUSTOMER_DOC_LISTE getCustomerDocListe() {
			return customerDocListe;
		}

		public void setCustomerDocListe(CUSTOMER_DOC_LISTE customerDocListe) {
			this.customerDocListe = customerDocListe;
		}

		public DOC_TYPE getDocType() {
			return docType;
		}

		public void setDocType(DOC_TYPE docType) {
			this.docType = docType;
		}

		public CUSTOMER_DOC(Integer cdoIden, String cdoLabe, Integer cdoCdlCode, CUSTOMER_DOC_LISTE customerDocListe,
				DOC_TYPE docType) {
			super();
			this.cdoIden = cdoIden;
			this.cdoLabe = cdoLabe;
			this.customerDocListe = customerDocListe;
			this.docType = docType;
		}

		public CUSTOMER_DOC() {}
		
		
	    
	    

}
