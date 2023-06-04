package lhind.flights.booking.service.impl;

import lhind.flights.booking.exception.FlightIsBookedException;
import lhind.flights.booking.exception.FlightNotFoundException;
import lhind.flights.booking.mapper.FlightMapper;
import lhind.flights.booking.mapper.UserMapper;
import lhind.flights.booking.model.dto.FlightDTO;
import lhind.flights.booking.model.dto.FlightSearch;
import lhind.flights.booking.model.dto.TestFlightSearch;
import lhind.flights.booking.model.dto.TravellerInfo;
import lhind.flights.booking.model.entity.Flight;
import lhind.flights.booking.model.entity.User;
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
    public List<FlightDTO> loadAllFlightsBySearch(FlightSearch flightSearch) {
        if(flightSearch.getAirlineCode()==null){
            return flightRepository.findFlightsBySearchWithoutAC(flightSearch.getOrigin(),
                    flightSearch.getDestination(), flightSearch.getFlightDate()).stream().map(flightMapper::toDto).collect(Collectors.toList());
        }else {
            return flightRepository.findFlightsBySearch(flightSearch.getOrigin(),
                    flightSearch.getDestination(),
                    flightSearch.getFlightDate(),
                    flightSearch.getAirlineCode()).stream().map(flightMapper::toDto).collect(Collectors.toList());
        }
//        return flightRepository.findFlightsBySearchWithoutAC(origin).stream().map(flightMapper::toDto).collect(Collectors.toList());
    }


    @Override
    public FlightDTO newFlight(FlightDTO flightDTO) {
        Flight flight = new Flight();

        flight.setAirlineCode(flightDTO.getAirlineCode());
        flight.setFlightNumber(flightDTO.getFlightNumber());
        flight.setOrigin(flightDTO.getOrigin());
        flight.setDestination(flightDTO.getDestination());
        flight.setFlightDate(flightDTO.getFlightDate());
        flight.setDepartureTime(flightDTO.getDepartureTime());
        flight.setAircraftType(flightDTO.getAircraftType());
        flight.setFlightStatus(flightDTO.getFlightStatus());

        return new FlightDTO(flightRepository.save(flight));
    }

    @Override
    public List<TravellerInfo> getUsersByFlightId(Long id) {
        return userRepository.findByFlightId(id).stream().map(TravellerInfo::new).collect(Collectors.toList());
    }

    @Override
    public FlightDTO updateFlight(Long id, FlightDTO flightDTO) throws FlightNotFoundException{
        Flight flight = flightRepository.findById(id).orElseThrow(FlightNotFoundException::new);
        List<User> listOfTravellers = userRepository.findByFlightId(id);

        if(listOfTravellers.isEmpty()){
            flight.setAirlineCode(flightDTO.getAirlineCode());
            flight.setFlightNumber(flightDTO.getFlightNumber());
            flight.setOrigin(flightDTO.getOrigin());
            flight.setDestination(flightDTO.getDestination());
            flight.setFlightDate(flightDTO.getFlightDate());
            flight.setDepartureTime(flightDTO.getDepartureTime());
            flight.setAircraftType(flightDTO.getAircraftType());
            flight.setFlightStatus(flightDTO.getFlightStatus());
        }else {
            flight.setDepartureTime(flightDTO.getDepartureTime());
        }
        return new FlightDTO(flightRepository.save(flight));
    }

    @Override
    public void deleteFlightById(Long id) throws FlightNotFoundException, FlightIsBookedException {
        Flight flight = flightRepository.findById(id).orElseThrow(FlightNotFoundException::new);
        List<User> listOfTravellers = userRepository.findByFlightId(id);
        if(listOfTravellers.isEmpty()){
            flightRepository.delete(flight);
        }else {
            throw new FlightIsBookedException();
        }

    }
}
