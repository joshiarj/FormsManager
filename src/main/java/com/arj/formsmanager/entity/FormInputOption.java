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
@Table(name = "tbl_forminputoptions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormInputOption.findAll", query = "SELECT f FROM FormInputOption f"),
    @NamedQuery(name = "FormInputOption.findByFormInputOptionId", query = "SELECT f FROM FormInputOption f WHERE f.formInputOptionId = :formInputOptionId"),
    @NamedQuery(name = "FormInputOption.findByFormInputOptionValue", query = "SELECT f FROM FormInputOption f WHERE f.formInputOptionValue = :formInputOptionValue")})
public class FormInputOption implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "form_input_option_id")
    private Integer formInputOptionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "form_input_option_value")
    private String formInputOptionValue;
    @JoinColumn(name = "form_option_id", referencedColumnName = "form_option_id")
    @ManyToOne(optional = false)
    private FormOption formOptionId;

    public FormInputOption() {
    }

    public FormInputOption(Integer formInputOptionId) {
        this.formInputOptionId = formInputOptionId;
    }

    public FormInputOption(Integer formInputOptionId, String formInputOptionValue) {
        this.formInputOptionId = formInputOptionId;
        this.formInputOptionValue = formInputOptionValue;
    }

    public Integer getFormInputOptionId() {
        return formInputOptionId;
    }

    public void setFormInputOptionId(Integer formInputOptionId) {
        this.formInputOptionId = formInputOptionId;
    }

    public String getFormInputOptionValue() {
        return formInputOptionValue;
    }

    public void setFormInputOptionValue(String formInputOptionValue) {
        this.formInputOptionValue = formInputOptionValue;
    }

    public FormOption getFormOptionId() {
        return formOptionId;
    }

    public void setFormOptionId(FormOption formOptionId) {
        this.formOptionId = formOptionId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formInputOptionId != null ? formInputOptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormInputOption)) {
            return false;
        }
        FormInputOption other = (FormInputOption) object;
        if ((this.formInputOptionId == null && other.formInputOptionId != null) || (this.formInputOptionId != null && !this.formInputOptionId.equals(other.formInputOptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.arj.formsmanager.entity.FormInputOption[ formInputOptionId=" + formInputOptionId + " ]";
    }
    
}
