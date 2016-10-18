package com.apress.springrecipes.board.web;


import com.apress.springrecipes.board.domain.Message;
import com.apress.springrecipes.board.service.MessageBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List; 


@Controller
@RequestMapping("/messageList*")
public class MessageListController {

    private MessageBoardService messageBoardService;

    // Wire service in constructor, available in application context 
    @Autowired
    public MessageListController(MessageBoardService messageBoardService) {
        this.messageBoardService = messageBoardService;
    }
    

    // Controller will always look for a default GET method to call first, irrespective of name
    // In this case, named setupForm to ease identification
    @RequestMapping(method = RequestMethod.GET)
	public String generateList(Model model) {
	// Create an empty messages list 
	List<Message> messages = java.util.Collections.emptyList();
	messages = messageBoardService.listMessages();
	// Update model to include reservations
	model.addAttribute("messages", messages);
        return "messageList";
    }
}
