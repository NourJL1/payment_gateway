package com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS")

public class User {
    @Id
    @Column(name = "USE_CODE")
    private Integer code;

    @Column(name = "USE_LOGI", unique = true)
    private String login;

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

}
