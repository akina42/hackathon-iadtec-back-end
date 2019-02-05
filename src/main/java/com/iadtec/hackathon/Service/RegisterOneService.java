package com.iadtec.hackathon.Service;

import com.iadtec.hackathon.DTO.RegisterOneDTO;
import com.iadtec.hackathon.Entity.RegisterOne;
import com.iadtec.hackathon.Repository.RegisterOneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterOneService {

    @Autowired
    private RegisterOneRepository registerOneRepository;

    @Autowired
    private ModelMapper modelMapper;

    public RegisterOneService() {
    }

    public Optional<RegisterOneDTO> getRegisterOne(Long id){
        Optional<RegisterOne> registerOneOptional = registerOneRepository.findById(id);
        registerOneOptional.map( registerOne -> {
            RegisterOneDTO registerOneDTO = modelMapper.map(registerOne, RegisterOneDTO.class);
            return Optional.of(registerOneDTO);
        }).orElse(Optional.empty());
        return Optional.empty();
    }
}
