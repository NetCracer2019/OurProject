package com.example.netbooks.dao;

import com.example.netbooks.models.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    public boolean save(User User) {
        //save into DB
        return true;
    }

    public User findByEmail(String mail) {
        /////
        return new User();
    }

}
