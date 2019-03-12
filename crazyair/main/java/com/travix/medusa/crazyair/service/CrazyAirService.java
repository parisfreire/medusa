package com.travix.medusa.crazyair.service;

import com.travix.medusa.domain.common.Supplier;
import com.travix.medusa.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.domain.crazyair.CrazyAirResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by parisfreire on 11/03/2019.
 */

@Service
public class CrazyAirService implements Supplier<CrazyAirResponse, CrazyAirRequest>{

    private static final List<CrazyAirResponse> CRAZYAIR_FLIGHTS_LIST = Arrays.asList(
            new CrazyAirResponse("Iberia", 200, "B", "LGC", "LHR", "2019-03-10", "2019-03-12"),
            new CrazyAirResponse("Vueling", 100, "E", "LGC", "LHR", "2019-03-10", "2019-03-12")
    );

    @Override
    public CrazyAirResponse getFlights(CrazyAirRequest supplierRequest) {
        CrazyAirResponse crazyAirResponse = getMatchingFlight(CRAZYAIR_FLIGHTS_LIST, supplierRequest);

        return crazyAirResponse;
    }

    @Override
    public CrazyAirResponse getMatchingFlight(List<CrazyAirResponse> supplierFlightsList, CrazyAirRequest supplierRequest) {

        List<CrazyAirResponse> matchingFlights = CRAZYAIR_FLIGHTS_LIST.stream().filter( flight ->
                /* Rules */
                supplierRequest.getOrigin().equals(flight.getDepartureAirportCode()) &&
                supplierRequest.getDestination().equals(flight.getDestinationAirportCode()) &&
                supplierRequest.getDepartureDate().equals(flight.getDepartureDate()) &&
                supplierRequest.getReturnDate().equals(flight.getArrivalDate())

        ).collect(Collectors.toList());

        CrazyAirResponse crazyAirResponse = matchingFlights.size() != 0 ? matchingFlights.get(0) : null;

        return crazyAirResponse;
    }
}
