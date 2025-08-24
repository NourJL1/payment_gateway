package com.model;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "USERS_PROFILE_MENUOPTION")

public class UserProfileMenuOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "UPM_UPR_CODE")
    private UserProfile profile;

    @ManyToOne
    @JoinColumn(name = "UPM_MOP_CODE")
    private MenuOption menuOption;

    @Column(name = "UPM_VIEW_ACCE")
    private Boolean canAccess;

    @Column(name = "UPM_VIEW_INSE")
    private Boolean canInsert;

    @Column(name = "UPM_VIEW_UPDA")
    private Boolean canUpdate;

    @Column(name = "UPM_VIEW_DELE")
    private Boolean canDelete;

    @Column(name = "UPM_VIEW_EDIT")
    private Boolean canEdit;

    @Column(name = "UPM_VIEW_PRIN")
    private Boolean canPrint;

    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public MenuOption getMenuOption() {
        return menuOption;
    }

    public void setMenuOption(MenuOption menuOption) {
        this.menuOption = menuOption;
    }

    public Boolean getCanAccess() {
        return canAccess;
    }

    public void setCanAccess(Boolean canAccess) {
        this.canAccess = canAccess;
    }

    public Boolean getCanInsert() {
        return canInsert;
    }

    public void setCanInsert(Boolean canInsert) {
        this.canInsert = canInsert;
    }

    public Boolean getCanUpdate() {
        return canUpdate;
    }

    public void setCanUpdate(Boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public Boolean getCanDelete() {
        return canDelete;
    }

    public void setCanDelete(Boolean canDelete) {
        this.canDelete = canDelete;
    }

    public Boolean getCanEdit() {
        return canEdit;
    }

    public void setCanEdit(Boolean canEdit) {
        this.canEdit = canEdit;
    }

    public Boolean getCanPrint() {
        return canPrint;
    }

    public void setCanPrint(Boolean canPrint) {
        this.canPrint = canPrint;
    }

    public UserProfileMenuOption(Integer id, UserProfile profile, MenuOption menuOption, Boolean canAccess,
            Boolean canInsert, Boolean canUpdate, Boolean canDelete, Boolean canEdit, Boolean canPrint) {
        this.id = id;
        this.profile = profile;
        this.menuOption = menuOption;
        this.canAccess = canAccess;
        this.canInsert = canInsert;
        this.canUpdate = canUpdate;
        this.canDelete = canDelete;
        this.canEdit = canEdit;
        this.canPrint = canPrint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserProfileMenuOption() {
    }

}
