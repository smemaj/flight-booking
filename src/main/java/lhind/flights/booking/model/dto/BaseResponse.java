package lhind.flights.booking.model.dto;

import java.util.List;

public class BaseResponse {

    private List<String> messages;

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}

