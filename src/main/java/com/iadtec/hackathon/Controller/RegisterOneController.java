package com.iadtec.hackathon.Controller;

import com.iadtec.hackathon.Service.RegisterOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/registerOne")
@RestController
public class RegisterOneController {

    @Autowired
    private RegisterOneService registerOneService;

}
