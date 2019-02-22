package com.iadtec.hackathon.Controller;

import com.iadtec.hackathon.DTO.CriarClienteDTO;
import com.iadtec.hackathon.DTO.ClienteResponseDTO;
import com.iadtec.hackathon.DTO.EditarCliente;
import com.iadtec.hackathon.DTO.Response;
import com.iadtec.hackathon.Entity.Cliente;
import com.iadtec.hackathon.ExceptionHandling.ResourceNotFoundException;
import com.iadtec.hackathon.Service.ClienteService;
import com.iadtec.hackathon.Utils.PathParamsPageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/cliente")
@RestController
@CrossOrigin
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getRegisterOne(@PathVariable("id") Long id)
            throws ResourceNotFoundException {
        Optional<ClienteResponseDTO> registerOneResponseOptional = clienteService.getCliente( id );
        registerOneResponseOptional.orElseThrow( ResourceNotFoundException::new );
        return new ResponseEntity<>(registerOneResponseOptional.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> getAllRegisterOne(
            @PathParam("page") Integer page,
            @PathParam("size") Integer size,
            @PathParam("ascendent") Boolean ascendent,
            @PathParam("orderBy") String fieldOrderBy){

        ResponseEntity<List<ClienteResponseDTO>> response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        PathParamsPageable pathParamsPageable = clienteService.convertPathParamsToObject(
                page, size, ascendent, fieldOrderBy);

        List<ClienteResponseDTO> allRegisterOneResponseDTO = clienteService.getAllRegisterOne(pathParamsPageable);

        if(!allRegisterOneResponseDTO.isEmpty()){
            response = new ResponseEntity<>(allRegisterOneResponseDTO, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<Response<ClienteResponseDTO>> createNewCliente(
            @Valid @RequestBody CriarClienteDTO criarCliente, BindingResult result){

        Response<ClienteResponseDTO> response = new Response<>();

        validarDadosExistentes( criarCliente, result );

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body( response );
        }

        ClienteResponseDTO clienteResponseDTO = clienteService.createCliente(criarCliente);
        response.setData( clienteResponseDTO );
        return ResponseEntity.ok( response );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<ClienteResponseDTO>> updateRegisterOne(@PathVariable("id") Long id,
                                                                @RequestBody EditarCliente registerOneRequestDTO,
                                                                BindingResult result){

        Response<ClienteResponseDTO> response = new Response<>();

        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body( response );
        }

        Optional<ClienteResponseDTO> registerOneResponse = clienteService.updateRegisterOne(id,
                registerOneRequestDTO);
        response.setData( registerOneResponse.get() );
        return ResponseEntity.ok( response );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> deleteRegisterOne(@PathVariable("id") Long id){
        ResponseEntity<ClienteResponseDTO> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Boolean successDelete = clienteService.deleteRegisterOne(id);
        if(successDelete){
            response = new ResponseEntity<>(HttpStatus.OK);
        }
        return response;
    }

    private void validarDadosExistentes(CriarClienteDTO criarClienteDTO, BindingResult result) {

        this.clienteService.buscarPorCpf( criarClienteDTO.getCpf())
                .ifPresent(func -> result.addError(new ObjectError("cliente", "CPF já existente.")));

        this.clienteService.buscarPorEmail( criarClienteDTO.getEmail())
                .ifPresent(func -> result.addError(new ObjectError("cliente", "Email já existente.")));
    }
}
