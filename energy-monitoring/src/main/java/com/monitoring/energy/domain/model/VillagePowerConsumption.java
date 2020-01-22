package com.monitoring.energy.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VillagePowerConsumption {
    private String villageName;
    private Double consumption;
}
