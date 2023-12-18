package com.levi.employeemanager.models;

public class DepartmentModel {
    String id;
    String name;

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

    public DepartmentModel(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
