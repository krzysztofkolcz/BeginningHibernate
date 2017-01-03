package com.kkolcz.exceptions;

import org.springframework.web.client.RestClientException;

public class ApiException extends RestClientException {
    private static final long serialVersionUID = 2L;
    private Integer responseCode;
    private String msg;

    public ApiException(Integer responseCode, String msg) {
        super("["+responseCode+"]: "+msg);
        this.responseCode = responseCode;
        this.msg = msg;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public String getMessage() {
        return responseCode+": "+msg;
    }
}
