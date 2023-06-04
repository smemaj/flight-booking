package lhind.flights.booking.controller;


import io.swagger.v3.oas.annotations.media.Schema;
import lhind.flights.booking.model.dto.FlightDTO;
import lhind.flights.booking.model.dto.TravellerInfo;
import lhind.flights.booking.model.dto.UserDTO;
import lhind.flights.booking.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/flight")
@Schema
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FlightDTO> newFlight(@RequestBody FlightDTO flightDTO) {
        return ResponseEntity.status(201).body(flightService.newFlight(flightDTO));
    }

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<List<TravellerInfo>> getUsersInFlight(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(flightService.getUsersByFlightId(id));
    }

}
