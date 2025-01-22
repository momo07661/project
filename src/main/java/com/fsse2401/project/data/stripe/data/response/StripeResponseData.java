package com.fsse2401.project.data.stripe.data.response;

public class StripeResponseData {
    private String sessionId;
    private String url;

    public StripeResponseData(String url, String sessionId) {
        this.url = url;
        this.sessionId = sessionId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
