package com.arj.formsmanager.dto;

public class FormOptionDTO {
    private String formOptionDisplayOrder, formFieldName, formOptionType, fieldOptions;
    private boolean fieldRequired;

    public FormOptionDTO() {
    }

    public FormOptionDTO(String formOptionDisplayOrder, String formFieldName, String formOptionType, String fieldOptions, boolean fieldRequired) {
        this.formOptionDisplayOrder = formOptionDisplayOrder;
        this.formFieldName = formFieldName;
        this.formOptionType = formOptionType;
        this.fieldOptions = fieldOptions;
        this.fieldRequired = fieldRequired;
    }

    public String getFormOptionDisplayOrder() {
        return formOptionDisplayOrder;
    }

    public void setFormOptionDisplayOrder(String formOptionDisplayOrder) {
        this.formOptionDisplayOrder = formOptionDisplayOrder;
    }

    public String getFormFieldName() {
        return formFieldName;
    }

    public void setFormFieldName(String formFieldName) {
        this.formFieldName = formFieldName;
    }

    public String getFormOptionType() {
        return formOptionType;
    }

    public void setFormOptionType(String formOptionType) {
        this.formOptionType = formOptionType;
    }

    public String getFieldOptions() {
        return fieldOptions;
    }

    public void setFieldOptions(String fieldOptions) {
        this.fieldOptions = fieldOptions;
    }

    public boolean isFieldRequired() {
        return fieldRequired;
    }

    public void setFieldRequired(boolean fieldRequired) {
        this.fieldRequired = fieldRequired;
    }
    
}
