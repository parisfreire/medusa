package com.travix.medusa.domain.common;

import java.util.List;

/**
 * Created by parisfreire on 11/03/2019.
 */
public interface Supplier<T, U> {

    T getFlights(U supplierRequest);
    T getMatchingFlight(List<T> supplierFlightsList, U supplierRequest);
}
