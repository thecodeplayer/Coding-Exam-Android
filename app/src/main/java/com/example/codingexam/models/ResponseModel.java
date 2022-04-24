package com.example.codingexam.models;

public class ResponseModel {
    private int age;
    private String full_name, email_address, mobile_number, gender, birthday;

    public ResponseModel(int age, String full_name, String email_address, String mobile_number, String gender, String birthday) {
        this.age = age;
        this.full_name = full_name;
        this.email_address = email_address;
        this.mobile_number = mobile_number;
        this.gender = gender;
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
