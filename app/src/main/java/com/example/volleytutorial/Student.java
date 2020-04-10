package com.example.volleytutorial;

public class Student {

    private int rollNumber;
    private String name;
    private int age;
    private String sex;



    public Student(Integer rollNumber, String name, Integer age, String sex) {
        this.rollNumber=rollNumber;
        this.name=name;
        this.age=age;
        this.sex=sex;

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
    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getRollNumber() {
        return rollNumber;
    }

    public Student() {
        super();
    }
    public Student(String name, int age, String sex) {
        super();
        this.name = name;
        this.age = age;
        this.sex = sex;
    }
    @Override
    public String toString() {
        return "Student [rollNumber=" + rollNumber + ", name=" + name + ", age=" + age + ", sex=" + sex + "]";
    }



}