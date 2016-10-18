package com.apress.springrecipes.social.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import java.util.Collections;

/**
 * Created by marten on 15-09-14.
 */
@Controller
public class SignupController {

    private final ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils();
    private final UserDetailsManager userDetailsManager;

    @Autowired
    public SignupController(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public SignupForm signupForm(WebRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        if (connection != null) {
            return SignupForm.fromProviderUser(connection.fetchUserProfile());
        } else {
            return new SignupForm();
        }
    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public String signup(@Validated SignupForm form, BindingResult formBinding, WebRequest request) {
        if (formBinding.hasErrors()) {
            return null;
        }
        SocialUser user = createUser(form, formBinding);
        if (user != null) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getUsername(), null, null));
            providerSignInUtils.doPostSignUp(user.getUsername(), request);
            return "redirect:/";
        }
        return null;
    }


    /**
     * Helper method. Delegates the insertion to the {@code UserDetailsManager} of Spring Security.
     *
     * @param form
     * @param errors
     * @return
     */
    private SocialUser createUser(SignupForm form, BindingResult errors) {
        SocialUser user = new SocialUser(form.getUsername(), form.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        userDetailsManager.createUser(user);
        return user;
    }
}
