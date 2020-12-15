package com.vladbrown.core.model.dao;

import com.vladbrown.core.model.domain.Exhibition;
import com.vladbrown.core.model.domain.ItemMovement;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.vladbrown.core.model.dao.CommonConstantsController.*;

@Repository
public class JdbcMovementDaoImpl implements MovementDao {

    private static final String SELECT_MOVEMENTS_BY_SET_ID = "SELECT museum_item_movements.id as id, " +
            "acceptDate, transferDate, returnDate, " +
            "exhibitions.id as exhibition_id, " +
            "exhibitions.name as exhibition_name, " +
            "exhibitions.address as exhibition_address, " +
            "exhibitions.phoneNumber as exhibition_phoneNumber, " +
            "persons.id as responsiblePerson_id, " +
            "persons.firstName as responsiblePerson_firstName, " +
            "persons.secondName as responsiblePerson_secondName, " +
            "persons.middleName as responsiblePerson_middleName," +
            "set_id as museumSet_id, " +
            "museum_item_sets.name as museumSet_name " +
            "FROM (SELECT * FROM museum_item_movements WHERE museum_item_movements.set_id = ?) as museum_item_movements " +
            "JOIN persons ON persons.id = responsible_person_id " +
            "JOIN exhibitions ON exhibitions.id = exhibition_id " +
            "JOIN museum_item_sets ON museum_item_sets.id = set_id";

    private static final String SELECT_MOVEMENT_OF_SET_BY_DAY = "SELECT museum_item_movements.id as id, " +
            "acceptDate, transferDate, returnDate, " +
            "exhibitions.id as exhibition_id, " +
            "exhibitions.name as exhibition_name, " +
            "exhibitions.address as exhibition_address, " +
            "exhibitions.phoneNumber as exhibition_phoneNumber, " +
            "persons.id as responsiblePerson_id, " +
            "persons.firstName as responsiblePerson_firstName, " +
            "persons.secondName as responsiblePerson_secondName, " +
            "persons.middleName as responsiblePerson_middleName," +
            "set_id as museumSet_id, " +
            "museum_item_sets.name as museumSet_name " +
            "FROM (SELECT * FROM museum_item_movements " +
            "WHERE museum_item_movements.set_id = ? AND " +
            "? BETWEEN museum_item_movements.transferDate AND museum_item_movements.returnDate) as museum_item_movements " +
            "JOIN persons ON persons.id = responsible_person_id " +
            "JOIN exhibitions ON exhibitions.id = exhibition_id " +
            "JOIN museum_item_sets ON museum_item_sets.id = set_id";
    ;
    private final ResultSetExtractor<List<ItemMovement>> movementResultSetExtractor = JdbcTemplateMapperFactory
            .newInstance().addKeys("id")
            .newResultSetExtractor(ItemMovement.class);
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private CommonJdbcDaoUtils commonJdbcDaoUtils;

    @Override
    public void save(ItemMovement itemMovement) {
        if (itemMovement.getId() != null) {
            if (commonJdbcDaoUtils.isEntityExist(MOVEMENTS_TABLE_NAME,
                    Collections.singletonMap("id", itemMovement.getId().toString()))) {
                update(itemMovement);
            } else {
                insertMovementWithAllRelations(itemMovement);
            }
        } else {
            insertMovementWithAllRelations(itemMovement);
        }
    }

    private void insertMovementWithAllRelations(ItemMovement itemMovement) {
        insertIfExistPerson(itemMovement);
        insertIfExistExhibition(itemMovement);
        insertIfExistMuseumSet(itemMovement);
        insertIfExistMovement(itemMovement);
    }

    private void insertIfExistMuseumSet(ItemMovement itemMovement) {
        if (commonJdbcDaoUtils.isEntityExist(SETS_TABLE_NAME,
                Collections.singletonMap("name", itemMovement.getMuseumSet().getName()))) {
            itemMovement.getMuseumSet().setId(jdbcTemplate.queryForObject(SELECT_SET_ID_BY_NAME, new Object[]{itemMovement.getMuseumSet().getName()}, Long.class));
        } else {
            var parameterSource = new BeanPropertySqlParameterSource(itemMovement.getMuseumSet());
            itemMovement.getMuseumSet().setId((Long) commonJdbcDaoUtils.insertAndReturnGeneratedKey(SETS_TABLE_NAME, parameterSource, "id"));
        }
    }

