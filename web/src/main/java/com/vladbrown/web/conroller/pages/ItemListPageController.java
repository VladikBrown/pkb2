package com.vladbrown.web.conroller.pages;

import com.vladbrown.core.model.dao.ItemDao;
import com.vladbrown.core.model.dao.SetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/itemList")
public class ItemListPageController {

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private SetDao setDao;

    @GetMapping
    public String showExhibitList(Model model) {
        var items = itemDao.findAll();
        model.addAttribute("items", items);
        return "itemList";
    }

    @GetMapping("/ofMuseumSet/{setId}")
    public String showItemOfMuseumSetList(@PathVariable Long setId, Model model) {
        var items = setDao.getSetOfItemsBySetId(setId);
        model.addAttribute("items", items);
        return "itemList";
    }
}
