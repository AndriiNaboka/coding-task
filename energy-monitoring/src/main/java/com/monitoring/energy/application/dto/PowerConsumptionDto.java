package com.monitoring.energy.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@ApiModel(value = "Model for Power Consumption")
@Getter
@Setter
public class PowerConsumptionDto {
    @ApiModelProperty(example = "1")
    @NotNull
    private Long id;

    @ApiModelProperty(example = "123.01")
    @NotNull
    private Double amount;
}
