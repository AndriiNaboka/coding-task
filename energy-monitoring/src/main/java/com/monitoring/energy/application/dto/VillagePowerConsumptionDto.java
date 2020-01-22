package com.monitoring.energy.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "Village Power Consuption")
@Getter
@Setter
public class VillagePowerConsumptionDto {
    @ApiModelProperty(example = "Villariba")
    private String villageName;

    @ApiModelProperty(example = "12038.08")
    private Double consumption;
}
