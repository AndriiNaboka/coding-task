package com.monitoring.energy.application;

import com.monitoring.energy.application.dto.PowerConsumptionDto;
import com.monitoring.energy.application.dto.VillagePowerConsumptionDto;
import com.monitoring.energy.facade.CounterFacade;
import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "Counter Management", tags = "Operations related to counter", protocols = "/counters")
@RestController
@RequestMapping("/counters")
public class CounterController {

    private final CounterFacade counterFacade;

    public CounterController(CounterFacade counterFacade) {
        this.counterFacade = counterFacade;
    }

    @ApiOperation(value = "Save data counter power consumption")
    @PostMapping
    public ResponseEntity<Long> savePowerConsumption(
            @NotNull @RequestBody  @ApiParam(value = "Power Consumption") PowerConsumptionDto powerConsumptionDto) {
        Long id = counterFacade.savePowerConsumption(powerConsumptionDto);
        return ResponseEntity.ok(id);
    }

    @ApiOperation(value = "Consumption report")
    @GetMapping(path = "/consumption-report")
    public List<VillagePowerConsumptionDto> getPowerConsumptionReport(
            @RequestParam String duration) {
        return counterFacade.getPowerConsumptionReport(duration);
    }
}
