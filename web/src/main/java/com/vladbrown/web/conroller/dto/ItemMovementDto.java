package com.vladbrown.web.conroller.dto;

public class ItemMovementDto {

    private String museumSetName;

    private String acceptDate;

    private String transferDate;

    private String returnDate;

    public String getMuseumSetName() {
        return museumSetName;
    }

    public void setMuseumSetName(String museumSetName) {
        this.museumSetName = museumSetName;
    }

    public String getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(String acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(String transferDate) {
        this.transferDate = transferDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
