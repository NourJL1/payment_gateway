package com.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "MENU_OPTION")
public class MenuOption {

    @Id
    @Column(name = "MOP_CODE")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer code;

    @Column(name = "MOP_IDEN")
    private String identifier;

    @Column(name = "MOP_LABE")
    private String label;

    @ManyToOne
    @JoinColumn(name = "MOP_PARE_CODE")
    private MenuOption parentOption;

    @Column(name = "MOP_FORM_NAME")
    private String formName;

    @ManyToOne
    @JoinColumn(name = "MOP_MOD_CODE")
    private Modules module;

    @OneToMany(mappedBy = "menuOption")
    private List<UserProfileMenuOption> profileMenuOptions;

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

    public MenuOption getParentOption() {
        return parentOption;
    }

    public void setParentOption(MenuOption parentOption) {
        this.parentOption = parentOption;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public Modules getModule() {
        return module;
    }

    public void setModule(Modules module) {
        this.module = module;
    }

    public List<UserProfileMenuOption> getProfileMenuOptions() {
        return profileMenuOptions;
    }

    public void setProfileMenuOptions(List<UserProfileMenuOption> profileMenuOptions) {
        this.profileMenuOptions = profileMenuOptions;
    }

    public MenuOption(Integer code, String identifier, String label, MenuOption parentOption, String formName,
            Modules module, List<UserProfileMenuOption> profileMenuOptions) {
        this.code = code;
        this.identifier = identifier;
        this.label = label;
        this.parentOption = parentOption;
        this.formName = formName;
        this.module = module;
        this.profileMenuOptions = profileMenuOptions;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public MenuOption() {
    }

}
