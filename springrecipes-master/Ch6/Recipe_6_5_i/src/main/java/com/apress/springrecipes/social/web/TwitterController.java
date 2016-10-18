package com.apress.springrecipes.social.web;

import org.springframework.social.twitter.api.TweetData;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by marten on 06-10-14.
 */
@Controller
@RequestMapping("/twitter")
public class TwitterController {

    private final Twitter twitter;


    public TwitterController(Twitter twitter) {
        this.twitter = twitter;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "twitter";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String tweet(@RequestParam("status") String status) {
        twitter.timelineOperations().updateStatus(status);
        return "redirect:/twitter";
    }
}
