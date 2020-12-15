package com.vladbrown.web.conroller.utils;

import com.vladbrown.core.model.domain.*;
import com.vladbrown.web.conroller.dto.ExhibitionInfoDto;
import com.vladbrown.web.conroller.dto.ItemInfoDto;
import com.vladbrown.web.conroller.dto.ItemMovementDto;
import com.vladbrown.web.conroller.dto.PersonInfoDto;

import java.time.LocalDate;

public abstract class DomainEntityBuilder {

    public static Item buildItem(ItemInfoDto itemInfoDto) {
        var item = new Item();
        item.setId(itemInfoDto.getItemId());
        item.setName(itemInfoDto.getItemName());
        item.setInventoryNumber(itemInfoDto.getInventoryNumber());
        item.setCreationDate(parseDate(itemInfoDto.getCreationDate()));

        item.setAnnotation(itemInfoDto.getAnnotation());

        var fund = new Fund();
        fund.setName(itemInfoDto.getFundName());
        item.setFund(fund);

        var museumSet = new MuseumSet();
        museumSet.setName(itemInfoDto.getSetName());
        item.setMuseumSet(museumSet);

        return item;
    }

    public static Person buildPerson(PersonInfoDto personInfoDto) {
        var person = new Person();
        person.setId(personInfoDto.getPersonId());
        person.setFirstName(personInfoDto.getFirstName());
        person.setMiddleName(personInfoDto.getMiddleName());
        person.setSecondName(personInfoDto.getSecondName());
        return person;
    }

    public static ItemMovement buildItemMovement(ItemMovementDto itemMovementDto) {
        var itemMovement = new ItemMovement();
        itemMovement.setAcceptDate(parseDate(itemMovementDto.getAcceptDate()));
        itemMovement.setTransferDate(parseDate(itemMovementDto.getTransferDate()));
        itemMovement.setReturnDate(parseDate(itemMovementDto.getReturnDate()));

        var set = new MuseumSet();
        set.setName(itemMovementDto.getMuseumSetName());
        itemMovement.setMuseumSet(set);

        return itemMovement;
    }

    public static Exhibition buildExhibition(ExhibitionInfoDto exhibitionInfoDto) {
        var exhibition = new Exhibition();
        exhibition.setName(exhibitionInfoDto.getExhibitionName());
        exhibition.setAddress(exhibitionInfoDto.getExhibitionAddress());
        exhibition.setPhoneNumber(exhibitionInfoDto.getExhibitionPhoneNumber());
        return exhibition;
    }

    public static LocalDate parseDate(String inputDate) {
        String[] strings = inputDate.split("-");
        String year = strings[0];
        String month = strings[1];
        String day = strings[2];
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }
}
