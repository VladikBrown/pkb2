package com.vladbrown.core.model.dao;

import com.vladbrown.core.model.domain.Item;
import org.simpleflatmapper.jdbc.spring.JdbcTemplateMapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcItemDao implements ItemDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CommonJdbcDaoUtils commonJdbcDaoUtils;

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

    public static final String SELECT_ALL_ITEMS = "SELECT museum_items.id as id, inventoryNumber, " +
            "name, creationDate, annotation, " +
            "author_id as author_id, " +
            "set_id as museumSet_id, " +
            "fund_id as fund_id " +
            "FROM museum_items";


    public static final String ITEMS_TABLE_NAME = "museum_items";
    public static final String SETS_TABLE_NAME = "museum_item_sets";
    public static final String FUNDS_TABLE_NAME = "museum_funds";
    public static final String PERSONS_TABLE_NAME = "persons";

    private final ResultSetExtractor<List<Item>> resultSetExtractor = JdbcTemplateMapperFactory
            .newInstance().addKeys("id")
            .newResultSetExtractor(Item.class);

    @Override
    public Item getItem(Long id) {
        return null;
    }

    @Override
    public void save(Item item) {
        insertIfExistPerson(item);
        insertIfExistFund(item);
        insertIfExistMuseumSet(item);
        insertIfExistItem(item);
    }

    private void insertIfExistItem(Item item) {
        if (commonJdbcDaoUtils.isEntityExist(ITEMS_TABLE_NAME,
                Collections.singletonMap("name", item.getName()))) {
            update(item);
        } else {
            var simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
            item.setId((Long)simpleJdbcInsert
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
    }

    private void update(Item item) {
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
}
