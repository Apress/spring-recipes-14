package com.apress.springrecipes.board.web;

import com.apress.springrecipes.board.domain.Message;
import com.apress.springrecipes.board.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// Bind controller to URL /reservationForm
// initial view will be resolved to the name returned in the default GET method
@Controller
@RequestMapping("/messagePost*")
//@SessionAttributes("message") // Command name class was used in earlier Spring versions
public class MessagePostController {

    private MessageBoardService messageBoardService;


    @Autowired
    public void MessagePostController(MessageBoardService messageBoardService) {
        this.messageBoardService = messageBoardService;
    }


    // Controller will always look for a default GET method to call first, irrespective of name
    // In this case, named setupForm to ease identification
    @RequestMapping(method = RequestMethod.GET)
    public String setupForm(Model model) {
	// Create inital message object
        Message message = new Message();
	// Add message to model so it can be display in view
        model.addAttribute("message", message);
        return "messagePost";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@ModelAttribute("message") Message message, BindingResult result) { //, SessionStatus status) { 
        if (result.hasErrors()) {
	    // Errors, return to Form view
            return "messagePost";
        } else {
	    // No errors make reservation 
            messageBoardService.postMessage(message);
	    // Set complete, mark the handler's session processing as complete
	    // Allowing for cleanup of session attributes.
	    //status.setComplete();
	    // Redirect to reservationSuccess URL, defined in ReservationSuccessController
            return "redirect:messageList";
        }
    }
}
