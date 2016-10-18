package com.apress.springrecipes.springintegration;

import com.apress.springrecipes.springintegration.myholiday.HotelReservation;
import com.apress.springrecipes.springintegration.myholiday.HotelReservationSearch;
import com.apress.springrecipes.springintegration.myholiday.VacationService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Throwable {
        // Start server
        ApplicationContext ctx = new ClassPathXmlApplicationContext("server-integration-context.xml");

        // Start client and issue search
        ApplicationContext clientCtx = new ClassPathXmlApplicationContext("client-integration-context.xml");
        VacationService vacationService = clientCtx.getBean(VacationService.class);

        Date now = new Date();
        HotelReservationSearch hotelReservationSearch = new HotelReservationSearch(200f, 2, DateUtils.addDays(now, 1), DateUtils.addDays(now, 8));
        List<HotelReservation> results = vacationService.findHotels(hotelReservationSearch);

        System.out.printf("Found %s results.", results.size());
        System.out.println();

        for (HotelReservation reservation : results) {
            System.out.printf("\t%s", reservation.toString());
            System.out.println();
        }
    }
}
