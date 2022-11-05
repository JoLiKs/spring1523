package com.example.spring152.controllers;

import com.example.spring152.models.ItemRepo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("/item")
public class ItemController {

    final ItemRepo itemRepo;

    public ItemController(ItemRepo itemRepo) {
        this.itemRepo = itemRepo;
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteItem(@PathVariable long id) {
itemRepo.deleteById(id);
return new RedirectView("/admin/edit");
    }
    @GetMapping("/edit/{id}")
    public RedirectView editItem(@PathVariable long id) {

        return new RedirectView("/admin/edit");
    }

    @PostMapping("/")
    @ResponseBody
    public String ajax(@RequestParam long id,@RequestParam String param) {
if (isNumeric(param)) itemRepo.setPrice(param, id);
else itemRepo.setName(param, id);
        return param;
    }
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
