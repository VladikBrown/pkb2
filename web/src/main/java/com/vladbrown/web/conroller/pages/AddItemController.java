package com.vladbrown.web.conroller.pages;

import com.vladbrown.core.model.dao.ItemDao;
import com.vladbrown.core.model.domain.Item;
import com.vladbrown.web.conroller.dto.ItemInfoDto;
import com.vladbrown.web.conroller.dto.PersonInfoDto;
import com.vladbrown.web.conroller.utils.DomainEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/addItem")
public class AddItemController {

    @Autowired
    private ItemDao itemDao;

    @GetMapping
    public String getForm() {
        return "addItem";
    }

    @PostMapping
    public String addItem(PersonInfoDto personInfoDto, ItemInfoDto itemInfoDto) {

        //validate first
        Item item = DomainEntityBuilder.buildItem(itemInfoDto);
        item.setAuthor(DomainEntityBuilder.buildPerson(personInfoDto));
        itemDao.save(item);
        return "redirect:/itemList";
    }
}
