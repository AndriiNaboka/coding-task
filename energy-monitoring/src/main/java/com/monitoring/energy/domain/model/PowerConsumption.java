package com.monitoring.energy.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PowerConsumption {
    private Long counterId;
    private Double amount;
}
