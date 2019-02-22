package com.iadtec.hackathon.Controller;

import com.iadtec.hackathon.DTO.*;
import com.iadtec.hackathon.Service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/pais")
@RestController
@CrossOrigin
public class PaisController {

    @Autowired
    private PaisService service;

    @GetMapping("{id}")
    public ResponseEntity<Response<PaisResponse>> getById(@PathVariable("id") Long id ) {
        Response<PaisResponse> response = new Response<>();
        response.setData( service.getById( id ));
        return ResponseEntity.ok( response );
    }

    @GetMapping
    public ResponseEntity<Response<List<PaisResponse>>> getAll() {
        Response<List<PaisResponse>> response = new Response<>();
        response.setData( service.getAll() );
        return ResponseEntity.ok( response );
    }

    @PostMapping
    public ResponseEntity<Response<PaisResponse>> create(@Valid @RequestBody CriarPaisDTO create, BindingResult result) {
        Response<PaisResponse> response = new Response<>();
        if (result.hasErrors()) {
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body( response );
        }
        response.setData( service.create( create ) );
        return ResponseEntity.ok( response );
    }

    @PutMapping("{id}")
    public ResponseEntity<Response<PaisResponse>> update(@PathVariable("id") Long id,
                                                         @Valid @RequestBody UpdatePais update,
                                                         BindingResult result) {
        Response<PaisResponse> response = new Response<>();
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
