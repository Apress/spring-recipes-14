package com.apress.springrecipes.city.servlet;

import com.apress.springrecipes.city.CityService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//@WebServlet(name = "distance", urlPatterns = {"/distance"}, loadOnStartup = 1)
public class DistanceServlet extends HttpServlet {

    private CityService cityService;

    @Override
    public void init() throws ServletException {
        WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        cityService = context.getBean(CityService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String srcCity = request.getParameter("srcCity");
        String destCity = request.getParameter("destCity");

        double distance = cityService.findDistance(srcCity, destCity);
        request.setAttribute("distance", distance);

        forward(request, response);
    }

    private void forward(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/distance.jsp");
        dispatcher.forward(request, response);
    }
}
