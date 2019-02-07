package com.iadtec.hackathon.Service;

import com.iadtec.hackathon.DTO.RegisterOneRequestDTO;
import com.iadtec.hackathon.DTO.RegisterOneResponseDTO;
import com.iadtec.hackathon.Entity.RegisterOne;
import com.iadtec.hackathon.Repository.RegisterOneRepository;
import com.iadtec.hackathon.Utils.PathParamsPageable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

//    public List<RegisterOneResponseDTO> getAllRegisterOne() {
//        Iterable<RegisterOne> allRegisterOne = registerOneRepository.findAll();
//        List<RegisterOneResponseDTO> allRegisterOneResponseDTO = new ArrayList<>();
//        allRegisterOne.forEach(registerOne -> {
//            RegisterOneResponseDTO registerOneResponseDTO = modelMapper.map(registerOne, RegisterOneResponseDTO.class);
//            allRegisterOneResponseDTO.add(registerOneResponseDTO);
//        });
//        return allRegisterOneResponseDTO;
//    }

    public List<RegisterOneResponseDTO> getAllRegisterOne(PathParamsPageable pathParamsPageable) {
        PageRequest pageRequest = this.createPageRequest(pathParamsPageable);
        Page<RegisterOne> page = registerOneRepository.findAll(pageRequest);
        List<RegisterOneResponseDTO> allRegisterOneResponseDTO = this.convertPageToListRegisterOne(page);
        return allRegisterOneResponseDTO;
    }

    public PathParamsPageable convertPathParamsToObject(Integer page, Integer size, Boolean ascendent,
                                                        String fieldOrderBy){
        return new PathParamsPageable(page, size, ascendent, fieldOrderBy);
    }

    private Sort.Direction convertBooleanToDirection(Boolean ascendent){
        Sort.Direction direction = Sort.Direction.ASC;
        if(ascendent){
            direction = Sort.Direction.ASC;
        }
        return direction;
    }

    private PageRequest createPageRequest(PathParamsPageable pathParamsPageable){
        pathParamsPageable.setDefaultValuesIfNull();
        Sort.Direction direction = this.convertBooleanToDirection(pathParamsPageable.getAscendent());
        PageRequest pageRequest = PageRequest.of(pathParamsPageable.getPage(), pathParamsPageable.getSize(),
                direction, pathParamsPageable.getFieldOrderBy());
        return pageRequest;
    }

    private List<RegisterOneResponseDTO> convertPageToListRegisterOne(Page<RegisterOne> page){
        List<RegisterOneResponseDTO> allRegisterOneResponseDTO =
                page.stream().map(registerOne ->
                        modelMapper.map(registerOne, RegisterOneResponseDTO.class)).collect(Collectors.toList());
        return allRegisterOneResponseDTO;
    }



}
