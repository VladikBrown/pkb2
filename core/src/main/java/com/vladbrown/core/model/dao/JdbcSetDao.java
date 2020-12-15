package com.vladbrown.core.model.dao;

import com.vladbrown.core.model.domain.Item;
import com.vladbrown.core.model.domain.MuseumSet;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcSetDao implements SetDao {

    public static final String SELECT_ALL_SETS = "SELECT * FROM museum_item_sets";
    public static final String SELECT_ITEMS_BY_SET_ID = "SELECT museum_items.id as id, inventoryNumber, " +
            "museum_items.name as name, creationDate, annotation, " +
            "author_id as author_id, " +
            "persons.firstName as author_firstName, " +
            "persons.secondName as author_secondName, " +
            "persons.middleName as author_middleName," +
            "set_id as museumSet_id, " +
            "museum_item_sets.name as museumSet_name, " +
            "fund_id as fund_id, " +
            "museum_funds.name as fund_name " +
            "FROM (SELECT * FROM museum_items WHERE museum_items.set_Id = ?) as museum_items " +
            "JOIN persons ON persons.id = author_id " +
            "JOIN museum_funds ON museum_funds.id = fund_id " +
            "JOIN museum_item_sets ON museum_item_sets.id = set_id";
    private final ResultSetExtractor<List<MuseumSet>> museumSetResultSetExtractor = JdbcTemplateMapperFactory
            .newInstance().addKeys("id")
            .newResultSetExtractor(MuseumSet.class);
    private final ResultSetExtractor<List<Item>> itemResultSetExtractor = JdbcTemplateMapperFactory
            .newInstance().addKeys("id")
            .newResultSetExtractor(Item.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Item> getSetOfItemsBySetId(Long id) {
        return jdbcTemplate.query(SELECT_ITEMS_BY_SET_ID, itemResultSetExtractor, id);
    }

    @Override
    public void save(MuseumSet museumSet) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<MuseumSet> findAll() {
        return jdbcTemplate.query(SELECT_ALL_SETS, museumSetResultSetExtractor);
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }
}
