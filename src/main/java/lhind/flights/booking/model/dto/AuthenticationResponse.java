package lhind.flights.booking.model.dto;
public class AuthenticationResponse extends BaseResponse {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}