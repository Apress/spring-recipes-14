// FINAL
package com.apress.springrecipes.court.web;

import com.apress.springrecipes.court.domain.Reservation;
import com.apress.springrecipes.court.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
// Bind controller to URL /reservationSummary
// initial view will be resolved to the name returned in the default GET method
@RequestMapping("/reservationSummary*")
public class ReservationSummaryController {

    private ReservationService reservationService;

    // Wire service available in application context 
    @Autowired
    public ReservationSummaryController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Note date parameter marked a required="true" inside first method
    // If the URL does not match /reservationSummary*?date=* the method will not execute 
    // NOTE: Since this is the default(and only) GET method,
    //       an error will thrown if there is no match(e.g. no date is passed as to URL) 
    @RequestMapping(method = RequestMethod.GET)
	public String generateSummary(
	   @RequestParam(required = true, value = "date") String selectedDate, Model model) { 
	// Create an empty reservation list 
	List<Reservation> reservations = java.util.Collections.emptyList();
	// Format date
	try { 
	    Date summaryDate = new SimpleDateFormat("yyyy-MM-dd").parse(selectedDate);
	    reservations = reservationService.findByDate(summaryDate);
	    // Catch error if request parameter date is not in format
	} catch (java.text.ParseException ex) { 
	    // Thorow custom error 
	    // Per exceptionMappings this will be redirected to the reservationWebException view
	    // a view that per resolvers will be mapped to /WEB-INF/jsp/error.jsp
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    ex.printStackTrace(pw); 
	    throw new ReservationWebException("Invalid date format for reservation summary",new Date(),sw.toString());
	}
	// Now that the date has passed, add reservations (if any) 
	// Add reservations object to model so it can be display in a view
	model.addAttribute("reservations",reservations);		
	// Check request extension
	/**
	if(request.getServletPath().endsWith(".pdf")) { 
	    // PDF extension
	    // Return view pdfSummary. Via resolver the view
	    // is mapped to the class PdfReservationSummary
	    // as defined in views.properties
	    return "pdfSummary";
	} else if(request.getServletPath().endsWith(".xls")) { 
	    // Excel extension
	    // Return view xlsSummary. Via resolver the view
	    // is mapped to the class ExcelReservationSummary
	    // as defined in views.properties
	    return "excelSummary";
	} else { 
	    // Any other extension return the file system view reservationSummary
	    // Via resolver it will be mapped to /WEB-INF/jsp/reservationSummary.jsp
	    return "reservationSummary";
	    }*/
	return "reservationSummary";
    }
}
