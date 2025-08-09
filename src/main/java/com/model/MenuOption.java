package com.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "MENU_OPTION", uniqueConstraints = { @UniqueConstraint(columnNames = "MOP_CODE"),
		@UniqueConstraint(columnNames = "MOP_IDEN")
})
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
    private Module module;

    @OneToMany(mappedBy = "menuOption")
    @JsonIgnore
    private List<UserProfileMenuOption> profileMenuOptions;

    @PrePersist
	public void onCreate() {
		this.identifier = "MOP-" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmm")) + "-"
				+ UUID.randomUUID().toString().substring(0, 4).toUpperCase();
	}

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

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public List<UserProfileMenuOption> getProfileMenuOptions() {
        return profileMenuOptions;
    }

    public void setProfileMenuOptions(List<UserProfileMenuOption> profileMenuOptions) {
        this.profileMenuOptions = profileMenuOptions;
    }

    public MenuOption(Integer code, String identifier, String label, MenuOption parentOption, String formName,
            Module module, List<UserProfileMenuOption> profileMenuOptions) {
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
