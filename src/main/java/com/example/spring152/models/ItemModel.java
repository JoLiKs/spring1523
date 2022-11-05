package com.example.spring152.models;

import com.example.spring152.controllers.AllItemsControllers;
import com.example.spring152.controllers.GetPriceThread;
import com.example.spring152.controllers.ResponseThread;
import com.example.spring152.models.enums.ItemEnum;
import lombok.Data;

import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(name = "shop_item")
@Data
public class ItemModel {
    public static String res;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "item_name")
    private String name;

    @Column(name = "description")
    private String desc;

    @Column(name = "price")
    private String price;

    @Column(name = "url")
    private String url;

    private String cur;

    @Enumerated
    @Column(name = "item_type")
    ItemEnum itemEnum;

    public String getCur() {
        if (cur == null) return "RUB";
        return cur;
    }

    public String getPrice(String cur) {
       if (!cur.equals("USD") && !cur.equals("EUR")) return this.price+" РУБ";
        new GetPriceThread(price, cur).start();
        try {
            Thread.sleep(1450);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}

