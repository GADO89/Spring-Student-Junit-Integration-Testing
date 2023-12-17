package com.spring.testing.model;

//Create Model Student

public class StudentDto {


private long id;

private String name;

private int age;

private String phone;

private boolean active;

    public StudentDto() {
    }

    public StudentDto(long id, String name, int age, String phone, boolean active) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.active = active;
    }

    public StudentDto(String name, int age, String phone, boolean active) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.active = active;
    }

    public long getId() {
    return id;
}

public void setId(long id) {
    this.id = id;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public int getAge() {
    return age;
}

public void setAge(int age) {
    this.age = age;
}

public String getPhone() {
    return phone;
}

public void setPhone(String phone) {
    this.phone = phone;
}

public boolean isActive() {
    return active;
}

public void setActive(boolean active) {
    this.active = active;
}
}
