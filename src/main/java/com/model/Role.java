package com.model;



import jakarta.persistence.*;

@Entity
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private RoleName name;


	/* @OneToMany(mappedBy = "role")
	@JsonIgnore
    private Set<CUSTOMER> customers = new HashSet<>(); */
    
    public enum RoleName {
        CUSTOMER,
        INTERNE,
        MERCHANT,
        ADMIN
    }

    // Getters and Setters
    
    public Long getId() {
		return id;
	}
    public Role() {}
	public Role(Long id, RoleName name) {
		//super();
		this.id = id;
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}
	/* public Set<CUSTOMER> getCustomers() {
		return customers;
	}
	public void setCustomers(Set<CUSTOMER> customers) {
		this.customers = customers;
	} */
	

	
}


