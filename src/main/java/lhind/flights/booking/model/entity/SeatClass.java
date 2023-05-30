package lhind.flights.booking.model.entity;

import lhind.flights.booking.model.enums.FlightClassEnum;

public class SeatClass {
    private FlightClassEnum flightClass;
    private Integer availableSeats = 0;

    public SeatClass() {
    }

    public SeatClass(FlightClassEnum flightClass, Integer availableSeats) {
        this.flightClass = flightClass;
        this.availableSeats = availableSeats;
    }

    public FlightClassEnum getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(FlightClassEnum flightClass) {
        this.flightClass = flightClass;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }
}
