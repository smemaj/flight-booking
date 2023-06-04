package lhind.flights.booking.controller;


import io.swagger.v3.oas.annotations.media.Schema;
import lhind.flights.booking.exception.FlightIsBookedException;
import lhind.flights.booking.exception.FlightNotFoundException;
import lhind.flights.booking.exception.UserNotFoundException;
import lhind.flights.booking.model.dto.FlightDTO;
import lhind.flights.booking.model.dto.FlightSearch;
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

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.POST, path = "/{id}")
    public ResponseEntity<FlightDTO> updateFlight(@PathVariable(value = "id") Long id, @RequestBody FlightDTO flightDTO)throws FlightNotFoundException{
        return ResponseEntity.status(202).body(flightService.updateFlight(id, flightDTO));
    }

    @PreAuthorize(value = "hasAnyRole('ADMINISTRATOR')")
    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<String> deleteFlightById(@PathVariable(value = "id") Long id) throws FlightNotFoundException, FlightIsBookedException {
        flightService.deleteFlightById(id);
        return ResponseEntity.status(202).body("Flight deleted!");
    }

    @PreAuthorize(value = "hasAnyRole('TRAVELLER')")
    @RequestMapping(method = RequestMethod.GET, path = "/search")
    public ResponseEntity<List<FlightDTO>> getFlights(FlightSearch flightSearch) {

        return ResponseEntity.ok(flightService.loadAllFlightsBySearch(flightSearch));
    }

}
