
package com.example.netbooks.services;

import com.example.netbooks.dao.UserRepository;
import com.example.netbooks.dao.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.netbooks.models.User;
import com.example.netbooks.models.VerificationToken;

public class VerificationTokenManager {
    
    @Autowired
    VerificationTokenRepository verificationTokenRepository;
    
    public VerificationToken FindVerificationToken(String verificationToken) {
        return verificationTokenRepository.findByVerificationToken(verificationToken);
    }
    
    public VerificationTokenManager(VerificationTokenRepository verificationTokenRepository)
    {
        this.verificationTokenRepository = verificationTokenRepository;
    }
   
    public VerificationToken SaveToken(User user)
    {
       VerificationToken VerTok = new VerificationToken(user);
       verificationTokenRepository.save(VerTok);
       return VerTok;
    }
}
