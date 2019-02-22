package com.iadtec.hackathon.Service;

import com.iadtec.hackathon.Configuration.Utils;
import com.iadtec.hackathon.DTO.CriarPaisDTO;
import com.iadtec.hackathon.DTO.PaisResponse;
import com.iadtec.hackathon.DTO.UpdatePais;
import com.iadtec.hackathon.Entity.Pais;
import com.iadtec.hackathon.ExceptionHandling.ResourceNotFoundException;
import com.iadtec.hackathon.Repository.PaisRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService {

    @Autowired
    private PaisRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public PaisResponse getById(Long id) {
        Optional<Pais> isFound = repository.findById(id);
        isFound.orElseThrow( ResourceNotFoundException::new );
        return modelMapper.map( isFound.get(), PaisResponse.class );
    }

    public List<PaisResponse> getAll() {
        Optional<List<Pais>> all = Optional.ofNullable( repository.findAll() );
        all.orElseThrow( ResourceNotFoundException::new );
        return modelMapper.map( all.get(), Utils.getListType( PaisResponse.class ));
    }

    public PaisResponse create(CriarPaisDTO create) {
        Pais toSave = modelMapper.map(create, Pais.class);
        return modelMapper.map( repository.save( toSave ), PaisResponse.class );
    }

    public PaisResponse update(Long id, UpdatePais update) {
        Optional<Pais> isFound = repository.findById( id );
        isFound.orElseThrow( ResourceNotFoundException::new );
        Pais toUpdate = updateAttributes( isFound.get(), update );
        return modelMapper.map( repository.save( toUpdate ), PaisResponse.class );
    }

    public void delete(Long id) {
        Optional<Pais> isFound = repository.findById(id);
        isFound.orElseThrow( ResourceNotFoundException::new );
        repository.delete(isFound.get());
    }

    private Pais updateAttributes(final Pais old, final UpdatePais novo){
        BeanUtils.copyProperties( novo, old, "id" );
        return old;
    }
}
