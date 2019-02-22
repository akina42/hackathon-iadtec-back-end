package com.iadtec.hackathon.Service;

import com.iadtec.hackathon.Configuration.Utils;
import com.iadtec.hackathon.DTO.CriarEstadoDTO;
import com.iadtec.hackathon.DTO.EstadoResponse;
import com.iadtec.hackathon.DTO.UpdateEstado;
import com.iadtec.hackathon.Entity.Estado;
import com.iadtec.hackathon.Entity.Pais;
import com.iadtec.hackathon.ExceptionHandling.ResourceNotFoundException;
import com.iadtec.hackathon.Repository.EstadoRepository;
import com.iadtec.hackathon.Repository.PaisRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private ModelMapper modelMapper;

    public EstadoResponse getById(Long id) {
        Optional<Estado> isFound = repository.findById(id);
        isFound.orElseThrow( ResourceNotFoundException::new );
        return modelMapper.map( isFound.get(), EstadoResponse.class );
    }

    public List<EstadoResponse> getAll() {
        Optional<List<Estado>> all = Optional.ofNullable( repository.findAll() );
        all.orElseThrow( ResourceNotFoundException::new );
        return modelMapper.map( all.get(), Utils.getListType( EstadoResponse.class ));
    }

    public EstadoResponse create(CriarEstadoDTO create) {
        Optional<Pais> wasPais = paisRepository.findById(create.getPais());
        wasPais.orElseThrow( ResourceNotFoundException::new );
        Estado toSave = modelMapper.map(create, Estado.class);
        toSave.setPais(wasPais.get());
        return modelMapper.map( repository.save( toSave ), EstadoResponse.class );
    }

    public EstadoResponse update(Long id, UpdateEstado update) {
        Optional<Estado> isFound = repository.findById(id);
        isFound.orElseThrow( ResourceNotFoundException::new );
        Estado toUpdate = updateAttributes( isFound.get(), update );
        return modelMapper.map( repository.save( toUpdate ), EstadoResponse.class );
    }

    public void delete(Long id) {
        Optional<Estado> isFound = repository.findById(id);
        isFound.orElseThrow( ResourceNotFoundException::new );
        repository.delete(isFound.get());
    }

    private Estado updateAttributes(final Estado old, final UpdateEstado novo){
        BeanUtils.copyProperties( novo, old, "id" );
        return old;
    }
}
