package com.vladbrown.web.conroller.pages;

import com.vladbrown.core.model.dao.ItemDao;
import com.vladbrown.core.model.domain.Item;
import com.vladbrown.web.conroller.dto.ItemInfoDto;
import com.vladbrown.web.conroller.dto.PersonInfoDto;
import com.vladbrown.web.conroller.utils.DomainEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(value = "/itemDetails")
public class ItemDetailsController {

    @Autowired
    ItemDao itemDao;

    @GetMapping(value = "/{itemId}")
    public String showExhibitDetails(@PathVariable Long itemId, Model model) {
        model.addAttribute("item", itemDao.getItem(itemId));
        return "/itemDetails";
    }

    @PutMapping(value = "/{itemId}")
    public String updateItem(@PathVariable Long itemId, PersonInfoDto personInfoDto, ItemInfoDto itemInfoDto, Model model) {
        itemInfoDto.setItemId(itemId);
        Item item = DomainEntityBuilder.buildItem(itemInfoDto);
        item.setAuthor(DomainEntityBuilder.buildPerson(personInfoDto));
        itemDao.save(item);
        return "redirect:/exhibitDetails/" + itemId;
    }

    @DeleteMapping(value = "/{itemiD}")
    public String deleteItem(@PathVariable Long itemiD) {
        itemDao.delete(itemiD);
        return "redirect:/itemList";
    }

}

