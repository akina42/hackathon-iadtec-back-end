package com.iadtec.hackathon.Service;

import com.iadtec.hackathon.DTO.ClienteResponseDTO;
import com.iadtec.hackathon.DTO.CriarClienteDTO;
import com.iadtec.hackathon.DTO.EditarCliente;
import com.iadtec.hackathon.Entity.Cliente;
import com.iadtec.hackathon.Entity.EnumSituacao;
import com.iadtec.hackathon.Entity.Estado;
import com.iadtec.hackathon.ExceptionHandling.ResourceNotFoundException;
import com.iadtec.hackathon.Repository.ClienteRepository;
import com.iadtec.hackathon.Repository.EstadoRepository;
import com.iadtec.hackathon.Utils.PathParamsPageable;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Optional<ClienteResponseDTO> getCliente(final Long id) throws ResourceNotFoundException{
        Optional<Cliente> isFound = clienteRepository.findById( id );
        isFound.orElseThrow( ResourceNotFoundException::new );
        final ClienteResponseDTO result = modelMapper.map(isFound.get(), ClienteResponseDTO.class);
        return Optional.of( result );
    }

    public List<ClienteResponseDTO> getAllClienteExport() {
        Iterable<Cliente> allRegisterOne = clienteRepository.findAll();

        List<ClienteResponseDTO> allRegisterOneResponseDTO = new ArrayList<>();

        allRegisterOne.forEach(registerOne -> {
            ClienteResponseDTO registerOneResponseDTO = modelMapper.map(registerOne, ClienteResponseDTO.class);
            allRegisterOneResponseDTO.add( registerOneResponseDTO );
        });

        return allRegisterOneResponseDTO;
    }

    public List<ClienteResponseDTO> getAllRegisterOne( final PathParamsPageable pathParamsPageable ) {
        PageRequest pageRequest = this.createPageRequest(pathParamsPageable);
        Page<Cliente> page = clienteRepository.findAll(pageRequest);
        List<ClienteResponseDTO> allRegisterOneResponseDTO = this.convertPageToListRegisterOne(page);
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

    private List<ClienteResponseDTO> convertPageToListRegisterOne(Page<Cliente> page){
        List<ClienteResponseDTO> allRegisterOneResponseDTO =
                page.stream().map(registerOne ->
                        modelMapper.map(registerOne, ClienteResponseDTO.class)).collect(Collectors.toList());
        return allRegisterOneResponseDTO;
    }

    @Transactional
    public ClienteResponseDTO createCliente(CriarClienteDTO criarClienteDTO){
        Cliente novo = modelMapper.map(criarClienteDTO, Cliente.class);
        novo.setSituacao( EnumSituacao.ATIVO );

        if (criarClienteDTO.getIdEstado() != null){
            Optional<Estado> wasPais = estadoRepository.findById( criarClienteDTO.getIdEstado() );
            wasPais.ifPresent(novo::setEstado);
        }
        clienteRepository.save( novo );
        return modelMapper.map( novo, ClienteResponseDTO.class);
    }

    @Transactional
    public Optional<ClienteResponseDTO> updateRegisterOne(final Long id,
                                                          final EditarCliente editarCliente ){

        Optional<Cliente> isFound = clienteRepository.findById( id );

        if( isFound.isPresent() ){
            Cliente registerOneUpdated = this.updateAttributesRegisterOne( isFound.get(), editarCliente );
            Cliente registerOneSaved = clienteRepository.save(registerOneUpdated);
            ClienteResponseDTO registerOneResponseDTO = modelMapper.map(registerOneSaved,
                    ClienteResponseDTO.class);
            return Optional.of(registerOneResponseDTO);
        }
        return Optional.empty();
    }

    private Cliente updateAttributesRegisterOne(final Cliente old, final EditarCliente novo){
        BeanUtils.copyProperties( novo, old, "id" );
        return old;
    }

    @Transactional
    public Boolean deleteRegisterOne(Long id){
        Boolean successDelete = false;
        Optional<Cliente> registerOne = clienteRepository.findById(id);
        if(registerOne.isPresent()){
            clienteRepository.delete(registerOne.get());
            successDelete = true;
        }
        return successDelete;
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return Optional.ofNullable( clienteRepository.findByCpf( cpf ));
    }

    public Optional<Object> buscarPorEmail(String email) {
        return Optional.ofNullable( clienteRepository.findByEmail( email ));
    }

//    private Function<Cliente, ClienteResponseDTO> toResult = ( in ) -> {
//        final ClienteResponseDTO result = modelMapper.map(in, ClienteResponseDTO.class);
//        result.setEstado( modelMapper.map(in.ge, ClienteResponseDTO.class); );
//        return result;
//    };
}
