package com.fsse2401.project.data.stripe.domainObject.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fsse2401.project.data.stripe.data.response.StripeResponseData;

public class StripeResponseDto {
    @JsonProperty("session_id")
    private String sessionID;
    private String url;

    public StripeResponseDto(StripeResponseData data) {
        this.sessionID = data.getSessionId();
        this.url = data.getUrl();
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
