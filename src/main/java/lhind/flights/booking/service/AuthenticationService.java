package lhind.flights.booking.service;

import lhind.flights.booking.model.dto.AuthenticationRequest;
import lhind.flights.booking.model.dto.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
