package com.example.spring152.models;


import lombok.Data;
import org.hibernate.annotations.Tables;

import javax.persistence.*;

@Entity
@Table(name = "JOJO2")
@Data
public class PersonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "last_jojo")
    private String lastName;

    @Column(name = "person_name")
    private String name;

    @Column(name = "age")
    private int age;

    public PersonModel(long id, String lastName, String name, int age) {
        this.id = id;
        this.lastName = lastName;
        this.name = name;
        this.age = age;
    }

    public PersonModel() {

    }

    @Override
    public String toString() {
        return "PersonModel{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

