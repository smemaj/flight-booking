package lhind.flights.booking.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenService {

    String generateToken(UserDetails userDetails);

    Boolean validateToken(String token, UserDetails user);

    String extractUsername(String token);

}

