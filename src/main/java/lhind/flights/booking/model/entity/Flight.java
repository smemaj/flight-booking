package lhind.flights.booking.model.entity;

import jakarta.persistence.*;
import lhind.flights.booking.model.enums.AirlineCodeEnum;
import lhind.flights.booking.model.enums.FlightClassEnum;
import lhind.flights.booking.model.enums.FlightStatusEnum;

import java.util.*;

@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "airline_code", nullable = false)//check if unique true
    private AirlineCodeEnum airlineCode;
    @Column(name = "flight_number", nullable = false)//unique for 24 hours
    private String flightNumber;
    @Column(name = "origin", nullable = false)
    private String origin;
    @Column(name = "destination", nullable = false)
    private String destination;
    @Column(name = "flight_date", nullable = false)
    @Temporal(value = TemporalType.DATE)
    private Date flightDate;
    @Column(name = "departure_time", nullable = false)
    @Temporal(value = TemporalType.TIME)
    private Date departureTime;
    @Column(name = "aircraft_type")
    private String aircraftType;

//    @Column(name = "total_seats_available")
//    private Map<FlightClassEnum, Integer> totalSeatsAvailable = new HashMap<>();
    @Enumerated(value = EnumType.STRING)
    @Column(name = "flight_status", nullable = false)
    private FlightStatusEnum flightStatus;

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

//    public Map<FlightClassEnum, Integer> getTotalSeatsAvailable() {
//        return totalSeatsAvailable;
//    }

    public FlightStatusEnum getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatusEnum flightStatus) {
        this.flightStatus = flightStatus;
    }

}
