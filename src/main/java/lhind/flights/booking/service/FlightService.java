package lhind.flights.booking.service;

import lhind.flights.booking.exception.FlightIsBookedException;
import lhind.flights.booking.exception.FlightNotFoundException;
import lhind.flights.booking.model.dto.FlightDTO;
import lhind.flights.booking.model.dto.FlightSearch;
import lhind.flights.booking.model.dto.TestFlightSearch;
import lhind.flights.booking.model.dto.TravellerInfo;

import java.util.List;

public interface FlightService {
    List<FlightDTO> loadAllFlightsBySearch(FlightSearch flightSearch);

    FlightDTO newFlight(FlightDTO flightDTO);

    List<TravellerInfo> getUsersByFlightId(Long id);


    FlightDTO updateFlight(Long id, FlightDTO flightDTO) throws FlightNotFoundException;

    void deleteFlightById(Long id) throws FlightNotFoundException, FlightIsBookedException;
}
