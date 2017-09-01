/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arj.formsmanager.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Zeppelin
 */
@Entity
@Table(name = "tbl_formresponses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FormResponse.findAll", query = "SELECT f FROM FormResponse f"),
    @NamedQuery(name = "FormResponse.findByFormResponseId", query = "SELECT f FROM FormResponse f WHERE f.formResponseId = :formResponseId"),
    @NamedQuery(name = "FormResponse.findByFormResponseAddedDate", query = "SELECT f FROM FormResponse f WHERE f.formResponseAddedDate = :formResponseAddedDate"),
    @NamedQuery(name = "FormResponse.findByFormResponseIpAddress", query = "SELECT f FROM FormResponse f WHERE f.formResponseIpAddress = :formResponseIpAddress")})
public class FormResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "form_response_id")
    private Integer formResponseId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "form_response_added_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date formResponseAddedDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "form_response_ip_address")
    private String formResponseIpAddress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formResponseId")
    private List<FormResponseOption> formResponseOptionList;
    @JoinColumn(name = "form_id", referencedColumnName = "form_id")
    @ManyToOne(optional = false)
    private Form formId;

    public FormResponse() {
    }

    public FormResponse(Integer formResponseId) {
        this.formResponseId = formResponseId;
    }

    public FormResponse(Integer formResponseId, Date formResponseAddedDate, String formResponseIpAddress) {
        this.formResponseId = formResponseId;
        this.formResponseAddedDate = formResponseAddedDate;
        this.formResponseIpAddress = formResponseIpAddress;
    }

    public Integer getFormResponseId() {
        return formResponseId;
    }

    public void setFormResponseId(Integer formResponseId) {
        this.formResponseId = formResponseId;
    }

    public Date getFormResponseAddedDate() {
        return formResponseAddedDate;
    }

    public void setFormResponseAddedDate(Date formResponseAddedDate) {
        this.formResponseAddedDate = formResponseAddedDate;
    }

    public String getFormResponseIpAddress() {
        return formResponseIpAddress;
    }

    public void setFormResponseIpAddress(String formResponseIpAddress) {
        this.formResponseIpAddress = formResponseIpAddress;
    }

    @XmlTransient
    public List<FormResponseOption> getFormResponseOptionList() {
        return formResponseOptionList;
    }

    public void setFormResponseOptionList(List<FormResponseOption> formResponseOptionList) {
        this.formResponseOptionList = formResponseOptionList;
    }

    public Form getFormId() {
        return formId;
    }

    public void setFormId(Form formId) {
        this.formId = formId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (formResponseId != null ? formResponseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormResponse)) {
            return false;
        }
        FormResponse other = (FormResponse) object;
        if ((this.formResponseId == null && other.formResponseId != null) || (this.formResponseId != null && !this.formResponseId.equals(other.formResponseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.arj.formsmanager.entity.FormResponse[ formResponseId=" + formResponseId + " ]";
    }
    
}
