package com.example.spring152.controllers;

import com.example.spring152.models.ItemModel;
import com.example.spring152.models.ItemRepo;
import com.example.spring152.models.enums.ItemEnum;
import com.example.spring152.services.FirebaseImageService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/allItems")
public class AllItemsControllers {
    @Autowired
    ItemRepo itemRepo;

   @Autowired
   FirebaseImageService fireBaseImageService;

    @GetMapping
    public String getPage(Model model) {
        List<ItemModel> list = itemRepo.findAll();
        list.forEach(i -> i.setUrl(fireBaseImageService.getImgUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }

    @PostMapping
    public String setFilter(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "smax", required = false) String smax,
                            @RequestParam(value = "smin", required = false) String smin,
                            @RequestParam(value = "cur", required = false) String cur,
                            Model model) {
        List<ItemModel> list = itemRepo.findAll();
        if (cur != null) {
            for (ItemModel itemModel : list) {
                itemModel.setCur(cur);
            }
        }
        if (name != null) {
            list = list.stream().filter(i -> (i.getName().contains(name))).collect(Collectors.toList());
            list.forEach(i -> i.setUrl(fireBaseImageService.getImgUrl(i.getUrl())));
            model.addAttribute("items", list);
            return "allItems";
        }
        if (smin != null && smax != null) {
            int max = 0;
            int min = 0;
            if (smax.length() > 0 && smin.length() == 0) {
                max = Integer.parseInt(smax);
                int finalMax = max;
                list = list.stream().filter(i -> Integer.parseInt(i.getPrice()) <= finalMax).collect(Collectors.toList());
                list.forEach(i -> i.setUrl(fireBaseImageService.getImgUrl(i.getUrl())));
                model.addAttribute("items", list);
                return "allItems";
            }
            if (smin.length() > 0 && smax.length() == 0) {
                min = Integer.parseInt(smin);
                int finalMin = min;
                list = list.stream().filter(i -> Integer.parseInt(i.getPrice()) >= finalMin).collect(Collectors.toList());
                list.forEach(i -> i.setUrl(fireBaseImageService.getImgUrl(i.getUrl())));
                model.addAttribute("items", list);
                return "allItems";
            }
            min = Integer.parseInt(smin);
            max = Integer.parseInt(smax);
            int finalMin1 = min;
            int finalMax1 = max;
            list = list.stream().filter(i -> Integer.parseInt(i.getPrice()) >= finalMin1 && Integer.parseInt(i.getPrice()) <= finalMax1).collect(Collectors.toList());
            list.forEach(i -> i.setUrl(fireBaseImageService.getImgUrl(i.getUrl())));
            model.addAttribute("items", list);
            return "allItems";
        }
        list.forEach(i -> i.setUrl(fireBaseImageService.getImgUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }
    @GetMapping("/cars")
    public String getCars(Model model) {
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i -> i.getItemEnum().equals(ItemEnum.AUTOS)).collect(Collectors.toList());
        list.forEach(i -> i.setUrl(fireBaseImageService.getImgUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }
    @GetMapping("/cats")
    public String getCats(Model model) {
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i -> i.getItemEnum().equals(ItemEnum.CATS)).collect(Collectors.toList());
        list.forEach(i -> i.setUrl(fireBaseImageService.getImgUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }
    @GetMapping("/drugs")
    public String getDrugs(Model model) {
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i -> i.getItemEnum().equals(ItemEnum.DRUGS)).collect(Collectors.toList());
        list.forEach(i -> i.setUrl(fireBaseImageService.getImgUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }
    @GetMapping("/hometechniks")
    public String getHomeTechniks(Model model) {
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i -> i.getItemEnum().equals(ItemEnum.HOMETECHNICS)).collect(Collectors.toList());
        list.forEach(i -> i.setUrl(fireBaseImageService.getImgUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }
    @GetMapping("/other")
    public String getOther(Model model) {
        List<ItemModel> list = itemRepo.findAll();
        list = list.stream().filter(i -> i.getItemEnum().equals(ItemEnum.OTHER)).collect(Collectors.toList());
        list.forEach(i -> i.setUrl(fireBaseImageService.getImgUrl(i.getUrl())));
        model.addAttribute("items", list);
        return "allItems";
    }
}
