package com.example.spring152.controllers;

import com.example.spring152.models.ItemModel;
import com.example.spring152.models.ItemRepo;
import com.example.spring152.models.enums.ItemEnum;
import com.example.spring152.services.FirebaseImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/addItem")
public class AddItemController {
    @Autowired
    ItemRepo itemRepo;

    final FirebaseImageService firebaseImageService;

    public AddItemController(FirebaseImageService firebaseImageService) {
        this.firebaseImageService = firebaseImageService;
    }

    @GetMapping
    public String getAddItemPage() {
        return "addItem";
    }

    @PostMapping
    public RedirectView setData(@RequestParam String name,
                                @RequestParam String price,
                                @RequestParam String desk,
                                @RequestParam MultipartFile file,
                                @RequestParam String type) {
        ItemModel itemModel = new ItemModel();
        itemModel.setDesc(desk);
        itemModel.setName(name);
        itemModel.setPrice(price);
        String fileName = firebaseImageService.save(file);
        itemModel.setUrl(fileName);
        switch (type) {
            case "Автомобиль":
                itemModel.setItemEnum(ItemEnum.AUTOS);
                break;
            case "Котики":
                itemModel.setItemEnum(ItemEnum.CATS);
                break;
            case "Наркотики":
                itemModel.setItemEnum(ItemEnum.DRUGS);
                break;
            default:
                itemModel.setItemEnum(ItemEnum.OTHER);
                break;
        }
        itemRepo.save(itemModel);
        return new RedirectView("/");
    }
}
