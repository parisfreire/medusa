package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.service.BusyFlightsService;
import com.travix.medusa.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.domain.toughjet.ToughJetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Created by parisfreire on 11/03/2019.
 */

@RestController
@RequestMapping("/busyflights")
public class BusyFlightsController {

    @Autowired
    BusyFlightsService busyFlightsService;

    @PostMapping("/getFlights")
    public ResponseEntity<List<BusyFlightsResponse>> getFlights(
            @RequestBody BusyFlightsRequest busyFlightsRequest) {

        CrazyAirResponse crazyAirResponse = busyFlightsService.getCrazyAirFlights();
        ToughJetResponse toughJetResponse = busyFlightsService.getToughJetFlights();

        BusyFlightsResponse busyFlightsResponse1 = getBusyFlightsResponseFromCrazyAirFlights(crazyAirResponse);
        BusyFlightsResponse busyFlightsResponse2 = getBusyFlightsResponseFromToughJetFlights(toughJetResponse);

        List<BusyFlightsResponse> busyFlightsResponseList = new ArrayList<>();

        busyFlightsResponseList.add(busyFlightsResponse1);
        busyFlightsResponseList.add(busyFlightsResponse2);

        //Comparator null-safe
        busyFlightsResponseList.sort(Comparator.comparing(BusyFlightsResponse::getFare, Comparator.nullsLast(Comparator.naturalOrder())));

        return new ResponseEntity<>(busyFlightsResponseList, HttpStatus.OK);
    }

    private BusyFlightsResponse getBusyFlightsResponseFromCrazyAirFlights(CrazyAirResponse crazyAirResponse){

        BusyFlightsResponse busyFlightsResponse1 = new BusyFlightsResponse();

        busyFlightsResponse1.setAirline(crazyAirResponse.getAirline());
        busyFlightsResponse1.setSupplier("CRAZYAIR");
        busyFlightsResponse1.setFare(crazyAirResponse.getPrice());
        busyFlightsResponse1.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
        busyFlightsResponse1.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
        busyFlightsResponse1.setDepartureDate(crazyAirResponse.getDepartureDate());
        busyFlightsResponse1.setArrivalDate(crazyAirResponse.getArrivalDate());

        return busyFlightsResponse1;
    }

    private BusyFlightsResponse getBusyFlightsResponseFromToughJetFlights(ToughJetResponse toughJetResponse){

        BusyFlightsResponse busyFlightsResponse2 = new BusyFlightsResponse();

        busyFlightsResponse2.setAirline(toughJetResponse.getCarrier());
        busyFlightsResponse2.setSupplier("TOUGHJET");
        busyFlightsResponse2.setFare(toughJetResponse.getTotalPrice());
        busyFlightsResponse2.setDepartureAirportCode(toughJetResponse.getDepartureAirportName());
        busyFlightsResponse2.setDestinationAirportCode(toughJetResponse.getArrivalAirportName());
        busyFlightsResponse2.setDepartureDate(toughJetResponse.getOutboundDateTime());
        busyFlightsResponse2.setArrivalDate(toughJetResponse.getInboundDateTime());

        return busyFlightsResponse2;
    }
}
