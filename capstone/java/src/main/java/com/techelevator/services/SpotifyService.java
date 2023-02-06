package com.techelevator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class SpotifyService {

    private String oauthURL = "https://accounts.spotify.com/api/token";
    private RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    private String apiKey = "fd66a5913e534ed5a013e7c2328efb1a";
    private String apiSecret = "8b689262349f473792e7211c32057500";


    public String getToken() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth(apiKey, apiSecret );
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = "grant_type=client_credentials";
        HttpEntity<String> request = new HttpEntity<>(body, httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(this.oauthURL, HttpMethod.POST, request, String.class);

        String token = "";
        try {
            JsonNode tokenServiceResponse = objectMapper.readTree(response.getBody());
             token = tokenServiceResponse.path("access_token").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return token;
    }

    public String searchSongId(String search) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(getToken());
        String url = "https://api.spotify.com/v1/search?q=";
        String url2 = "&type=track";
        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        ResponseEntity<String> response = restTemplate.exchange(url + search + url2, HttpMethod.GET, request, String.class);

        System.out.println(response);
        return null;
    }


}
