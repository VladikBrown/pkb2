package com.vladbrown.core.model.dao;

import com.vladbrown.core.model.domain.ItemMovement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovementDao {

    void save(ItemMovement itemMovement);

    List<ItemMovement> findAllBySetId(Long id);

    Optional<ItemMovement> getMovementOfSetByDate(Long setId, LocalDate localDate);
}