    private void insertIfExistExhibition(ItemMovement itemMovement) {
        if (commonJdbcDaoUtils.isEntityExist(EXHIBITIONS_TABLE_NAME,
                Collections.singletonMap("name", itemMovement.getExhibition().getName()))) {
            itemMovement.getExhibition().setId(jdbcTemplate.queryForObject(SELECT_EXHIBITION_ID_BY_NAME, new Object[]{itemMovement.getExhibition().getName()}, Long.class));
        } else {
            var parameterSource = new BeanPropertySqlParameterSource(itemMovement.getExhibition());
            itemMovement.getExhibition().setId((Long) commonJdbcDaoUtils.insertAndReturnGeneratedKey(EXHIBITIONS_TABLE_NAME, parameterSource, "id"));
        }
    }

    //todo move to commonUtils extenstion:  public Long insertIfExistPerson(Person person)
    private void insertIfExistPerson(ItemMovement itemMovement) {
        String firstName = itemMovement.getResponsiblePerson().getFirstName();
        String secondName = itemMovement.getResponsiblePerson().getSecondName();
        String middleName = itemMovement.getResponsiblePerson().getMiddleName();
        if (commonJdbcDaoUtils.isEntityExist(PERSONS_TABLE_NAME,
                Map.of("firstName", firstName,
                        "secondName", secondName,
                        "middleName", middleName))) {
            itemMovement.getResponsiblePerson().setId(jdbcTemplate.queryForObject(SELECT_PERSON_ID_BY_NAME, new Object[]{firstName, middleName, secondName}, Long.class));
        } else {
            var parameterSource = new BeanPropertySqlParameterSource(itemMovement.getResponsiblePerson());
            itemMovement.getResponsiblePerson().setId((Long) commonJdbcDaoUtils.insertAndReturnGeneratedKey(PERSONS_TABLE_NAME, parameterSource, "id"));
        }
    }

    private void insertIfExistMovement(ItemMovement itemMovement) {
        var simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        itemMovement.setId((Long) simpleJdbcInsert
                .withTableName(MOVEMENTS_TABLE_NAME)
                .usingGeneratedKeyColumns("id")
                .executeAndReturnKey(new MapSqlParameterSource()
                        .addValue("acceptDate", itemMovement.getAcceptDate())
                        .addValue("transferDate", itemMovement.getTransferDate())
                        .addValue("returnDate", itemMovement.getReturnDate())
                        .addValue("exhibition_id", itemMovement.getExhibition().getId())
                        .addValue("set_id", itemMovement.getMuseumSet().getId())
                        .addValue("responsible_person_id", itemMovement.getResponsiblePerson().getId())));
    }

    private void update(ItemMovement itemMovement) {
        NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        parameterJdbcTemplate.update(UPDATE_MOVEMENT_SQL_QUERY, new BeanPropertySqlParameterSource(itemMovement));
        updateRelatedPerson(itemMovement.getResponsiblePerson());
        updateRelatedExhibition(itemMovement.getExhibition());
    }

    private void updateRelatedExhibition(Exhibition exhibition) {
        NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        parameterJdbcTemplate.update(UPDATE_PERSON_SQL_QUERY, new BeanPropertySqlParameterSource(exhibition));
    }

    private void updateRelatedPerson(Person person) {
        NamedParameterJdbcTemplate parameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        parameterJdbcTemplate.update(UPDATE_PERSON_SQL_QUERY, new BeanPropertySqlParameterSource(person));
    }

    @Override
    public List<ItemMovement> findAllBySetId(Long id) {
        return jdbcTemplate.query(SELECT_MOVEMENTS_BY_SET_ID, movementResultSetExtractor, id);
    }

    @Override
    public Optional<ItemMovement> getMovementOfSetByDate(Long setId, LocalDate localDate) {
        List<ItemMovement> queryResult = jdbcTemplate.query(SELECT_MOVEMENT_OF_SET_BY_DAY, movementResultSetExtractor, setId, localDate);
        if (queryResult.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(queryResult.get(0));
    }


}
