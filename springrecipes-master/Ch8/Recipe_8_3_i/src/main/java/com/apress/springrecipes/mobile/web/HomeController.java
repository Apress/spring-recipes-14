package com.apress.springrecipes.mobile.web;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by marten on 23-06-14.
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String index() {
        return "home";
    }

}
