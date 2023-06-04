package lhind.flights.booking.mapper;

import lhind.flights.booking.model.dto.FlightDTO;
import lhind.flights.booking.model.entity.Flight;

public class FlightMapper extends  AbstractMapper<Flight, FlightDTO>{
    @Override
    public Flight toEntity(FlightDTO flightDTO) {
        if (flightDTO == null) {
            return null;
        }
        Flight flight = new Flight();
        flight.setId(flightDTO.getId());
        flight.setAirlineCode(flightDTO.getAirlineCode());
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setOrigin(flightDTO.getOrigin());
        flight.setDestination(flightDTO.getDestination());
        flight.setFlightDate(flightDTO.getFlightDate());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setAircraftType(flightDTO.getAircraftType());
//        flight.setFlightStatus(flightDTO.getFlightStatus());
        return flight;

    }

    @Override
    public FlightDTO toDto(Flight flight) {
        if (flight == null) {
            return null;
        }
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setId(flight.getId());
        flightDTO.setAirlineCode(flight.getAirlineCode());
        flightDTO.setFlightNumber(flight.getFlightNumber());
        flightDTO.setOrigin(flight.getOrigin());
        flightDTO.setDestination(flight.getDestination());
        flightDTO.setFlightDate(flight.getFlightDate());
        flightDTO.setDepartureTime(flight.getDepartureTime());
        flightDTO.setAircraftType(flight.getAircraftType());
//        flightDTO.setFlightStatus(flight.getFlightStatus());
        return flightDTO;
    }
}
