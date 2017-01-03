package com.kkolcz.rest; 

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;

public class RestTemplate extends org.springframework.web.client.RestTemplate
{
    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback,
            ResponseExtractor<T> responseExtractor) throws RestClientException {
        Assert.notNull(url, "'url' must not be null");
        Assert.notNull(method, "'method' must not be null");
        ClientHttpResponse rawResponse = null;
        ExtendedHttpResponse response = null;
        try {
            ClientHttpRequest request = createRequest(url, method);

            if (requestCallback != null) {
                requestCallback.doWithRequest(request);
            }

            rawResponse = request.execute();
            response = new ExtendedHttpResponse(rawResponse);

            if (!getErrorHandler().hasError(response)) {
                logResponseStatus(method, url, response);
            }
            else {
                handleResponseError(method, url, response);
            }
            if (responseExtractor != null) {
                return responseExtractor.extractData(response);
            }
            else {
                return null;
            }
        }
        catch (IOException ex) {
            throw new ResourceAccessException("I/O error on " + method.name() +
                    " request for \"" + url + "\":" + ex.getMessage(), ex);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    private void logResponseStatus(HttpMethod method, URI url, ExtendedHttpResponse response)
    {
        if (logger.isInfoEnabled()) {
            try {
                logger.info(
                        method.name() + " request for \"" + url + "\" resulted in " + response.getStatusCode() + " (" +
                                response.getStatusText() + ")");
                logger.info("Response headers: <"+response.getHeaders()+">");
                if (!response.getHeaders().getContentType().toString().contains("application/pdf")) {
                    logger.info("Response body: <"+response.getBodyString()+">");
                }
            }
            catch (IOException e) {
                // ignore
                logger.error(e);
            }
        }
    }

    private void handleResponseError(HttpMethod method, URI url, ExtendedHttpResponse response) throws IOException
    {
        if (logger.isWarnEnabled()) {
            try {
                logger.warn(
                        method.name() + " request for \"" + url + "\" resulted in " + response.getStatusCode() + " (" +
                                response.getStatusText() + "); invoking error handler");
                logger.warn("Response headers: <"+response.getHeaders()+">");
                logger.warn("Response body: <"+response.getBodyString()+">");
            }
            catch (IOException e) {
                // ignore
                logger.error(e);
            }
        }
        getErrorHandler().handleError(response);
    }
}

