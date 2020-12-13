package com.vladbrown.web.conroller.pages;

import com.vladbrown.core.model.dao.ItemDao;
import com.vladbrown.core.model.domain.Fund;
import com.vladbrown.core.model.domain.Item;
import com.vladbrown.core.model.domain.MuseumSet;
import com.vladbrown.core.model.domain.Person;
import com.vladbrown.web.conroller.dto.ItemRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

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
    public String addItem(Person person, ItemRegistrationDto itemRegistrationDto) {

        //validate first
        itemDao.save(buildItem(person, itemRegistrationDto));
        //make redirect to item details page
        return "redirect:/exhibitList";
    }

    private Item buildItem(Person person, ItemRegistrationDto itemRegistrationDto) {
        var item = new Item();
        item.setAuthor(person);

        Fund fund = new Fund();
        fund.setName(itemRegistrationDto.getFundName());
        item.setFund(fund);

        MuseumSet museumSet = new MuseumSet();
        museumSet.setName(itemRegistrationDto.getSetName());
        item.setMuseumSet(museumSet);

        item.setCreationDate(parseDate(itemRegistrationDto.getCreationDate()));

        item.setInventoryNumber(itemRegistrationDto.getInventoryNumber());

        item.setAnnotation(itemRegistrationDto.getAnnotation());

        item.setName(itemRegistrationDto.getItemName());

        return item;
    }

    private LocalDate parseDate(String inputDate) {
        String[] strings = inputDate.split("\\.");
        String day = strings[0];
        String month = strings[1];
        String year = strings[2];
        return LocalDate.of(Integer.parseInt(year) , Integer.parseInt(month), Integer.parseInt(day));
    }
}
