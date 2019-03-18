package com.travix.medusa.toughjet.service;

import com.travix.medusa.domain.common.Supplier;
import com.travix.medusa.domain.common.service.MockService;
import com.travix.medusa.domain.toughjet.ToughJetRequest;
import com.travix.medusa.domain.toughjet.ToughJetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by parisfreire on 11/03/2019.
 */

@Service
public class ToughJetService implements Supplier<ToughJetResponse, ToughJetRequest>{

    @Autowired
    MockService mockService;

    @Override
    public ToughJetResponse getFlights(ToughJetRequest supplierRequest) {
        ToughJetResponse toughJetResponse = getMatchingFlight(mockService.TOUGHJET_FLIGHTS_LIST, supplierRequest);

        return toughJetResponse;
    }

    @Override
    public ToughJetResponse getMatchingFlight(List<ToughJetResponse> supplierFlightsList, ToughJetRequest supplierRequest) {

        List<ToughJetResponse> matchingFlights = mockService.TOUGHJET_FLIGHTS_LIST.stream().filter( flight ->
                /* Rules */
                supplierRequest.getFrom().equals(flight.getDepartureAirportName()) &&
                supplierRequest.getTo().equals(flight.getArrivalAirportName()) &&
                supplierRequest.getOutboundDate().equals(flight.getOutboundDateTime()) &&
                supplierRequest.getInboundDate().equals(flight.getInboundDateTime())

        ).collect(Collectors.toList());

        ToughJetResponse toughJetResponse = matchingFlights.size() != 0 ? matchingFlights.get(0) : null;

        return toughJetResponse;
    }
}
