package com.vladbrown.web.conroller.pages;

import com.vladbrown.core.model.dao.SetDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/setList")
public class SetListPageController {

    @Autowired
    SetDao setDao;

    @GetMapping
    public String showSetList(Model model) {
        model.addAttribute("sets", setDao.findAll());
        return "setList";
    }
}
