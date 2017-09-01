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
@Table(name = "tbl_formoptions")
@XmlRootElement
//@NamedQueries({
//    @NamedQuery(name = "FormOption.findAll", query = "SELECT f FROM FormOption f"),
//    @NamedQuery(name = "FormOption.findByFormOptionId", query = "SELECT f FROM FormOption f WHERE f.formOptionId = :formOptionId"),
//    @NamedQuery(name = "FormOption.findByFormOptionDisplayOrder", query = "SELECT f FROM FormOption f WHERE f.formOptionDisplayOrder = :formOptionDisplayOrder"),
//    @NamedQuery(name = "FormOption.findByFormOptionType", query = "SELECT f FROM FormOption f WHERE f.formOptionType = :formOptionType"),
//    @NamedQuery(name = "FormOption.findByFormOptionTypeOptions", query = "SELECT f FROM FormOption f WHERE f.formOptionTypeOptions = :formOptionTypeOptions"),
//    @NamedQuery(name = "FormOption.findByFormOptionRequired", query = "SELECT f FROM FormOption f WHERE f.formOptionRequired = :formOptionRequired")})
public class FormOption implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "form_option_id")
    private Integer formOptionId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "form_option_display_order")
    private int formOptionDisplayOrder;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "form_option_type")
    private String formOptionType;
    @Size(max = 255)
    @Column(name = "form_option_type_options", nullable = true)
    private String formOptionTypeOptions;
    @Basic(optional = false)
    @NotNull
    @Column(name = "form_option_required")
    private boolean formOptionRequired;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "formOptionId")
    private List<FormInputOption> formInputOptionList;
    @JoinColumn(name = "form_id", referencedColumnName = "form_id")
    @ManyToOne(optional = false)
    private Form formId;
    @JoinColumn(name = "form_field_id", referencedColumnName = "form_field_id")
    @ManyToOne(optional = false)
    private FormField formFieldId;

    public FormOption() {
    }

    public FormOption(Integer formOptionId) {
        this.formOptionId = formOptionId;
    }

    public FormOption(Integer formOptionId, int formOptionDisplayOrder, String formOptionType, boolean formOptionRequired) {
        this.formOptionId = formOptionId;
        this.formOptionDisplayOrder = formOptionDisplayOrder;
        this.formOptionType = formOptionType;
        this.formOptionRequired = formOptionRequired;
    }

    public Integer getFormOptionId() {
        return formOptionId;
    }

    public void setFormOptionId(Integer formOptionId) {
        this.formOptionId = formOptionId;
    }

    public int getFormOptionDisplayOrder() {
        return formOptionDisplayOrder;
    }

    public void setFormOptionDisplayOrder(int formOptionDisplayOrder) {
        this.formOptionDisplayOrder = formOptionDisplayOrder;
    }

    public String getFormOptionType() {
        return formOptionType;
    }

    public void setFormOptionType(String formOptionType) {
        this.formOptionType = formOptionType;
    }

    public String getFormOptionTypeOptions() {
        return formOptionTypeOptions;
    }

    public void setFormOptionTypeOptions(String formOptionTypeOptions) {
        this.formOptionTypeOptions = formOptionTypeOptions;
    }

    public boolean getFormOptionRequired() {
        return formOptionRequired;
    }

    public void setFormOptionRequired(boolean formOptionRequired) {
        this.formOptionRequired = formOptionRequired;
    }

    @XmlTransient
    public List<FormInputOption> getFormInputOptionList() {
        return formInputOptionList;
    }

    public void setFormInputOptionList(List<FormInputOption> formInputOptionList) {
        this.formInputOptionList = formInputOptionList;
    }

    public Form getFormId() {
        return formId;
    }

    public void setFormId(Form formId) {
        this.formId = formId;
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
        hash += (formOptionId != null ? formOptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FormOption)) {
            return false;
        }
        FormOption other = (FormOption) object;
        if ((this.formOptionId == null && other.formOptionId != null) || (this.formOptionId != null && !this.formOptionId.equals(other.formOptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.arj.formsmanager.entity.FormOption[ formOptionId=" + formOptionId + " ]";
    }
    
}
