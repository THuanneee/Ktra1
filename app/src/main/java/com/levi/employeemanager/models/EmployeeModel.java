package com.levi.employeemanager.models;

public class EmployeeModel {
    String id;
    String name;
    String departmentId;
    String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setImage(String image) {
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

    public String getImage() {
        return image;
    }

    public String getSdt() {
        return sdt;
    }

    public String getEmail() {
        return email;
    }

    public EmployeeModel(String id, String name, String departmentId, String image, String sdt, String email) {
        this.id = id;
        this.name = name;
        this.departmentId = departmentId;
        this.image = image;
        this.sdt = sdt;
        this.email = email;
    }

    String sdt;
    String email;
}
