package com.kkolcz.error;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import com.kkolcz.exceptions.EmptyBodyException;
import com.kkolcz.exceptions.ApiException;
import com.kkolcz.types.GenericResponse;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class ErrorHandler extends org.springframework.web.client.DefaultResponseErrorHandler {

    private ObjectMapper mapper;

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public boolean hasError(ClientHttpResponse response) throws IOException {
        boolean hasErrorStatusCode = super.hasError(response);
        if (!hasErrorStatusCode && isResponseTypeJSON(response)) {
            return (responseCode(response) != 0);
        } else {
            return hasErrorStatusCode;
        }
    }

    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = response.getStatusCode();
        switch (statusCode.series()) {
            case CLIENT_ERROR:
                throw new HttpClientErrorException(statusCode, response.getStatusText(),
                        response.getHeaders(), getResponseBody(response), getCharset(response));
            case SERVER_ERROR:
                throw new HttpServerErrorException(statusCode, response.getStatusText(),
                        response.getHeaders(), getResponseBody(response), getCharset(response));
            default:
                if (response.getBody() != null) {
                    GenericResponse resp;
                    try {
                        resp = getGenericResponse(response);
                        if (resp.getCode() != 0) {
                            throw new ApiException(resp.getCode(), resp.getInformation());
                        }
                    } catch (EmptyBodyException e) {
                    }
                }
                throw new RestClientException("Unknown status code [" + statusCode + "]");
        }
    }

    private boolean isResponseTypeJSON(ClientHttpResponse response) {
        return response.getHeaders().getContentType().includes(MediaType.APPLICATION_JSON);
    }

    private GenericResponse getGenericResponse(ClientHttpResponse response) throws EmptyBodyException {
        GenericResponse data = null;
        String body = new String(getResponseBody(response));
        if (body.isEmpty()) {
            throw new EmptyBodyException();
        } else {
            try {
                data = mapper.readValue(body, GenericResponse.class);
            } catch (JsonParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JsonMappingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return data;
        }
    }

    private Integer responseCode(ClientHttpResponse response) {
        GenericResponse data = null;
        try {
            data = mapper.readValue(new String(getResponseBody(response)), GenericResponse.class);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return data != null ? data.getCode() : null;
    }

    private byte[] getResponseBody(ClientHttpResponse response) {
        try {
            InputStream responseBody = response.getBody();
            if (responseBody != null) {
                return FileCopyUtils.copyToByteArray(responseBody);
            }
        }
        catch (IOException ex) {
            // ignore
        }
        return new byte[0];
    }

    private Charset getCharset(ClientHttpResponse response) {
        HttpHeaders headers = response.getHeaders();
        MediaType contentType = headers.getContentType();
        return contentType != null ? contentType.getCharSet() : null;
    }
}

