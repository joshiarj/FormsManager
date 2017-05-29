package com.arj.formsmanager.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "tbl_forms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Form.findAll", query = "SELECT f FROM Form f"),
    @NamedQuery(name = "Form.findByFormId", query = "SELECT f FROM Form f WHERE f.formId = :formId"),
    @NamedQuery(name = "Form.findByFormTitle", query = "SELECT f FROM Form f WHERE f.formTitle = :formTitle"),
    @NamedQuery(name = "Form.findByFormDescription", query = "SELECT f FROM Form f WHERE f.formDescription = :formDescription"),
    @NamedQuery(name = "Form.findByStatus", query = "SELECT f FROM Form f WHERE f.status = :status")})
public class Form implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "form_id")
    private Integer formId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "form_title")
    private String formTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "form_description")
    private String formDescription;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private boolean status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formId")
    private List<FormUser> formUserList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formId")
    private List<FormOption> formOptionList;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formId")
    private List<FormResponse> formResponseList;

    public Form() {
    }

    public Form(Integer formId) {
        this.formId = formId;
    }

    public Form(Integer formId, String formTitle, String formDescription, boolean status) {
        this.formId = formId;
        this.formTitle = formTitle;
        this.formDescription = formDescription;
        this.status = status;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getFormTitle() {
        return formTitle;
    }

    public void setFormTitle(String formTitle) {
        this.formTitle = formTitle;
    }

    public String getFormDescription() {
        return formDescription;
    }

    public void setFormDescription(String formDescription) {
        this.formDescription = formDescription;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @XmlTransient
    public List<FormUser> getFormUserList() {
        return formUserList;
    }

    public void setFormUserList(List<FormUser> formUserList) {
        this.formUserList = formUserList;
    }

    @XmlTransient
    public List<FormOption> getFormOptionList() {
        return formOptionList;
    }

    public void setFormOptionList(List<FormOption> formOptionList) {
        this.formOptionList = formOptionList;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @XmlTransient
    public List<FormResponse> getFormResponseList() {
        return formResponseList;
    }

    public void setFormResponseList(List<FormResponse> formResponseList) {
        this.formResponseList = formResponseList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formId != null ? formId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Form)) {
            return false;
        }
        Form other = (Form) object;
        if ((this.formId == null && other.formId != null) || (this.formId != null && !this.formId.equals(other.formId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.arj.formsmanager.controller.Form[ formId=" + formId + " ]";
    }
    
}
