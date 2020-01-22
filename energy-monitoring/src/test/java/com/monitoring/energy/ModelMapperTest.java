package com.monitoring.energy;

import com.monitoring.energy.application.dto.VillagePowerConsumptionDto;
import com.monitoring.energy.domain.model.VillagePowerConsumption;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class ModelMapperTest {
    private ModelMapper mapper;

    @Before
    public void init() {
        mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Test
    public void conversionListVillagePowerConsumptionToListVillagePowerConsumptionDto() {
        VillagePowerConsumption villagePowerConsumption = new VillagePowerConsumption();
        villagePowerConsumption.setVillageName("Villabago");
        villagePowerConsumption.setConsumption(123456.1d);

        VillagePowerConsumptionDto villagePowerConsumptionDto = mapper.map(villagePowerConsumption, VillagePowerConsumptionDto.class);

        assertEquals(villagePowerConsumptionDto.getConsumption(), villagePowerConsumption.getConsumption());
        assertEquals(villagePowerConsumption.getVillageName(), villagePowerConsumptionDto.getVillageName());
    }

}
