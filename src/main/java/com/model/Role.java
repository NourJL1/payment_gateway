package com.model;



<<<<<<< HEAD
=======
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d
import jakarta.persistence.*;

@Entity
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false)
    private RoleName name;
<<<<<<< HEAD
=======


	@OneToMany(mappedBy = "role")
	@JsonIgnore
    private Set<CUSTOMER> customers = new HashSet<>();
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d
    
    public enum RoleName {
        CUSTOMER,
        INTERNE,
        MERCHANT,
<<<<<<< HEAD
        ADMIN,
=======
        ADMIN
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d
    }

    // Getters and Setters
    
    public Long getId() {
		return id;
	}
    public Role() {}
	public Role(Long id, RoleName name) {
<<<<<<< HEAD
		super();
=======
		//super();
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d
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
<<<<<<< HEAD
=======
	public Set<CUSTOMER> getCustomers() {
		return customers;
	}
	public void setCustomers(Set<CUSTOMER> customers) {
		this.customers = customers;
	}
	

	
>>>>>>> 8d7b492c1726db82471bfb1dc6e77da1e903039d
}


