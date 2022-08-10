package com.example.usermanager.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class User {
    protected int id;
    @NotEmpty(message = "Tên không được bỏ trống")
    @Length(min = 3, max = 10 , message = "Độ dài của tên phải từ 3-10 ký tự ")
    protected String name;
    @NotEmpty(message = "Email không được bỏ trống")
    @Email(message = "Email phải đúng định dạng")
    protected String email;
    protected int idCountry;

    public User() {}

    public User(int id, String name, String email, int idCountry) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.idCountry = idCountry;
    }

    public User( String name, String email, int idCountry) {
        this.name = name;
        this.email = email;
        this.idCountry = idCountry;
    }

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;

    }


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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }
}
