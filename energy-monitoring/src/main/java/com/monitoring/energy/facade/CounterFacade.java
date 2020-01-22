package com.monitoring.energy.facade;

import com.monitoring.energy.application.dto.PowerConsumptionDto;
import com.monitoring.energy.application.dto.VillagePowerConsumptionDto;
import com.monitoring.energy.domain.model.PowerConsumption;
import com.monitoring.energy.domain.service.CounterService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CounterFacade {

    private final CounterService counterService;
    private final ModelMapper modelMapper;

    public CounterFacade(CounterService counterService,
                         ModelMapper modelMapper) {
        this.counterService = counterService;
        this.modelMapper = modelMapper;
    }

    public Long savePowerConsumption(PowerConsumptionDto powerConsumptionDto) {
        PowerConsumption powerConsumption = modelMapper.map(powerConsumptionDto, PowerConsumption.class);
        powerConsumption.setCounterId(powerConsumptionDto.getId());
        return counterService.savePower(powerConsumption);
    }

    public List<VillagePowerConsumptionDto> getPowerConsumptionReport(String duration) {
        return counterService.generateConsumptionReport(duration).stream()
                .map(villagePowerConsumption -> modelMapper.map(villagePowerConsumption, VillagePowerConsumptionDto.class))
                .collect(Collectors.toList());
    }
}
