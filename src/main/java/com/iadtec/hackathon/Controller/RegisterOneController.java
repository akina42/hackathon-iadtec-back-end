package com.iadtec.hackathon.Controller;

import com.iadtec.hackathon.DTO.RegisterOneDTO;
import com.iadtec.hackathon.Service.RegisterOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping("/api/v1/registerOne")
@RestController
public class RegisterOneController {

    @Autowired
    private RegisterOneService registerOneService;

    public RegisterOneController() {
    }

    @GetMapping("/{id}")
    private ResponseEntity<RegisterOneDTO> getRegisterOne(@PathVariable("id") Long id){
       Optional<RegisterOneDTO> registerOneDTOOptional = registerOneService.getRegisterOne(id);
       registerOneDTOOptional.map(registerOneDTO -> new ResponseEntity<>(registerOneDTO, HttpStatus.OK));
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
