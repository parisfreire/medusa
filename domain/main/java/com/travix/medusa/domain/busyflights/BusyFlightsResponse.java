package com.travix.medusa.domain.busyflights;

import java.text.DecimalFormat;

public class BusyFlightsResponse {

    private String airline;
    private String supplier;
    private Double fare;
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;
    private String arrivalDate;

    public String getAirline() {
        return airline;
    }
    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Double getFare() {
        return fare;
    }
    public void setFare(Double fare) {

        DecimalFormat df = new DecimalFormat("###.##");
        this.fare = Double.parseDouble(df.format(fare));
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }
    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode.substring(0,2);
    }

    public String getDestinationAirportCode() {
        return destinationAirportCode;
    }
    public void setDestinationAirportCode(String destinationAirportCode) {
        this.destinationAirportCode = destinationAirportCode.substring(0,2);
    }

    public String getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}
