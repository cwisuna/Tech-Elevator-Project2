package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;

public class AccountService {
    private final String apiBaseUrl;
    private RestTemplate restTemplate = new RestTemplate();

    public AccountService(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }

    public double getBalance(String token){
        Account account = restTemplate.exchange(apiBaseUrl + "tenmo/balance", HttpMethod.GET, makeAuthEntity(token), Account.class).getBody();
        return account.getBalance();
    }

    //insert method here to list users that you can send money to

//    public double sendMoney(String token){
////        Account account = restTemplate.put(apiBaseUrl + "tenmo/transfer", HttpMethod.PUT, makeAuthEntity(token), Account.class);
//    }

    private HttpEntity makeAuthEntity(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);
        return entity;
    }
}
