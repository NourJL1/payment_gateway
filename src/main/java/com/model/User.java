package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.OnDelete;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "USERS", uniqueConstraints = { @UniqueConstraint(columnNames = "MOP_CODE"),
        @UniqueConstraint(columnNames = "MOP_IDEN"),
        @UniqueConstraint(columnNames = "MOP_LOGI"),
        @UniqueConstraint(columnNames = "MOP_PHONE"),
        @UniqueConstraint(columnNames = "MOP_MAIL")
})

public class User implements UserDetails// extends ABSTRACT_USER
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USE_CODE")
    private Integer code;

    @Column(name = "USE_IDEN")
    private String identifier;

    @Column(name = "USE_LOGI", unique = true)
    private String login;

    @Column(name = "USE_PASS")
    private String password;

    @Column(name = "USE_FIRS_NAME")
    private String firstName;

    @Column(name = "USE_MIDD_NAME")
    private String middleName;

    @Column(name = "USE_LAST_NAME")
    private String lastName;

    @Column(name = "USE_FUNC")
    private String function;

    @Column(name = "USE_UST_CODE")
    private String statusCode;

    @Column(name = "USE_PHONE")
    private String phone;

    @Column(name = "USE_FAX")
    private String fax;

    @Column(name = "USE_MAIL")
    private String email;

    @ManyToOne
    @JoinColumn(name = "USE_UPR_CODE")
    private UserProfile profile;

    @PrePersist
    public void prePersist() {
        this.identifier = "USE-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm")) + "-"
                + UUID.randomUUID().toString().substring(0, 4).toUpperCase();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public User(Integer code, String login, String firstName, String middleName, String lastName, String function,
            String statusCode, String phone, String fax, String email, UserProfile profile) {
        this.code = code;
        this.login = login;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.function = function;
        this.statusCode = statusCode;
        this.phone = phone;
        this.fax = fax;
        this.email = email;
        this.profile = profile;
    }

    public User() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @JsonProperty("fullName")
    public String getFullName() {
        StringBuilder fullName = new StringBuilder();

        if (firstName != null)
            fullName.append(firstName).append(" ");
        if (middleName != null)
            fullName.append(middleName).append(" ");
        if (lastName != null)
            fullName.append(lastName);

        return fullName.toString().trim();
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.getClass().getSimpleName().toUpperCase()));

        if (profile != null) {
            authorities.add(new SimpleGrantedAuthority("PROFILE_" + profile.getLabel().toUpperCase()));
            // authorities.add(new SimpleGrantedAuthority(profile.getIdentifier()));

            /* for (Module module : profile.getModules())
                authorities.add(new SimpleGrantedAuthority("MODULE_" + module.getAccessPath().toUpperCase()));

            for (UserProfileMenuOption upmo : profile.getProfileMenuOptions())
                authorities.add(new SimpleGrantedAuthority("UPMO_" + upmo.getId())); */
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

}
