package com.iadtec.hackathon.Service;

import com.iadtec.hackathon.Repository.RegisterOneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterOneService {

    @Autowired
    private RegisterOneRepository registerOneRepository;

    public RegisterOneService() {
    }

}
