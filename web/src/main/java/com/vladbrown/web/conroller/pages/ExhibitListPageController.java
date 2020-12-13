package com.vladbrown.web.conroller.pages;

import com.vladbrown.core.model.dao.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/exhibitList")
public class ExhibitListPageController {

    @Autowired
    private ItemDao itemDao;

    @GetMapping
    public String showExhibitList(Model model) {
        var items = itemDao.findAll();
        items.forEach(System.out::println);
        model.addAttribute("items", items);
        return "exhibitList";
    }
}
