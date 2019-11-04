package com.example.netbooks.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.netbooks.dao.UserRepository;
import com.example.netbooks.dao.VerificationTokenRepository;
import com.example.netbooks.services.EmailSender;
import com.example.netbooks.models.User;
import com.example.netbooks.models.VerificationToken;
import com.example.netbooks.services.UserManager;
import com.example.netbooks.services.VerificationTokenManager;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthenticationController {

    @Autowired
    private UserManager userManager;
    @Autowired
    private VerificationTokenManager verificationTokenManager;
    @Autowired
    EmailSender emailSender;
    private final Logger logger = LogManager.getLogger(AuthenticationController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
    public String firstPage() {
        return "qwer";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void registerUser(User user) {
        if (userManager.RegisterUser(user)) {
            VerificationToken VerTok = verificationTokenManager.SaveToken(user);
            emailSender.sendMessage(user.getEmail(), "Complete Registration!", VerTok.getVerificationToken());
            logger.info("Complete Registration!" + user.getFirstName());
        } else {
            logger.info("Registration failed!" + user.getFirstName());

        }
    }

    @RequestMapping(value = "/verification-account", method = {RequestMethod.GET, RequestMethod.POST})
    public void confirmUserAccount(@RequestParam("token") String verificationToken) {
        VerificationToken token = verificationTokenManager.FindVerificationToken(verificationToken);

        if (token != null) {
            userManager.SaveUser(userManager.GetUserByMail(token.getUser()));
            //todo smth message for clien (account Verified)
        } else {

            //todo smth message for clien (link is invalid)
        }

    }

}
