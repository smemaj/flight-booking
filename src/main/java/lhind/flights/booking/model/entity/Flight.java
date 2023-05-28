package lhind.flights.booking.model.entity;

import jakarta.persistence.*;
import lhind.flights.booking.model.enums.FlightStatusEnum;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "flight")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "airline_code", nullable = false, unique = true)//check if unique true
    private String airlineCode;
    @Column(name = "flight_number", nullable = false, unique = true)//unique for 24 hours
    private String flightNumber;
    @Column(name = "origin", nullable = false, unique = true)
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
    @Column(name = "total_seats_available")//??
    private List totalSeatsAvailable;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "flight_status", nullable = false)
    private FlightStatusEnum flightStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
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

    public List getTotalSeatsAvailable() {
        return totalSeatsAvailable;
    }

    public void setTotalSeatsAvailable(List totalSeatsAvailable) {
        this.totalSeatsAvailable = totalSeatsAvailable;
    }

    public FlightStatusEnum getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatusEnum flightStatus) {
        this.flightStatus = flightStatus;
    }
}
