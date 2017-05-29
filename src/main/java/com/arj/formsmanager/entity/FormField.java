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
@Table(name = "tbl_formfields")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormField.findAll", query = "SELECT f FROM FormField f"),
    @NamedQuery(name = "FormField.findByFormFieldId", query = "SELECT f FROM FormField f WHERE f.formFieldId = :formFieldId"),
    @NamedQuery(name = "FormField.findByFormFieldName", query = "SELECT f FROM FormField f WHERE f.formFieldName = :formFieldName")})
public class FormField implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "form_field_id")
    private Integer formFieldId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "form_field_name")
    private String formFieldName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formFieldId")
    private List<FormOption> formOptionList;
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formFieldId")
    private List<FormResponseOption> formResponseOptionList;

    public FormField() {
    }

    public FormField(Integer formFieldId) {
        this.formFieldId = formFieldId;
    }

    public FormField(Integer formFieldId, String formFieldName) {
        this.formFieldId = formFieldId;
        this.formFieldName = formFieldName;
    }

    public Integer getFormFieldId() {
        return formFieldId;
    }

    public void setFormFieldId(Integer formFieldId) {
        this.formFieldId = formFieldId;
    }

    public String getFormFieldName() {
        return formFieldName;
    }

    public void setFormFieldName(String formFieldName) {
        this.formFieldName = formFieldName;
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
    public List<FormResponseOption> getFormResponseOptionList() {
        return formResponseOptionList;
    }

    public void setFormResponseOptionList(List<FormResponseOption> formResponseOptionList) {
        this.formResponseOptionList = formResponseOptionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formFieldId != null ? formFieldId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormField)) {
            return false;
        }
        FormField other = (FormField) object;
        if ((this.formFieldId == null && other.formFieldId != null) || (this.formFieldId != null && !this.formFieldId.equals(other.formFieldId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.arj.formsmanager.controller.FormField[ formFieldId=" + formFieldId + " ]";
    }
    
}
