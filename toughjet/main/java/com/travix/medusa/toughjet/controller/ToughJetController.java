package com.travix.medusa.toughjet.controller;

import com.travix.medusa.domain.toughjet.ToughJetRequest;
import com.travix.medusa.domain.toughjet.ToughJetResponse;
import com.travix.medusa.toughjet.service.ToughJetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by parisfreire on 11/03/2019.
 */

@RestController
@RequestMapping("/toughjet")
public class ToughJetController {

    @Autowired
    ToughJetService toughJetService;

        @GetMapping("/helloToughJet")
        public String helloToughJet() {
            return "hello from Tough Jet";
        }

    @PostMapping("/getFlights")
    public ResponseEntity<ToughJetResponse> getFlights(
            @RequestBody ToughJetRequest toughJetRequest) {

        ToughJetResponse toughJetResponse = toughJetService.getFlights(toughJetRequest);

        return new ResponseEntity<>(toughJetResponse, HttpStatus.OK);
    }
}
