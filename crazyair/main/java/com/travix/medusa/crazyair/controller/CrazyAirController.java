package com.travix.medusa.crazyair.controller;

import com.travix.medusa.crazyair.service.CrazyAirService;
import com.travix.medusa.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.domain.crazyair.CrazyAirResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by parisfreire on 11/03/2019.
 */

 @RestController
 @RequestMapping("/crazyair")
 public class CrazyAirController {

    @Autowired
    CrazyAirService crazyAirService;

     @GetMapping("/helloCrazyAir")
     public String helloCrazyAir() { return "hello from Crazy Air"; }

    @PostMapping("/getFlights")
    public ResponseEntity<CrazyAirResponse> getFlights(
            @RequestBody CrazyAirRequest crazyAirRequest) {

        CrazyAirResponse crazyAirResponse = crazyAirService.getFlights(crazyAirRequest);

        return new ResponseEntity<>(crazyAirResponse, HttpStatus.OK);
    }
}

