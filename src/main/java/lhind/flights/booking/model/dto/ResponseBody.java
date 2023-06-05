package lhind.flights.booking.model.dto;

public class ResponseBody {

    private String response;

    public ResponseBody(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
