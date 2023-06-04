package lhind.flights.booking.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lhind.flights.booking.model.entity.Flight;
import lhind.flights.booking.model.enums.AirlineCodeEnum;
import lhind.flights.booking.model.enums.FlightStatusEnum;

import java.util.Date;

@JsonSerialize
public class FlightDTO {
    private Long id;
    private AirlineCodeEnum airlineCode;
    private String flightNumber;
    private String origin;
    private String destination;
    private Date flightDate;
    private Date departureTime;
    private String aircraftType;
//    private FlightStatusEnum flightStatus;

    public FlightDTO() {
    }

    public FlightDTO(Flight flight) {
        this.setId(flight.getId());
        this.setAirlineCode(flight.getAirlineCode());
        this.setFlightNumber(flight.getFlightNumber());
        this.setOrigin(flight.getOrigin());
        this.setDestination(flight.getDestination());
        this.setFlightDate(flight.getFlightDate());
        this.setDepartureTime(flight.getDepartureTime());
        this.setAircraftType(flight.getAircraftType());
//        this.setFlightStatus(flight.getFlightStatus());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AirlineCodeEnum getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(AirlineCodeEnum airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getFlightDate() {
        return flightDate;
    }

    public void setFlightDate(Date flightDate) {
        this.flightDate = flightDate;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

//    public FlightStatusEnum getFlightStatus() {
//        return flightStatus;
//    }
//
//    public void setFlightStatus(FlightStatusEnum flightStatus) {
//        this.flightStatus = flightStatus;
//    }
}
