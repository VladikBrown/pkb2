package com.vladbrown.web.conroller.pages;

import com.vladbrown.core.model.dao.MovementDao;
import com.vladbrown.web.conroller.utils.DomainEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/movements")
public class ItemMovementController {

    @Autowired
    MovementDao movementDao;

    @GetMapping("/bySet/{setId}")
    public String showMovementsById(@PathVariable Long setId, Model model) {
        var items = movementDao.findAllBySetId(setId);
        model.addAttribute("setId", setId);
        model.addAttribute("items", items);
        return "movementList";
    }

    @GetMapping("/bySetAndDay/{setId}")
    public String showMovementsById(@PathVariable Long setId, @RequestParam String date, Model model) {
        var item = movementDao.getMovementOfSetByDate(setId, DomainEntityBuilder.parseDate(date));
        item.ifPresent(itemMovement -> model.addAttribute("item", itemMovement));
        return "exhibitionDetails";
    }
}
