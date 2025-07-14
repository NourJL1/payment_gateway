package com.model;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "CUSTOMER_IDENTITY_TYPE")
@Data

public class CUSTOMER_IDENTITY_TYPE {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "CIT_CODE", nullable = false, unique = true)
	    private Integer citCode;

	    @Column(name = "CIT_IDEN", nullable = false)
	    private String citIden;

	    @Column(name = "CIT_LABE", nullable = false)
	    private String citLabe;
	    
	    @OneToMany(mappedBy = "customerIdentityType", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @ToString.Exclude
	    @EqualsAndHashCode.Exclude
	    @JsonIgnore

	    private List<CUSTOMER_IDENTITY> customerIdentities;

	   


		public String getCitIden() {
			return citIden;
		}

		public void setCitIden(String citIden) {
			this.citIden = citIden;
		}

		@PrePersist
		public void setCitIden() {
			this.citIden = "CIT-" + citLabe.substring(0, 3).toUpperCase();
		}

		public String getCitLabe() {
			return citLabe;
		}

		public void setCitLabe(String citLabe) {
			this.citLabe = citLabe;
		}
		
		

		

		public Integer getCitCode() {
			return citCode;
		}

		public void setCitCode(Integer citCode) {
			this.citCode = citCode;
		}

		public List<CUSTOMER_IDENTITY> getCustomerIdentities() {
			return customerIdentities;
		}

		public void setCustomerIdentities(List<CUSTOMER_IDENTITY> customerIdentities) {
			this.customerIdentities = customerIdentities;
		}

		public CUSTOMER_IDENTITY_TYPE(Integer citCode, String citIden, String citLabe,
				List<CUSTOMER_IDENTITY> customerIdentities) {
			super();
			this.citCode = citCode;
			this.citIden = citIden;
			this.citLabe = citLabe;
			this.customerIdentities = customerIdentities;
		}

		// Default constructor
	    public   CUSTOMER_IDENTITY_TYPE() {
	    }
		
	    
	    
	}

