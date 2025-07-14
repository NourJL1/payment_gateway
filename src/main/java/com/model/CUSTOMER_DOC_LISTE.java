package com.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.servicesImp.CustomerDocListeServiceImp;
import com.servicesImp.CustomerDocServiceImp;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import org.aspectj.util.FileUtil;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "CUSTOMER_DOC_LISTE")
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class CUSTOMER_DOC_LISTE {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CDL_CODE", nullable = false, unique = true)
	private Integer cdlCode;

	@Column(name = "CDL_IDEN", nullable = false)
	private String cdlIden;

	@Column(name = "CDL_LABE", nullable = false)
	private String cdlLabe;
	@JsonIgnore
	@ToString.Exclude
	@OneToMany(mappedBy = "customerDocListe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CUSTOMER_DOC> customerDocs;

	@OneToOne(mappedBy = "customerDocListe", fetch = FetchType.LAZY)
	// @JoinColumn(name = "CDL_CID_CODE", nullable = false)
	@ToString.Exclude
	@JsonIgnore
	private CUSTOMER_IDENTITY customerIdentity;

	
	@PreRemove
	public void preRemove() {
		String storageDir = (new CustomerDocListeServiceImp()).getStorageDir();
		System.out.println("----storageDir: " + storageDir);
		try
        {
            FileUtil.deleteContents(new File(storageDir + File.separator + cdlLabe));
            Files.deleteIfExists(Paths.get(storageDir + File.separator + cdlLabe));
			System.out.println("----Directory deleted: " + storageDir + File.separator + cdlLabe);
        }
        catch(IOException e)
        {System.out.println("------------------------------"+e.getClass().toString() + ":\n" + e.getMessage());}
	}

	public String getCdlIden() {
		return cdlIden;
	}

	public void setCdlIden(String cdlIden) {
		this.cdlIden = cdlIden;
	}

	@PrePersist
	public void setCdlIden() {
		this.cdlIden = "CDL-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm")) + "-"
				+ UUID.randomUUID().toString().substring(0, 4).toUpperCase();
		// this.cdlLabe = customerIdentity.getCustomer().getCusIden();
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

	public CUSTOMER_DOC_LISTE(Integer cdlCode, String cdlIden, String cdlLabe, List<CUSTOMER_DOC> customerDocs) {
		super();
		this.cdlCode = cdlCode;
		this.cdlIden = cdlIden;
		this.cdlLabe = cdlLabe;
		this.customerDocs = customerDocs;
	}

	public CUSTOMER_DOC_LISTE() {
	}

}
