package com.vladbrown.web.conroller.pages;

import com.vladbrown.core.model.dao.MovementDao;
import com.vladbrown.web.conroller.dto.ExhibitionInfoDto;
import com.vladbrown.web.conroller.dto.ItemMovementDto;
import com.vladbrown.web.conroller.dto.PersonInfoDto;
import com.vladbrown.web.conroller.utils.DomainEntityBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/addMovement")
public class AddItemMovementPageController {

    @Autowired
    MovementDao movementDao;

    @GetMapping
    public String getForm() {
        return "addMovement";
    }

    @PostMapping
    public String addItem(ItemMovementDto itemMovementDto, ExhibitionInfoDto exhibitionInfoDto, PersonInfoDto personInfoDto) {

        var movement = DomainEntityBuilder.buildItemMovement(itemMovementDto);
        var person = DomainEntityBuilder.buildPerson(personInfoDto);
        var exhibition = DomainEntityBuilder.buildExhibition(exhibitionInfoDto);

        movement.setResponsiblePerson(person);
        movement.setExhibition(exhibition);

        movementDao.save(movement);

        //todo redirect to movementDetails
        return "redirect:/itemList";
    }
}
