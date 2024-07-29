package com.api.schoolproject.dto;

public class SchoolDto {
    private String id;
    private String name;
    private String email;

    public SchoolDto(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public SchoolDto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}