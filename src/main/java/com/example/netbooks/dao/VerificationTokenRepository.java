package com.example.netbooks.dao;

import com.example.netbooks.models.VerificationToken;
import org.springframework.stereotype.Repository;

@Repository
public class VerificationTokenRepository {

    public void save(VerificationToken verificationToken) {
        //save into db
    }

    public VerificationToken findByVerificationToken(String verificationToken) {
        //////////
        return new VerificationToken();
    }
}
