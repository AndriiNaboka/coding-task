package com.monitoring.energy.dataaccess.repository;

import com.monitoring.energy.dataaccess.entity.PowerConsumptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CounterRepository extends JpaRepository<PowerConsumptionEntity, Long> {
    List<PowerConsumptionEntity> findAllByCreatedAtBetween(LocalDateTime startTime, LocalDateTime endTime);
}
