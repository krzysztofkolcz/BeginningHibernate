package com.kkolcz.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import com.kkolcz.HttpComponentsClientHttpRequestFactoryDigestAuth;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {
    private static final String DEFAULT_USER = "devapi";
    private static final String DEFAULT_PASS = "devApiPass";

    public ClientConfig() {
        super();
    }

    @Bean
    public RestTemplate restTemplate() {
        final HttpHost host = new HttpHost("localhost", 8080, "http");
        final CloseableHttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider()).useSystemProperties().build();

        final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactoryDigestAuth(host, client);
        final RestTemplate restTemplate = new RestTemplate(requestFactory);

        final int timeout = 5;
        configureTimeouts(requestFactory, timeout);

        return restTemplate;
    }

    private final void configureTimeouts(final HttpComponentsClientHttpRequestFactory requestFactory, final int timeout) {
        final HttpClient httpClient = requestFactory.getHttpClient();
    }

    private final CredentialsProvider provider() {
        final CredentialsProvider provider = new BasicCredentialsProvider();
        final UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(DEFAULT_USER, DEFAULT_PASS);
        provider.setCredentials(AuthScope.ANY, credentials);
        return provider;
    }

}
