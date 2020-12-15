package com.vladbrown.core.model.dao;

import com.vladbrown.core.model.domain.Item;

import java.util.List;

public interface ItemDao {

    Item getItem(Long id);

    void save(Item item);

    List<Item> findAll();

    void delete(Long id);
}
