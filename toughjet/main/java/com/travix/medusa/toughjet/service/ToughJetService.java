package com.travix.medusa.toughjet.service;

import com.travix.medusa.domain.common.Supplier;
import com.travix.medusa.domain.toughjet.ToughJetRequest;
import com.travix.medusa.domain.toughjet.ToughJetResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by parisfreire on 11/03/2019.
 */

@Service
public class ToughJetService implements Supplier<ToughJetResponse, ToughJetRequest>{

    private static final List<ToughJetResponse> TOUGHJET_FLIGHTS_LIST = Arrays.asList(
            new ToughJetResponse("Ryanair", 50, 20, 15, "LGC", "LHR", "2019-03-10", "2019-03-12"),
            new ToughJetResponse("Norwegian", 100, 20, 5, "LGC", "LHR", "2019-03-10", "2019-03-12")
    );

    @Override
    public ToughJetResponse getFlights(ToughJetRequest supplierRequest) {
        ToughJetResponse toughJetResponse = getMatchingFlight(TOUGHJET_FLIGHTS_LIST, supplierRequest);

        return toughJetResponse;
    }

    @Override
    public ToughJetResponse getMatchingFlight(List<ToughJetResponse> supplierFlightsList, ToughJetRequest supplierRequest) {

        List<ToughJetResponse> matchingFlights = TOUGHJET_FLIGHTS_LIST.stream().filter( flight ->
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
