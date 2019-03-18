package com.travix.medusa.busyflights.service;

import com.travix.medusa.domain.common.MyErrorHandler;
import com.travix.medusa.domain.common.service.MockService;
import com.travix.medusa.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.domain.toughjet.ToughJetRequest;
import com.travix.medusa.domain.toughjet.ToughJetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by parisfreire on 11/03/2019.
 */
@Service
public class BusyFlightsService {

    @Autowired
    MockService mockService;

    //TODO: Refactor exposing ENDPOINTS to a shared config

    private final static String CRAZYAIR_URL = "http://localhost:8081/crazyair/getFlights";
    private final static String TOUGHJET_URL = "http://localhost:8082/toughjet/getFlights";

    public CrazyAirResponse getCrazyAirFlights(){

        CrazyAirRequest crazyAirRequest = mockService.mockCrazyAirRequest();

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new MyErrorHandler());

        HttpHeaders headers = addHttpHeaders();
        HttpEntity<CrazyAirRequest> entity = new HttpEntity<>(crazyAirRequest, headers);

        ResponseEntity<CrazyAirResponse> result = restTemplate.exchange(CRAZYAIR_URL, HttpMethod.POST, entity, CrazyAirResponse.class);

        return result.getBody();
    }

    public ToughJetResponse getToughJetFlights(){

        ToughJetRequest toughJetRequest = mockService.mockToughJetRequest();

        RestTemplate restTemplate = new RestTemplate();
        //Setting MyErrorHandler implementation.
        restTemplate.setErrorHandler(new MyErrorHandler());

        HttpHeaders headers = addHttpHeaders();
        HttpEntity<ToughJetRequest> entity = new HttpEntity<>(toughJetRequest, headers);

        //TODO: controlar esta excepcion
        ResponseEntity<ToughJetResponse> result = restTemplate.exchange(TOUGHJET_URL, HttpMethod.POST, entity, ToughJetResponse.class);

        return result.getBody();
    }

    private HttpHeaders addHttpHeaders(){

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }


}
