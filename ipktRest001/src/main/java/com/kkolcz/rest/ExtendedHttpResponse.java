package com.kkolcz.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;

public class ExtendedHttpResponse implements ClientHttpResponse {
    private ClientHttpResponse originalResponse;
    private byte[] body;

    public ExtendedHttpResponse(ClientHttpResponse response) {
        setOriginalResponse(response);
    }

    @Override
    public InputStream getBody() throws IOException {
        if (body != null) {
            return new ByteArrayInputStream(body);
        } else {
            return null;
        }
    }

    public String getBodyString() {
        if (body != null) {
            return new String(body);
        } else {
            return null;
        }
    }

    @Override
    public HttpHeaders getHeaders() {
        return originalResponse.getHeaders();
    }

    @Override
    public HttpStatus getStatusCode() throws IOException {
        return originalResponse.getStatusCode();
    }

    @Override
    public int getRawStatusCode() throws IOException {
        return originalResponse.getRawStatusCode();
    }

    @Override
    public String getStatusText() throws IOException {
        return originalResponse.getStatusText();
    }

    @Override
    public void close() {
        originalResponse.close();
    }

    public ClientHttpResponse getOriginalResponse() {
        return originalResponse;
    }

    public void setOriginalResponse(ClientHttpResponse originalResponse) {
        this.originalResponse = originalResponse;
        try {
            InputStream ins = originalResponse.getBody();
            if (ins != null) {
                this.body = convertBodyToByteStream(ins);
            }
        } catch (IOException e) {
            // ignore
        }
    }

    protected byte[] convertBodyToByteStream(InputStream body) {
        try {
            return IOUtils.toByteArray(body);
        } catch (IOException e) {
            return null;
        }
    }

}

