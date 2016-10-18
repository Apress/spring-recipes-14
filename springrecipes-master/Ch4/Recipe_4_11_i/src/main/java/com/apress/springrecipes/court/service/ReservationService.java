package com.apress.springrecipes.court.service;

import com.apress.springrecipes.court.domain.Reservation;
import com.apress.springrecipes.court.domain.SportType;

import java.util.Date;
import java.util.List;

public interface ReservationService {

    public List<Reservation> query(String courtName);
    public List<Reservation> findByDate(Date date);

    void make(Reservation reservation)
            throws ReservationNotAvailableException;

    List<SportType> getAllSportTypes();
    public SportType getSportType(int sportTypeId);
}
