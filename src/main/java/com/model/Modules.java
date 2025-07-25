package com.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "MODULE")
@Data
public class Modules {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "MOD_CODE")

    private Integer code;

    @Column(name = "MOD_IDEN")
    private String identifier;

    @Column(name = "MOD_LABE")
    private String label;

    @Column(name = "MOD_LOGO")
    private String logo;

    @Column(name = "MOD_MENU")
    private Boolean isMenu;

    @Column(name = "MOD_ACCE")
    private String accessPath;

    @Column(name = "MOD_ORDR")
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "MOD_MOD_CODE")
    private Modules parentModule;

    @ManyToMany(mappedBy = "modules")
    private List<UserProfile> profiles;

    @OneToMany(mappedBy = "module")
    private List<MenuOption> menuOptions;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Boolean getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Boolean isMenu) {
        this.isMenu = isMenu;
    }

    public String getAccessPath() {
        return accessPath;
    }

    public void setAccessPath(String accessPath) {
        this.accessPath = accessPath;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Modules getParentModule() {
        return parentModule;
    }

    public void setParentModule(Modules parentModule) {
        this.parentModule = parentModule;
    }

    public List<UserProfile> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<UserProfile> profiles) {
        this.profiles = profiles;
    }

    public List<MenuOption> getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(List<MenuOption> menuOptions) {
        this.menuOptions = menuOptions;
    }

    public Modules(Integer code, String identifier, String label, String logo, Boolean isMenu, String accessPath,
            Integer order, Modules parentModule, List<UserProfile> profiles, List<MenuOption> menuOptions) {
        this.code = code;
        this.identifier = identifier;
        this.label = label;
        this.logo = logo;
        this.isMenu = isMenu;
        this.accessPath = accessPath;
        this.order = order;
        this.parentModule = parentModule;
        this.profiles = profiles;
        this.menuOptions = menuOptions;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Modules() {
    }

}
