package com.vladbrown.core.model.dao;

import com.vladbrown.core.model.domain.Item;
import com.vladbrown.core.model.domain.MuseumSet;

import java.util.List;

public interface SetDao {

    List<Item> getSetOfItemsBySetId(Long id);

    void save(MuseumSet museumSet);

    List<MuseumSet> findAll();

    void delete(Long id);
}
