package com.arj.formsmanager.dto;

import com.arj.formsmanager.entity.User;

public class FormDTO {
    private String title, description;
    private User userId;

    public FormDTO() {
    }

    public FormDTO(String title, String description, User userId) {
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }
    
}
