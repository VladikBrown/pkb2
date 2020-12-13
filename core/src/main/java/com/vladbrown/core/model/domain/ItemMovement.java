package com.vladbrown.core.model.domain;

import java.util.Date;

public class ItemMovement {

    private Long id;

    //private Long itemId;

    //private Long exhibitionId;

    private MuseumSet museumSet;

    private Date acceptDate;

    private Date trasferDate;

    private Date returnDate;

    private Exhibition exhibition;

    private Person resposiblePerson;
}
