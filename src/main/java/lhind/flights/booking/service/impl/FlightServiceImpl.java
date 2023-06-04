package lhind.flights.booking.service.impl;

import lhind.flights.booking.mapper.FlightMapper;
import lhind.flights.booking.mapper.UserMapper;
import lhind.flights.booking.model.dto.FlightDTO;
import lhind.flights.booking.model.dto.TravellerInfo;
import lhind.flights.booking.model.entity.Flight;
import lhind.flights.booking.repository.FlightRepository;
import lhind.flights.booking.repository.UserRepository;
import lhind.flights.booking.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final UserRepository userRepository;

    private final FlightMapper flightMapper;

    private final UserMapper userMapper;

    public FlightServiceImpl(FlightRepository flightRepository, UserRepository userRepository) {
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
        this.userMapper = new UserMapper();
        this.flightMapper = new FlightMapper();
    }

    @Override
    public List<FlightDTO> loadAll() {
        return null;
    }

    @Override
    public FlightDTO newFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();

        flight.setFlightStatus(flightDTO.getFlightStatus());
        flight.setFlightDate(flightDTO.getFlightDate());
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setOrigin(flightDTO.getOrigin());
        flight.setDestination(flightDTO.getDestination());
        flight.setAircraftType(flight.getAircraftType());
        flight.setAirlineCode(flightDTO.getAirlineCode());
        flight.setDepartureTime(flightDTO.getDepartureTime());

        return new FlightDTO(flightRepository.save(flight));
    }

    @Override
    public List<TravellerInfo> getUsersByFlightId(Long id) {
        return userRepository.findByFlightId(id).stream().map(TravellerInfo::new).collect(Collectors.toList());
    }
}
