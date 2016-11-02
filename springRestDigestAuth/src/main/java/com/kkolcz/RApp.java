package com.kkolcz;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.DigestScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;




import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.kkolcz.config.ClientConfig;

public class RApp {
  public static void main(String[] args) {

      ApplicationContext context = new AnnotationConfigApplicationContext(ClientConfig.class);
      RestTemplate restTemplate = (RestTemplate) context.getBean("restTemplate");
      String uri = "http://localhost:8080/panel/api/ifi/sprawdz-domene/email/info@testing0320.pl/domena/testing0320.pl/abonentId/451266.json";
      ResponseEntity<SpfStatus> entity = restTemplate.exchange(uri, HttpMethod.GET, null, SpfStatus.class);
      System.out.println(entity);
  }
}
