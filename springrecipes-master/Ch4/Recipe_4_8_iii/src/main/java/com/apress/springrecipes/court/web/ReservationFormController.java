// FINAL
package com.apress.springrecipes.court.web;

import com.apress.springrecipes.court.domain.Player;
import com.apress.springrecipes.court.domain.Reservation;
import com.apress.springrecipes.court.domain.ReservationValidator;
import com.apress.springrecipes.court.domain.SportType;
import com.apress.springrecipes.court.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.List;


@Controller
// Bind controller to URL /reservationForm
// initial view will be resolved to the name returned in the default GET method
@RequestMapping("/reservationForm")
// Add Reservation object to session, since its created on setup and used after submission
@SessionAttributes("reservation") // Command name class was used in earlier Spring versions
public class ReservationFormController {

    private ReservationService reservationService;
    private ReservationValidator validator;

    // Wire service and validator in constructor, available in application context 
    @Autowired
    public ReservationFormController(ReservationService reservationService,
                                     ReservationValidator validator) {
        this.reservationService = reservationService;
        this.validator = validator;
    }


    // Create attribute for model 
    // Will be represented as drop box Sport Types in reservationForm
    @ModelAttribute("sportTypes")
    public List<SportType> populateSportTypes() {
        return reservationService.getAllSportTypes();
    }

    // Controller will always look for a default GET method to call first, irrespective of name
    // In this case, named setupForm to ease identification
    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(
            @RequestParam(required = false, value = "username") String username,
            Model model) {
	// Create inital reservation object
        Reservation reservation = new Reservation();
	// Add player to reservation, since a Player field will be required
        // IMPORTANT - This Player instance will be saved in the Reservation session object
        //             if the Reservation object is not placed in session, the Player instance
	//             would need to be recreated after submission
        reservation.setPlayer(new Player(username, null));
	// Add reservation to model so it can be display in view
        model.addAttribute("reservation", reservation);
	// Return view as a string
	// Based on resolver configuration the reservationForm view
	// will be mapped to a JSP in /WEB-INF/jsp/reservationForm.jsp 
	// NOTE: If the method would have a void return value, by default the method would have 
        //       looked for the same reservationForm view, since the default URL for the 
        //       controller is this same name @RequestMapping("/reservationForm")
        return "reservationForm";
    }

    // Controller will always look for a default POST method irrespective of name
    // when a submission ocurrs on the URL (i.e.@RequestMapping(/reservationForm)) 
    // In this case, named submitForm to ease identification
    @RequestMapping(method = RequestMethod.POST)
    // Model reservation object, BindingResult and SessionStatus as parameters 
    public String submitForm(
            @ModelAttribute("reservation") Reservation reservation,
            BindingResult result, SessionStatus status) {
	// User is finished validate reservation object
        validator.validate(reservation, result);	
        if (result.hasErrors()) {
	    // Errors, return to reservationForm view
            return "reservationForm";
        } else {
	    // No errors make reservation 
            reservationService.make(reservation);
	    // Set complete, mark the handler's session processing as complete
	    // Allowing for cleanup of session attributes.
	    status.setComplete();
	    // Redirect to reservationSuccess URL, defined in ReservationSuccessController
            return "redirect:reservationSuccess";
        }
    }
}
