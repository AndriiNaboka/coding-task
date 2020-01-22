package com.monitoring.energy.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "power_consumption")
public class PowerConsumptionEntity extends BaseEntity {
    private Long counterId;
    private Double amount;
}
