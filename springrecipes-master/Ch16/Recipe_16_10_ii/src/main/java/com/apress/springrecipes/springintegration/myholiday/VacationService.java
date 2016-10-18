package com.apress.springrecipes.springintegration.myholiday;

import org.springframework.integration.annotation.Gateway;

import java.util.List;


public interface VacationService {
    @Gateway(requestChannel = "requests", replyChannel = "responses")
    List<HotelReservation> findHotels(HotelReservationSearch hotelReservationSearch);
}
