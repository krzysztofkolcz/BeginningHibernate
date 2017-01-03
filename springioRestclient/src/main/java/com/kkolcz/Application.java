package com.kkolcz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String args[]) {
        SpringApplication.run(Application.class);
    }

    @Override
    public void run(String... args) throws Exception {
        /* RestTemplate restTemplate = new RestTemplate(); */
        /* Quote quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", Quote.class); */
        /* User user = restTemplate.getForObject("http://localhost:8080/websystiqueRest002/user/1", User.class); */
        /* log.info(user.toString()); */
        /* SpfStatus spfStatus = restTemplate.getForObject("http://localhost:8080/panel/api/ifi/sprawdz-domene/email/info@testing0320.pl/domena/testing0320.pl/abonentId/451266.json", SpfStatus.class); */
        /* log.info(spfStatus.toString()); */

        /* ipakiet korzysta z autoryzacji digest */

        /* curl --digest -udevapi:devApiPass http://localhost:8080/panel/api/ifi/sprawdz-domene/email/info@testing0321.pl/domena/testing0321.pl/abonentId/451266.json */

        String uri = "http://localhost:8080/panel/api/ifi/sprawdz-domene/email/info@testing0320.pl/domena/testing0320.pl/abonentId/451266.json";

        /* restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<T>(createHeaders(username, password)), clazz); */
        /* restTemplate.exchange(uri, HttpMethod.GET, new HttpEntity<T>(createHeaders(username, password)), clazz); */
        /* HttpEntity<String> request = new HttpEntity<String>(createHeaders("devapi","devApiPass")); */
        /* ResponseEntity<SpfStatus> response = restTemplate.exchange(uri, HttpMethod.GET, request, SpfStatus.class); */
        /* log.info(response.toString()); */
        


        /*
        HttpClient client = new HttpClient();
        Credentials defaultcreds = new UsernamePasswordCredentials("devapi", "devApiPass");
        client.getState().setCredentials(new AuthScope("localhost", 8080, AuthScope.ANY_REALM), defaultcreds);
        CommonsClientHttpRequestFactory commons = new CommonsClientHttpRequestFactory(client);

        RestTemplate restTemplate = new RestTemplate(commons);
        String result = restTemplate.getForObject(uri, SpfStatus.class);

        log.info(response.toString());
        */

        /*
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        InternalHttpClient client = (InternalHttpClient) factory.getHttpClient();
        client.getCredentialsProvider().setCredentials(
                new AuthScope("localhost", 8080),
                new UsernamePasswordCredentials("devapi", "devApiPass"));
        RestTemplate restTemplate = new RestTemplate(factory);
        */
        /* String result = restTemplate.getForObject(uri, SpfStatus.class); */
        /* SpfStatus result = restTemplate.getForObject(uri, SpfStatus.class); */

        /* log.info(response.toString()); */
        /* System.out.println(result.toString()); */
        ResponseEntity<Foo> entity = restTemplate.exchange(uri, HttpMethod.GET, null, SpfStatus.class);
        System.out.println(entity.getStatusCode());
    }

}
