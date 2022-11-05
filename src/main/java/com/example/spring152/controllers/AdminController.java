package com.example.spring152.controllers;

import com.example.spring152.models.ItemModel;
import com.example.spring152.models.ItemRepo;
import com.example.spring152.services.FirebaseImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    final ItemRepo itemRepo;
    final FirebaseImageService firebaseImageService;

    public AdminController(ItemRepo itemRepo, FirebaseImageService firebaseImageService) {
        this.itemRepo = itemRepo;
        this.firebaseImageService = firebaseImageService;
    }

    @GetMapping
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/edit")
    public String editItems(Model model) {
        List<ItemModel> list = itemRepo.findAll();
        list.forEach(i -> i.setUrl(firebaseImageService.getImgUrl(i.getUrl())));
        model.addAttribute("list", list);
        return "editItem";
    }
}
