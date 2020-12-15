package com.vladbrown.core.model.dao;

import com.vladbrown.core.model.domain.Item;
import com.vladbrown.core.model.domain.Person;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.vladbrown.core.model.dao.CommonConstantsController.*;

@Repository
public class JdbcItemDao implements ItemDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CommonJdbcDaoUtils commonJdbcDaoUtils;

    public static final String SELECT_ALL_ITEMS = "SELECT museum_items.id as id, inventoryNumber, " +
            "museum_items.name as name, creationDate, annotation, " +
            "author_id as author_id, " +
            "persons.firstName as author_firstName, " +
            "persons.secondName as author_secondName, " +
            "persons.middleName as author_middleName," +
            "set_id as museumSet_id, " +
            "museum_item_sets.name as museumSet_name, " +
            "fund_id as fund_id, " +
            "museum_funds.name as fund_name " +
            "FROM museum_items " +
            "JOIN persons ON persons.id = author_id " +
            "JOIN museum_funds ON museum_funds.id = fund_id " +
            "JOIN museum_item_sets ON museum_item_sets.id = set_id";

    public static final String SELECT_ONE_ITEM_BY_ID = "SELECT museum_items.id as id, inventoryNumber, " +
            "museum_items.name as name, creationDate, annotation, " +
            "author_id as author_id, " +
            "persons.firstName as author_firstName, " +
            "persons.secondName as author_secondName, " +
            "persons.middleName as author_middleName," +
            "set_id as museumSet_id, " +
            "museum_item_sets.name as museumSet_name, " +
            "fund_id as fund_id, " +
            "museum_funds.name as fund_name " +
            "FROM (SELECT * FROM museum_items WHERE museum_items.id = ?) as museum_items " +
            "JOIN persons ON persons.id = author_id " +
            "JOIN museum_funds ON museum_funds.id = fund_id " +
            "JOIN museum_item_sets ON museum_item_sets.id = set_id";


    private static final String DELETE_ITEM_BY_ID = "DELETE FROM museum_items WHERE id = ?";


    private final ResultSetExtractor<List<Item>> resultSetExtractor = JdbcTemplateMapperFactory
            .newInstance().addKeys("id")
            .newResultSetExtractor(Item.class);

    @Override
    public Item getItem(Long id) {
        List<Item> queryResult = jdbcTemplate.query(SELECT_ONE_ITEM_BY_ID, resultSetExtractor, id);
        return queryResult.get(0);
    }

    @Override
    public void save(Item item) {
        if (item.getId() != null) {
            if (commonJdbcDaoUtils.isEntityExist(ITEMS_TABLE_NAME,
                    Collections.singletonMap("id", item.getId().toString()))) {
                update(item);
            } else {
                insertItemWithAllRelations(item);
            }
        } else if (commonJdbcDaoUtils.isEntityExist(ITEMS_TABLE_NAME,
                Collections.singletonMap("name", item.getName()))) {
            update(item);
        } else {
            insertItemWithAllRelations(item);
        }
    }

    private void insertItemWithAllRelations(Item item) {
        insertIfExistPerson(item);
        insertIfExistFund(item);
        insertIfExistMuseumSet(item);
        insertIfExistItem(item);
    }

    private void insertIfExistItem(Item item) {
        var simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        item.setId((Long) simpleJdbcInsert
                .withTableName(ITEMS_TABLE_NAME)
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource()
                        .addValue("inventorYNumber", item.getInventoryNumber())
                        .addValue("name", item.getName())
                        .addValue("creationDate", item.getCreationDate())
                        .addValue("annotation", item.getAnnotation())
                        .addValue("set_id", item.getMuseumSet().getId())
                        .addValue("author_id", item.getAuthor().getId())
                        .addValue("fund_id", item.getFund().getId())));

    }

    private void update(Item item) {
        NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        parameterJdbcTemplate.update(UPDATE_ITEM_SQL_QUERY, new BeanPropertySqlParameterSource(item));
        updateRelatedPerson(item.getAuthor());
    }

    private void updateRelatedPerson(Person person) {
        NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        parameterJdbcTemplate.update(UPDATE_PERSON_SQL_QUERY, new BeanPropertySqlParameterSource(person));
    }

    private void insertIfExistFund(Item item) {
        if (commonJdbcDaoUtils.isEntityExist(FUNDS_TABLE_NAME,
                Collections.singletonMap("name", item.getFund().getName()))) {
            item.getFund().setId(jdbcTemplate.queryForObject(SELECT_FUND_ID_BY_NAME, new Object[]{item.getFund().getName()}, Long.class));
        } else {
            var parameterSource = new BeanPropertySqlParameterSource(item.getFund());
            item.getFund().setId((Long) commonJdbcDaoUtils.insertAndReturnGeneratedKey(FUNDS_TABLE_NAME, parameterSource, "id"));
        }
    }

    private void insertIfExistPerson(Item item) {
        String firstName = item.getAuthor().getFirstName();
        String secondName = item.getAuthor().getSecondName();
        String middleName = item.getAuthor().getMiddleName();
        if (commonJdbcDaoUtils.isEntityExist(PERSONS_TABLE_NAME,
                Map.of("firstName", firstName,
                        "secondName", secondName,
                        "middleName", middleName))) {
            item.getAuthor().setId(jdbcTemplate.queryForObject(SELECT_PERSON_ID_BY_NAME, new Object[]{firstName, middleName, secondName}, Long.class));
        } else {
            var parameterSource = new BeanPropertySqlParameterSource(item.getAuthor());
            item.getAuthor().setId((Long) commonJdbcDaoUtils.insertAndReturnGeneratedKey(PERSONS_TABLE_NAME, parameterSource, "id"));
        }
    }

    private void insertIfExistMuseumSet(Item item) {
        if (commonJdbcDaoUtils.isEntityExist(SETS_TABLE_NAME,
                Collections.singletonMap("name", item.getMuseumSet().getName()))) {
            item.getMuseumSet().setId(jdbcTemplate.queryForObject(SELECT_SET_ID_BY_NAME, new Object[]{item.getMuseumSet().getName()}, Long.class));
        } else {
            var parameterSource = new BeanPropertySqlParameterSource(item.getMuseumSet());
            item.getMuseumSet().setId((Long) commonJdbcDaoUtils.insertAndReturnGeneratedKey(SETS_TABLE_NAME, parameterSource, "id"));
        }
    }

    @Override
    public List<Item> findAll() {
        return jdbcTemplate.query(SELECT_ALL_ITEMS, resultSetExtractor);
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.update(DELETE_ITEM_BY_ID, id);
    }
}
