package com.PracticeSpring.mysqldemo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class User {

    private int UserId;
    private  String name;
    private int age;
    private  String country;
    private Date timeOfInsertion;


    public User(int userId, String name, int age, String country, Date timeOfInsertion) {
        UserId = userId;
        this.name = name;
        this.age = age;
        this.country = country;
        this.timeOfInsertion = timeOfInsertion;
    }
}
