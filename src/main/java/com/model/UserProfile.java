package com.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import com.model.*;

@Entity
@Table(name = "USER_PROFILE")

public class UserProfile {
    @Id
    @Column(name = "UPR_CODE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer code;

    @Column(name = "UPR_IDEN")
    private String identifier;

    @Column(name = "UPR_LABE")
    private String label;

    @Column(name = "UPR_VIEW_BANK")
    private Boolean viewBank;

    @Column(name = "UPR_VIEW_BRAN")
    private Boolean viewBranch;

    @Column(name = "UPR_VIEW_CHLD")
    private Boolean viewChild;

    @Column(name = "UPR_VIEW_CUST_MERC")
    private Boolean viewCustomerMerchant;

    @Column(name = "UPR_GRANT")
    private Boolean grantPermission;

    @Column(name = "UPR_CRYP_PAN")
    private Boolean canDecryptPan;

    @OneToMany(mappedBy = "profile")
    private List<User> users;

    @ManyToMany
    @JoinTable(name = "USERS_PROFILE_MODULES", joinColumns = @JoinColumn(name = "PRM_UPR_CODE"), inverseJoinColumns = @JoinColumn(name = "PRM_MOD_CODE"))
    private List<Modules> modules;

    @OneToMany(mappedBy = "profile")
    private List<UserProfileMenuOption> profileMenuOptions;

    public String getIdentifier() {
        return identifier;
    }

    public String getLabel() {
        return label;
    }

    public Boolean getViewBank() {
        return viewBank;
    }

    public Boolean getViewBranch() {
        return viewBranch;
    }

    public Boolean getViewChild() {
        return viewChild;
    }

    public Boolean getViewCustomerMerchant() {
        return viewCustomerMerchant;
    }

    public Boolean getGrantPermission() {
        return grantPermission;
    }

    public Boolean getCanDecryptPan() {
        return canDecryptPan;
    }

    public List<Modules> getModules() {
        return modules;
    }

    public List<UserProfileMenuOption> getProfileMenuOptions() {
        return profileMenuOptions;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setViewBank(Boolean viewBank) {
        this.viewBank = viewBank;
    }

    public void setViewBranch(Boolean viewBranch) {
        this.viewBranch = viewBranch;
    }

    public void setViewChild(Boolean viewChild) {
        this.viewChild = viewChild;
    }

    public void setViewCustomerMerchant(Boolean viewCustomerMerchant) {
        this.viewCustomerMerchant = viewCustomerMerchant;
    }

    public void setGrantPermission(Boolean grantPermission) {
        this.grantPermission = grantPermission;
    }

    public void setCanDecryptPan(Boolean canDecryptPan) {
        this.canDecryptPan = canDecryptPan;
    }

    public void setModules(List<Modules> modules) {
        this.modules = modules;
    }

    public void setProfileMenuOptions(List<UserProfileMenuOption> profileMenuOptions) {
        this.profileMenuOptions = profileMenuOptions;
    }

    public UserProfile(Integer code, String identifier, String label, Boolean viewBank, Boolean viewBranch,
            Boolean viewChild, Boolean viewCustomerMerchant, Boolean grantPermission, Boolean canDecryptPan,
            List<User> users, List<Modules> modules, List<UserProfileMenuOption> profileMenuOptions) {
        this.code = code;
        this.identifier = identifier;
        this.label = label;
        this.viewBank = viewBank;
        this.viewBranch = viewBranch;
        this.viewChild = viewChild;
        this.viewCustomerMerchant = viewCustomerMerchant;
        this.grantPermission = grantPermission;
        this.canDecryptPan = canDecryptPan;
        this.users = users;
        this.modules = modules;
        this.profileMenuOptions = profileMenuOptions;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public UserProfile() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
