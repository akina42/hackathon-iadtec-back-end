package com.iadtec.hackathon.Service;

import com.iadtec.hackathon.DTO.RegisterOneRequestDTO;
import com.iadtec.hackathon.DTO.RegisterOneResponseDTO;
import com.iadtec.hackathon.Entity.RegisterOne;
import com.iadtec.hackathon.Repository.RegisterOneRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterOneService {

    @Autowired
    private RegisterOneRepository registerOneRepository;

    @Autowired
    private ModelMapper modelMapper;

    public RegisterOneService() {
    }

    public Optional<RegisterOneResponseDTO> getRegisterOne(Long id){
        Optional<RegisterOne> registerOne = registerOneRepository.findById(id);
        if(registerOne.isPresent()){
            RegisterOneResponseDTO registerOneResponseDTO = modelMapper.map(registerOne.get(),
                    RegisterOneResponseDTO.class);
            return Optional.of(registerOneResponseDTO);
        }
        return Optional.empty();
    }

    public RegisterOneResponseDTO createRegisterOne(RegisterOneRequestDTO registerOneRequestDTO){
        RegisterOne newRegisterOne = modelMapper.map(registerOneRequestDTO, RegisterOne.class);
        RegisterOne registerOneSaved = registerOneRepository.save(newRegisterOne);
        RegisterOneResponseDTO registerOneResponseDTO = modelMapper.map(registerOneSaved, RegisterOneResponseDTO.class);
        return registerOneResponseDTO;
    }

    public List<RegisterOneResponseDTO> getAllRegisterOne() {
        Iterable<RegisterOne> allRegisterOne = registerOneRepository.findAll();
        List<RegisterOneResponseDTO> allRegisterOneResponseDTO = new ArrayList<>();
        allRegisterOne.forEach(registerOne -> {
            RegisterOneResponseDTO registerOneResponseDTO = modelMapper.map(registerOne, RegisterOneResponseDTO.class);
            allRegisterOneResponseDTO.add(registerOneResponseDTO);
        });
        return allRegisterOneResponseDTO;
    }
}
