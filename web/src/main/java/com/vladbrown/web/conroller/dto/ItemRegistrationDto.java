package com.vladbrown.web.conroller.dto;

public class ItemRegistrationDto {

    private String fundName;

    private String setName;

    private String itemName;

    private String creationDate;

    private String annotation;

    private Long inventoryNumber;

    public Long getInventoryNumber() {
        return inventoryNumber;
    }

    public void setInventoryNumber(Long inventoryNumber) {
        this.inventoryNumber = inventoryNumber;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @Override
    public String toString() {
        return "ItemRegistrationDto{" +
                "fundName='" + fundName + '\'' +
                ", setName='" + setName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", creationDay='" + creationDate + '\'' +
                ", annotation='" + annotation + '\'' +
                '}';
    }
}
