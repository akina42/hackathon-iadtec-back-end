package com.iadtec.hackathon.Controller;

import com.iadtec.hackathon.DTO.CriarEstadoDTO;
import com.iadtec.hackathon.DTO.EstadoResponse;
import com.iadtec.hackathon.DTO.Response;
import com.iadtec.hackathon.DTO.UpdateEstado;
import com.iadtec.hackathon.Service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/estado")
@RestController
@CrossOrigin
public class EstadoController {

    @Autowired
    private EstadoService service;

    @GetMapping("{id}")
    public ResponseEntity<Response<EstadoResponse>> getById(@PathVariable("id") Long id ) {
        Response<EstadoResponse> response = new Response<>();
        response.setData( service.getById( id ));
        return ResponseEntity.ok( response );
    }

    @GetMapping
    public ResponseEntity<Response<List<EstadoResponse>>> getAll() {
        Response<List<EstadoResponse>> response = new Response<>();
        response.setData( service.getAll() );
        return ResponseEntity.ok( response );
    }

    @PostMapping
    public ResponseEntity<Response<EstadoResponse>> create(@Valid @RequestBody CriarEstadoDTO create,
                                                           BindingResult result) {

        Response<EstadoResponse> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body( response );
        }
        response.setData( service.create( create ) );
        return ResponseEntity.ok( response );
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<EstadoResponse>> update(@PathVariable("id") Long id,
                                                           @Valid @RequestBody UpdateEstado update,
                                                           BindingResult result) {

        Response<EstadoResponse> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body( response );
        }
        response.setData( service.update( id, update ) );
        return ResponseEntity.ok( response );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Response<Void>> delete(@PathVariable("id") Long id) {
        Response<Void> response = new Response<>();
        service.delete( id );
        return ResponseEntity.ok( response );
    }
}
