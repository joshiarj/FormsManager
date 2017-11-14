package com.arj.formsmanager.dto;

import com.arj.formsmanager.entity.User;

public class FormDTO {
    private String title, description, userId;
//    private User userId;

    public FormDTO() {
    }

    public FormDTO(String title, String description, String userId) {
        this.title = title;
        this.description = description;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
}
