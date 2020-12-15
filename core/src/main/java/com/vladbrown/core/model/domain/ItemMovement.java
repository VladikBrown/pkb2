package com.vladbrown.core.model.domain;

import java.time.LocalDate;

public class ItemMovement {

    private Long id;

    private MuseumSet museumSet;

    private LocalDate acceptDate;

    private LocalDate transferDate;

    private LocalDate returnDate;

    private Exhibition exhibition;

    private Person responsiblePerson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MuseumSet getMuseumSet() {
        return museumSet;
    }

    public void setMuseumSet(MuseumSet museumSet) {
        this.museumSet = museumSet;
    }

    public LocalDate getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(LocalDate acceptDate) {
        this.acceptDate = acceptDate;
    }

    public LocalDate getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(LocalDate transferDate) {
        this.transferDate = transferDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }

    public Person getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(Person responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }
}

