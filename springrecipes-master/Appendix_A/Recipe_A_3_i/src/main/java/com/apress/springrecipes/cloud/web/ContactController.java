package com.apress.springrecipes.cloud.web;

import com.apress.springrecipes.cloud.Contact;
import com.apress.springrecipes.cloud.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by marten on 06-10-14.
 */
@Controller
@RequestMapping("/contact")
public class ContactController {

    private final ContactRepository contactRepository;


    @Autowired
    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("contacts", contactRepository.findAll());
        return "list";
    }


    @RequestMapping(value="/new", method=RequestMethod.GET)
    public String newContact(Model model) {
        model.addAttribute(new Contact());
        return "contact";
    }

    @RequestMapping(value="/new", method=RequestMethod.POST)
    public String newContact(@ModelAttribute Contact contact) {
        contactRepository.save(contact);
        return "redirect:/contact";
    }

}
