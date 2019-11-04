package com.example.netbooks.services;

import com.example.netbooks.dao.UserRepository;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.netbooks.models.User;

@Component
public class UserManager {

    @Autowired
    UserRepository userRepository;

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User GetUserByMail(User user) {
        return userRepository.findByEmail(user.getEmail());
    }

    public boolean SaveUser(User user) {
        return userRepository.save(user);
    }

    public boolean RegisterUser(User user) {
        if (GetUserByMail(user) != null) {
            return false;
        } else {
            SaveUser(user);//into db
            return true;
        }
    }

}
