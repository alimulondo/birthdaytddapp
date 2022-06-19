package com.zaictronics.birthday;

public class Person {
    private String fname;
    private String lname;
    private String birthday;
    private String email;

    public Person(String fname, String lname, String birthday, String email) {
        this.fname = fname;
        this.lname = lname;
        this.birthday = birthday;
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
