package lhind.flights.booking.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lhind.flights.booking.model.entity.Flight;
import lhind.flights.booking.model.enums.AirlineCodeEnum;

import java.util.Date;

@JsonSerialize
public class FlightSearch {


    private String origin;
    private String destination;
    private Date flightDate;
    private AirlineCodeEnum airlineCode;

//    public FlightSearch(Flight flight) {
//        this.setAirlineCode(flight.getAirlineCode());
//        this.setDestination(flight.getDestination());
//        this.setFlightDate(flight.getFlightDate());
//        this.setAirlineCode(flight.getAirlineCode());
//    }

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

    public AirlineCodeEnum getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(AirlineCodeEnum airlineCode) {
        this.airlineCode = airlineCode;
    }
}
