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
import java.util.LinkedList;
import org.springframework.web.bind.annotation.RequestBody;

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

    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    public void registerUser(@RequestBody User user) {
        if (userManager.GetUserByMail(user) == null) {
            VerificationToken VerTok = verificationTokenManager.SaveToken(user);
            emailSender.sendMessage(user.getEmail(), "Complete Registration!", VerTok.getVerificationToken());
            logger.info("Complete Registration!" + user.getFirstName());
        } else {
            logger.info("Registration failed!" + user.getFirstName());

        }
    }

    @RequestMapping(value = "/verification-account", method = {RequestMethod.POST})
    public void confirmUserAccount(@RequestBody String verificationToken) {
        VerificationToken token = verificationTokenManager.FindVerificationToken(verificationToken);

        if (token != null) {
            userManager.SaveUser(token.getUser());
            verificationTokenManager.RemoveVerificationToken(verificationToken);
        } else {

            //todo smth message for clien (link is invalid)
        }

    }
    
    //принимает строку имейла юзера и создает ему новый акк с ролью админа
    @RequestMapping(value = "/createAdmin", method = RequestMethod.POST)
    public void CreateAdmin(@RequestBody String mail) {
        User user = userManager.GetUserByMail(mail);
        if (user != null) {
            user = userManager.CreateAdmin(user);
            if(user != null)
            {
            emailSender.sendMessage(mail, 
                    "Complete admin registration!", 
                    user.getPassword());
            logger.info("Complete Registration!" + user.getFirstName());
            }
        } else {
            logger.info("Registration failed!");

        }
    }
    
    @RequestMapping(value = "/createModerator", method = RequestMethod.POST)
    public void CreateModerator(@RequestBody String mail) {
        User user = userManager.GetUserByMail(mail);;
        if (user != null) {
            user = userManager.CreateModerator(user);
             if(user != null)
             {
            emailSender.sendMessage(mail, 
                    "Complete admin registration!", 
                    user.getPassword());
            logger.info("Complete Registration!" + user.getFirstName());
             }
        } else {
            logger.info("Registration failed!");

        }
    }
    
    
    @RequestMapping(value = "/Users", method = {RequestMethod.GET, RequestMethod.POST})
    public LinkedList<User> getAllUsers() {
        return userManager.GetAllUsers();
    }
    
    @RequestMapping(value = "/Tokens", method = {RequestMethod.GET, RequestMethod.POST})
    public LinkedList<VerificationToken> getAllVerificationTokens() {
        return verificationTokenManager.GetAllVerificationTokens();
    }
}
