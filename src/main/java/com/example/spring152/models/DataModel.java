package com.example.spring152.models;

import com.example.spring152.controllers.GetPriceThread;
import com.example.spring152.models.enums.ItemEnum;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "data")
@Data
public class DataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "item_id")
    private long itemId;

    @Column(name = "person_name")
    private String name;

    @Column(name = "contact")
    private String contact;

}

