package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Alimento;
import com.example.pswproject.services.AlimentoService;
import com.example.pswproject.support.authentication.Utils;
import com.example.pswproject.support.exceptions.AlimentoAlreadyExistsException;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("/alimento")
public class AlimentoController {

    @Autowired
    private AlimentoService alimentoService;

    private final String NUTRIZIONISTA = "mattia";

    @PreAuthorize("hasAuthority('paziente')")
    @GetMapping
    public ResponseEntity<Collection<Alimento>> getAlimenti(){
        try{
            Collection<Alimento> alimenti = alimentoService.getAlimenti(Utils.getUsername());
            return ResponseEntity.ok(alimenti);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Utente non trovato!",e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @GetMapping("/nutrizionista")
    public ResponseEntity<Collection<Alimento>> getAlimentiNutrizionista(){
        try{
            Collection<Alimento> alimenti = alimentoService.getAlimenti(NUTRIZIONISTA);
            return ResponseEntity.ok(alimenti);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Utente non trovato!",e);
        }
    }


    @PreAuthorize("hasAuthority('paziente')")
    @PostMapping
    public ResponseEntity<Alimento> addAlimento(@RequestBody Alimento alimento){
        try{
            Alimento alimentoAggiunto = alimentoService.aggiungi(Utils.getUsername(),alimento);
            return ResponseEntity.ok(alimento);
        }catch(ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Utente non trovato!",e);
        }catch(AlimentoAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Già presente un alimento con questo nome!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Alimento non supportato!",e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @PutMapping("/{id}")
    public ResponseEntity<Alimento> updateAlimento(@PathVariable long id, @RequestBody Alimento alimento){
        try{
            Alimento alimentoAggiornato = alimentoService.modifica(id,Utils.getUsername(),alimento);
            return ResponseEntity.ok(alimentoAggiornato);
        }catch(ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Alimento non trovato!",e);
        }catch(AlimentoAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Già presente un altro alimento con questo nome!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Alimento non supportato!",e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Alimento> deleteAlimento(@PathVariable long id){
        try{
            Alimento alimentoRimosso = alimentoService.rimuovi(id,Utils.getUsername());
            return ResponseEntity.ok(alimentoRimosso);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Alimento non trovato!",e);
        }
    }

}
