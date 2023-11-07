package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Diario;
import com.example.pswproject.services.DiarioService;
import com.example.pswproject.support.authentication.Utils;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.DiarioAlreadyExistsException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("/diario")
public class DiarioController {

    @Autowired
    private DiarioService diarioService;

    @PreAuthorize("hasAuthority('paziente')")
    @GetMapping
    public ResponseEntity<Collection<Diario>> getDiari(){
        try{
            Collection<Diario> diari = diarioService.getDiari(Utils.getUsername());
            return ResponseEntity.ok(diari);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Utente non trovato!",e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @PostMapping
    public ResponseEntity<Diario> addDiario(@RequestBody Diario diario){
        try{
            Diario diarioAggiunto = diarioService.aggiungi(Utils.getUsername(), diario);
            return ResponseEntity.ok(diarioAggiunto);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Utente non trovato!",e);
        }catch(DiarioAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Diario già inserito per questa data!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Diario non supportato!",e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @PutMapping("/{id}")
    public ResponseEntity<Diario> updateDiario(@PathVariable long id, @RequestBody Diario diario){
        try{
            Diario diarioAggiornato = diarioService.modifica(id,Utils.getUsername(),diario);
            return ResponseEntity.ok(diarioAggiornato);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Diario non trovato!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Diario non supportato!",e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Diario> deleteDiario(@PathVariable long id){
        try{
            Diario diarioRimosso = diarioService.rimuovi(id, Utils.getUsername());
            return ResponseEntity.ok(diarioRimosso);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Diario non trovato!",e);
        }
    }

}
