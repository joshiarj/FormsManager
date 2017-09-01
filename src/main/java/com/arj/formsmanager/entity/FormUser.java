/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arj.formsmanager.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Zeppelin
 */
@Entity
@Table(name = "tbl_formusers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormUser.findAll", query = "SELECT f FROM FormUser f"),
    @NamedQuery(name = "FormUser.findByFormUserId", query = "SELECT f FROM FormUser f WHERE f.formUserId = :formUserId"),
    @NamedQuery(name = "FormUser.findByFormUserPermissions", query = "SELECT f FROM FormUser f WHERE f.formUserPermissions = :formUserPermissions")})
public class FormUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "form_user_id")
    private Integer formUserId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "form_user_permissions")
    private String formUserPermissions;
    @JoinColumn(name = "form_id", referencedColumnName = "form_id")
    @ManyToOne(optional = false)
    private Form formId;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;

    public FormUser() {
    }

    public FormUser(Integer formUserId) {
        this.formUserId = formUserId;
    }

    public FormUser(Integer formUserId, String formUserPermissions) {
        this.formUserId = formUserId;
        this.formUserPermissions = formUserPermissions;
    }

    public Integer getFormUserId() {
        return formUserId;
    }

    public void setFormUserId(Integer formUserId) {
        this.formUserId = formUserId;
    }

    public String getFormUserPermissions() {
        return formUserPermissions;
    }

    public void setFormUserPermissions(String formUserPermissions) {
        this.formUserPermissions = formUserPermissions;
    }

    public Form getFormId() {
        return formId;
    }

    public void setFormId(Form formId) {
        this.formId = formId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formUserId != null ? formUserId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormUser)) {
            return false;
        }
        FormUser other = (FormUser) object;
        if ((this.formUserId == null && other.formUserId != null) || (this.formUserId != null && !this.formUserId.equals(other.formUserId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.arj.formsmanager.entity.FormUser[ formUserId=" + formUserId + " ]";
    }
    
}
