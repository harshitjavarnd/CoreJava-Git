package com.exam.DAO;

public class EmployeeDAO {
    private int id;
    private String name;
    private String phone;

    public EmployeeDAO(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
