package com.vladbrown.web.conroller.dto;

import org.springframework.stereotype.Controller;

@Controller
public class ExhibitionInfoDto {

    private String exhibitionName;

    private String exhibitionAddress;

    private String exhibitionPhoneNumber;

    public String getExhibitionName() {
        return exhibitionName;
    }

    public void setExhibitionName(String exhibitionName) {
        this.exhibitionName = exhibitionName;
    }

    public String getExhibitionAddress() {
        return exhibitionAddress;
    }

    public void setExhibitionAddress(String exhibitionAddress) {
        this.exhibitionAddress = exhibitionAddress;
    }

    public String getExhibitionPhoneNumber() {
        return exhibitionPhoneNumber;
    }

    public void setExhibitionPhoneNumber(String exhibitionPhoneNumber) {
        this.exhibitionPhoneNumber = exhibitionPhoneNumber;
    }
}
