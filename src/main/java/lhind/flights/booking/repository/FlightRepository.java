package lhind.flights.booking.repository;

import lhind.flights.booking.model.dto.FlightSearch;
import lhind.flights.booking.model.dto.TravellerInfo;
import lhind.flights.booking.model.entity.Flight;
import lhind.flights.booking.model.entity.User;
import lhind.flights.booking.model.enums.AirlineCodeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {

    @Query(value = "SELECT * FROM flight where " +
            "origin = :origin AND destination = :destination AND " +
            "flight_date = :flightDate AND" +
            " airline_code = :airlineCode", nativeQuery = true)
    List<Flight> findFlightsBySearch(String origin, String destination, Date flightDate, AirlineCodeEnum airlineCode);

    @Query(value = "SELECT * FROM flight where " +
            "origin = :origin AND destination = :destination and flight_date = :flightDate", nativeQuery = true)
    List<Flight> findFlightsBySearchWithoutAC(String origin, String destination, Date flightDate);
}
