package com.example.spring152.controllers;

import com.example.spring152.Print;
import com.example.spring152.models.DataModel;
import com.example.spring152.models.ItemModel;
import com.example.spring152.models.ItemRepo;
import com.example.spring152.models.RequestRepo;
import com.example.spring152.services.FirebaseImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/detailItem")
public class DetailItemController {
    static int id;
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    RequestRepo dataRepo;

    @Autowired
    FirebaseImageService firebaseImageService;



    @GetMapping("/{id}")
    public String detail(@PathVariable long id, Model model) {
        ItemModel itemModel = itemRepo.findById(id);
        this.id = (int) id;
        itemModel.setUrl(firebaseImageService.getImgUrl(itemModel.getUrl()));
        model.addAttribute("items", itemModel);
        return "detail";
    }
    @PostMapping("/post/{id}")
    public RedirectView setData( @PathVariable(value = "id", required = false) Long id,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "number", required = false) String contact) {
        DataModel dataModel = new DataModel();
        dataModel.setItemId(id);
        dataModel.setName(name);
        dataModel.setContact(contact);
        dataRepo.save(dataModel);
        return new RedirectView("/allItems");
    }
    @PostMapping("/{id}")
    public String setCur(@RequestParam(value = "cur", required = false) String cur,
                            Model model) {
        if (cur != null) {
                List<ItemModel> list = itemRepo.findAll();
                list.get(0).setCur(cur);
                model.addAttribute("items", list.get(0));
                return "detail";
        }
        List<ItemModel> list = itemRepo.findAll();
        model.addAttribute("items", list.get(0));
        return "detail";
        }
}