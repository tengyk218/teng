package com.ncme.auth.model;

import lombok.Data;

/**
 * Created by ralap on 2017/11/9.
 */
@Data
public class UserModel {

    private int id;
    private String name;
    private String password;
    private String roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
