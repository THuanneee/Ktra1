package com.stu.employeemanager.models;

import java.io.Serializable;

public class EmployeeModel implements Serializable {
    String id;
    String name;
    String departmentId;
    String chucvu;
    byte[] image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setchucvu(String name) {
        this.chucvu = chucvu;
    }

    public String getChucvu() {
        return chucvu;
    }


    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public byte[] getImage() {
        return image;
    }

    public String getSdt() {
        return sdt;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeModel(String id, String name, String departmentId, byte[] image, String sdt, String email, String chucvu) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.image = image;
        this.sdt = sdt;
        this.email = email;
        this.chucvu
                = chucvu;
    }

    String sdt;
    String email;

}
