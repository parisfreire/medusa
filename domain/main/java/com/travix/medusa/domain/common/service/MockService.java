package com.travix.medusa.domain.common.service;

import com.travix.medusa.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.domain.toughjet.ToughJetRequest;
import com.travix.medusa.domain.toughjet.ToughJetResponse;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by parisfreire on 13/03/2019.
 */
@Service
public class MockService {

    public static List<CrazyAirResponse> CRAZYAIR_FLIGHTS_LIST = Arrays.asList(
            new CrazyAirResponse("Iberia", 200, "B", "LGC", "LHR", "2019-03-10", "2019-03-12")
    );

    public static List<ToughJetResponse> TOUGHJET_FLIGHTS_LIST = Arrays.asList(
            new ToughJetResponse("Ryanair", 50, 20, 15, "LGC", "LHR", "2019-03-10", "2019-03-12")
    );

    public CrazyAirRequest mockCrazyAirRequest(){
        CrazyAirRequest crazyAirRequest = new CrazyAirRequest();

        crazyAirRequest.setOrigin("LGC");
        crazyAirRequest.setDestination("LHR");
        crazyAirRequest.setDepartureDate("2019-03-10");
        crazyAirRequest.setReturnDate("2019-03-12");

        return crazyAirRequest;
    }

    public ToughJetRequest mockToughJetRequest(){
        ToughJetRequest toughJetRequest = new ToughJetRequest();

        toughJetRequest.setFrom("LGC");
        toughJetRequest.setTo("LHR");
        toughJetRequest.setOutboundDate("2019-03-10");
        toughJetRequest.setInboundDate("2019-03-12");

        return toughJetRequest;
    }
}
