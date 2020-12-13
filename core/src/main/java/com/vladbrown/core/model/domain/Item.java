package com.vladbrown.core.model.domain;

import java.time.LocalDate;
import java.util.Date;

public class Item {

    private Long id;

    private Long inventoryNumber;

    private String name;

    private LocalDate creationDate;

    private Person author;

    private MuseumSet museumSet;

    private Fund fund;

    private String annotation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(Long inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    public MuseumSet getMuseumSet() {
        return museumSet;
    }

    public void setMuseumSet(MuseumSet museumSet) {
        this.museumSet = museumSet;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", inventoryNumber=" + inventoryNumber +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", author=" + author +
                ", museumSet=" + museumSet +
                ", fund=" + fund +
                ", annotation='" + annotation + '\'' +
                '}';
    }
}
