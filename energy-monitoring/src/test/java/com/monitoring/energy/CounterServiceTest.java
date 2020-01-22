package com.monitoring.energy;

import com.monitoring.energy.dataaccess.entity.PowerConsumptionEntity;
import com.monitoring.energy.dataaccess.repository.CounterRepository;
import com.monitoring.energy.domain.model.VillagePowerConsumption;
import com.monitoring.energy.domain.service.CounterService;
import com.monitoring.energy.integration.CounterApi;
import com.monitoring.energy.integration.model.CounterLocation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CounterServiceTest {
    @Mock
    private CounterRepository counterRepository;

    @Mock
    private CounterApi counterApi;

    @InjectMocks
    private CounterService counterService;


    @Before
    public void init() {
        CounterLocation counterLocation1 = new CounterLocation();
        counterLocation1.setId(1L);
        counterLocation1.setVillageName("Villariba");

        CounterLocation counterLocation2 = new CounterLocation();
        counterLocation2.setId(2L);
        counterLocation2.setVillageName("Villabago");

        when(counterApi.getCounterLocation(1L)).thenReturn(counterLocation1);
        when(counterApi.getCounterLocation(2L)).thenReturn(counterLocation2);

        PowerConsumptionEntity powerVillariba1 = new PowerConsumptionEntity();
        powerVillariba1.setCounterId(1L);
        powerVillariba1.setAmount(122d);

        PowerConsumptionEntity powerVillariba2 = new PowerConsumptionEntity();
        powerVillariba2.setCounterId(1L);
        powerVillariba2.setAmount(14d);

        PowerConsumptionEntity powerVillabago1 = new PowerConsumptionEntity();
        powerVillabago1.setCounterId(2L);
        powerVillabago1.setAmount(122d);

        PowerConsumptionEntity powerVillabago2 = new PowerConsumptionEntity();
        powerVillabago2.setCounterId(2L);
        powerVillabago2.setAmount(122d);


        List<PowerConsumptionEntity> powerConsumptionEntities = Arrays.asList(powerVillariba1, powerVillariba2,
                powerVillabago1, powerVillabago2);

        when(counterRepository.findAllByCreatedAtBetween(any(), any())).thenReturn(powerConsumptionEntities);

    }

    @Test
    public void generatePowerConsumptionReportForTwoVillagesTest() {
        List<VillagePowerConsumption> powerConsumptionReport = counterService.generateConsumptionReport("24h");

        assertEquals(powerConsumptionReport.size(), 2);

        VillagePowerConsumption villaribaPowerConsumption = powerConsumptionReport.get(0);
        VillagePowerConsumption villabagoPowerConsuption = powerConsumptionReport.get(1);

        assertEquals(villaribaPowerConsumption.getVillageName(), "Villariba");
        assertEquals(villaribaPowerConsumption.getConsumption(), 136d);


        assertEquals(villabagoPowerConsuption.getVillageName(), "Villabago");
        assertEquals(villabagoPowerConsuption.getConsumption(), 244d);
    }
}
