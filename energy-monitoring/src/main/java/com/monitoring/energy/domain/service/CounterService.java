package com.monitoring.energy.domain.service;

import com.monitoring.energy.dataaccess.entity.PowerConsumptionEntity;
import com.monitoring.energy.dataaccess.repository.CounterRepository;
import com.monitoring.energy.domain.model.PowerConsumption;
import com.monitoring.energy.domain.model.VillagePowerConsumption;
import com.monitoring.energy.integration.CounterApi;
import com.monitoring.energy.integration.model.CounterLocation;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class CounterService {
    private final CounterRepository counterRepository;
    private final ModelMapper modelMapper;
    private final CounterApi counterApi;

    public CounterService(CounterRepository counterRepository,
                          ModelMapper modelMapper,
                          CounterApi counterApi) {
        this.counterRepository = counterRepository;
        this.modelMapper = modelMapper;
        this.counterApi = counterApi;
    }

    public Long savePower(PowerConsumption powerConsumption) {
        PowerConsumptionEntity powerConsumptionEntity = modelMapper.map(powerConsumption, PowerConsumptionEntity.class);
        PowerConsumptionEntity savedPowerConsumption = counterRepository.save(powerConsumptionEntity);
        return savedPowerConsumption.getId();
    }

    public List<VillagePowerConsumption> generateConsumptionReport(String duration) {
        if (Objects.isNull(duration)) {
            throw new NumberFormatException("Can't calculate Report for the empty duration");
        }
        Pattern durationPattern = Pattern.compile("([0-9]+)([h])");
        Matcher matcher = durationPattern.matcher(duration);
        matcher.find();

        String parsedHours = matcher.group(1);
        String parsedDuration = matcher.group(2);

        if (Objects.isNull(parsedHours) || Objects.isNull(parsedDuration)) {
            throw new NumberFormatException("Can't calculate Report for the wrong duration format.");
        }

        Long durationHours = Long.valueOf(parsedHours);

        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = endTime.minusHours(durationHours);

        List<VillagePowerConsumption> villagePowerConsumptionList = new ArrayList<>();

        counterRepository.findAllByCreatedAtBetween(startTime, endTime)
                .stream().collect(Collectors.groupingBy(PowerConsumptionEntity::getCounterId))
                .forEach((counterId, powerConsumptionEntityList) -> {
                            CounterLocation counterLocation = counterApi.getCounterLocation(counterId);

                            VillagePowerConsumption villagePowerConsumption = new VillagePowerConsumption();
                            villagePowerConsumption.setVillageName(counterLocation.getVillageName());

                            Double totalPowerConsumption = powerConsumptionEntityList.stream()
                                    .mapToDouble(PowerConsumptionEntity::getAmount)
                                    .sum();

                            villagePowerConsumption.setConsumption(totalPowerConsumption);

                            villagePowerConsumptionList.add(villagePowerConsumption);
                        }
                );


        return villagePowerConsumptionList;
    }
}
