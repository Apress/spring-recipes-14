package com.apress.springrecipes.city.servlet;

import com.apress.springrecipes.city.CityService;
import org.springframework.web.HttpRequestHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Simple POJO that will have its beens injected
 */
public class DistanceHttpRequestHandler implements HttpRequestHandler {

    private final CityService cityService;

    public DistanceHttpRequestHandler(CityService cityService) {
        this.cityService = cityService;
    }

    @Override
    public void handleRequest(final HttpServletRequest request, final HttpServletResponse response)
        throws ServletException, IOException {
        if (request.getMethod().toUpperCase().equals("POST")) {
            String srcCity = request.getParameter("srcCity");
            String destCity = request.getParameter("destCity");
            double distance = cityService.findDistance(srcCity, destCity);
            request.setAttribute("distance", distance);
        }
        forward(request, response);
    }

    private void forward(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/distance.jsp");
        dispatcher.forward(request, response);
    }
}
