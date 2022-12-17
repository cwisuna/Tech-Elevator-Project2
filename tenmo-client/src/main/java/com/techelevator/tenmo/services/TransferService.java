package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class TransferService {

    private final String apiBaseUrl;
    private final RestTemplate restTemplate = new RestTemplate();


    public TransferService(String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }

    public void createTransfer(Transfer transfer, String token){
        try {
            restTemplate.exchange(apiBaseUrl + "tenmo/transfer", HttpMethod.POST, makeAuthEntity(transfer, token), Void.class);
        } catch (Exception e){
            System.out.println("Unable to complete transaction.");
        }
    }

    public Transfer listAllTransfer(){
//        try {
//          //  return restTemplate.getForObject(apiBaseUrl + "tenmo/transfer", HttpMethod.GET, Transfer.class);
//        } catch (Exception e){
//            System.out.println("Unable to list transfers.");
//        }
        return null;
    }




    private HttpEntity makeAuthEntity(Transfer transfer, String token){
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(transfer, headers);
        return entity;
    }

}
