package com.iadtec.hackathon.Controller;

import com.iadtec.hackathon.DTO.RegisterOneRequestDTO;
import com.iadtec.hackathon.DTO.RegisterOneResponseDTO;
import com.iadtec.hackathon.Service.RegisterOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/registerOne")
@RestController
public class RegisterOneController {

    @Autowired
    private RegisterOneService registerOneService;

    public RegisterOneController() {
    }

    @GetMapping("/{id}")
    private ResponseEntity<RegisterOneResponseDTO> getRegisterOne(@PathVariable("id") Long id){
       ResponseEntity<RegisterOneResponseDTO> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
       Optional<RegisterOneResponseDTO> registerOneResponse = registerOneService.getRegisterOne(id);
       if(registerOneResponse.isPresent()){
           response = new ResponseEntity<>(registerOneResponse.get(), HttpStatus.OK);
       }
       return response;
    }

    @GetMapping()
    private ResponseEntity<List<RegisterOneResponseDTO>> getAllRegisterOne(){
        ResponseEntity<List<RegisterOneResponseDTO>> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        List<RegisterOneResponseDTO> allRegisterOneResponseDTO = registerOneService.getAllRegisterOne();
        if(!allRegisterOneResponseDTO.isEmpty()){
            response = new ResponseEntity<>(allRegisterOneResponseDTO, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping()
    private ResponseEntity<RegisterOneResponseDTO> createNewRegisterOne(
            @RequestBody RegisterOneRequestDTO registerOneRequestDTO){
        RegisterOneResponseDTO registerOneResponseDTO = registerOneService.createRegisterOne(registerOneRequestDTO);
        return new ResponseEntity<>(registerOneResponseDTO, HttpStatus.OK);
    }
}
