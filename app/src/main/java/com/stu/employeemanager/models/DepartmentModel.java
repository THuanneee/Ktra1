package com.stu.employeemanager.models;

import java.io.Serializable;

public class DepartmentModel  implements Serializable {
    String id;
    String name;
    String email;
    String web;
    String logo;
    String diachi;
    String sdt;
    String madonvicha;

    public DepartmentModel(String id, String name, String email, String web, String logo, String diachi, String sdt, String madonvicha) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.web = web;
        this.logo = logo;
        this.diachi = diachi;
        this.sdt = sdt;
        this.madonvicha = madonvicha;
    }


    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMadonvicha() {
        return madonvicha;
    }

    public void setMadonvicha(String madonvicha) {
        this.madonvicha = madonvicha;
    }
}
