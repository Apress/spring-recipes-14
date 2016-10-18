package com.apress.springrecipes.court.domain;

import com.apress.springrecipes.court.service.ReservationService;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by marten on 16-05-14.
 */
public class SportTypeConverter implements Converter<String, SportType> {

    private ReservationService reservationService;

    public SportTypeConverter(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public SportType convert(String source) {
        int sportTypeId = Integer.parseInt(source);
        SportType sportType = reservationService.getSportType(sportTypeId);
        return sportType;
    }
}
