package com.vladbrown.core.model.dao;

public class CommonConstantsController {

    public static final String SELECT_PERSON_ID_BY_NAME = "SELECT id " +
            "FROM persons " +
            "WHERE persons.firstName = ? " +
            "AND persons.middleName = ? " +
            "AND persons.secondName = ?";

    public static final String SELECT_FUND_ID_BY_NAME = "SELECT id " +
            "FROM museum_funds " +
            "WHERE museum_funds.name = ?";

    public static final String SELECT_SET_ID_BY_NAME = "SELECT id " +
            "FROM museum_item_sets " +
            "WHERE museum_item_sets.name = ?";

    public static final String SELECT_EXHIBITION_ID_BY_NAME = "SELECT id FROM exhibitions WHERE exhibtions.name = ?";

    public static final String UPDATE_PERSON_SQL_QUERY = "UPDATE persons SET firstName=:firstName, secondName=:secondName, " +
            "middleName=:middleName WHERE persons.id=:id";

    public static final String UPDATE_ITEM_SQL_QUERY = "UPDATE museum_items SET " +
            "inventoryNumber=:inventoryNumber, " +
            "name=:name, " +
            "creationDate=:creationDate, " +
            "annotation=:annotation " +
            "WHERE museum_items.id =:id";

    public static final String UPDATE_MOVEMENT_SQL_QUERY = "UPDATE museum_item_movements SET " +
            "acceptDate=:acceptDate, " +
            "transferDate=:transferDate, " +
            "returnDate=:returnDate";

    public static final String UPDATE_EXHIBITION_SQL_QUERY = "UPDATE exhibitions SET " +
            "name=:name, " +
            "address=:address, " +
            "phoneNumber=:phoneNumber";

    public static final String ITEMS_TABLE_NAME = "museum_items";
    public static final String SETS_TABLE_NAME = "museum_item_sets";
    public static final String FUNDS_TABLE_NAME = "museum_funds";
    public static final String PERSONS_TABLE_NAME = "persons";
    public static final String MOVEMENTS_TABLE_NAME = "museum_item_movements";
    public static final String EXHIBITIONS_TABLE_NAME = "exhibitions";
}
