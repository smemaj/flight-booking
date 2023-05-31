package lhind.flights.booking.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lhind.flights.booking.model.enums.ClaimIdentifier;
import lhind.flights.booking.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TokenServiceImpl implements TokenService {

    private static final Long FIVE_HOURS = 5L * 60 * 60 * 1000;
    private final Key key;

    public TokenServiceImpl(Key key) {
        this.key = key;
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        final Map<String, Object> claims = new HashMap<>();
        claims.put(ClaimIdentifier.AUTHORITIES.name(), userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return createToken(claims, userDetails.getUsername());
    }

    @Override
    public Boolean validateToken(String token, UserDetails user) {
        return extractUsername(token).equals(user.getUsername()) && !isTokenExpired(token);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractAllClaims(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + FIVE_HOURS))
                .signWith(key)
                .compact();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

}
