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
@Table(name = "tbl_formresponseoptions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormResponseOption.findAll", query = "SELECT f FROM FormResponseOption f"),
    @NamedQuery(name = "FormResponseOption.findByFormResponseOptionId", query = "SELECT f FROM FormResponseOption f WHERE f.formResponseOptionId = :formResponseOptionId"),
    @NamedQuery(name = "FormResponseOption.findByFormResponseOptionValue", query = "SELECT f FROM FormResponseOption f WHERE f.formResponseOptionValue = :formResponseOptionValue")})
public class FormResponseOption implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "form_response_option_id")
    private Integer formResponseOptionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "form_response_option_value")
    private String formResponseOptionValue;
    @JoinColumn(name = "form_response_id", referencedColumnName = "form_response_id")
    @ManyToOne(optional = false)
    private FormResponse formResponseId;
    @JoinColumn(name = "form_field_id", referencedColumnName = "form_field_id")
    @ManyToOne(optional = false)
    private FormField formFieldId;

    public FormResponseOption() {
    }

    public FormResponseOption(Integer formResponseOptionId) {
        this.formResponseOptionId = formResponseOptionId;
    }

    public FormResponseOption(Integer formResponseOptionId, String formResponseOptionValue) {
        this.formResponseOptionId = formResponseOptionId;
        this.formResponseOptionValue = formResponseOptionValue;
    }

    public Integer getFormResponseOptionId() {
        return formResponseOptionId;
    }

    public void setFormResponseOptionId(Integer formResponseOptionId) {
        this.formResponseOptionId = formResponseOptionId;
    }

    public String getFormResponseOptionValue() {
        return formResponseOptionValue;
    }

    public void setFormResponseOptionValue(String formResponseOptionValue) {
        this.formResponseOptionValue = formResponseOptionValue;
    }

    public FormResponse getFormResponseId() {
        return formResponseId;
    }

    public void setFormResponseId(FormResponse formResponseId) {
        this.formResponseId = formResponseId;
    }

    public FormField getFormFieldId() {
        return formFieldId;
    }

    public void setFormFieldId(FormField formFieldId) {
        this.formFieldId = formFieldId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formResponseOptionId != null ? formResponseOptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormResponseOption)) {
            return false;
        }
        FormResponseOption other = (FormResponseOption) object;
        if ((this.formResponseOptionId == null && other.formResponseOptionId != null) || (this.formResponseOptionId != null && !this.formResponseOptionId.equals(other.formResponseOptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.arj.formsmanager.entity.FormResponseOption[ formResponseOptionId=" + formResponseOptionId + " ]";
    }
    
}
