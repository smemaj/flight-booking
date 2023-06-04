package lhind.flights.booking.service;

import lhind.flights.booking.model.dto.FlightDTO;
import lhind.flights.booking.model.dto.TravellerInfo;

import java.util.List;

public interface FlightService {
    List<FlightDTO> loadAll();

    FlightDTO newFlight(FlightDTO flightDTO);

    List<TravellerInfo> getUsersByFlightId(Long id);
}
