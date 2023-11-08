package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Scheda;
import com.example.pswproject.services.SchedaService;
import com.example.pswproject.support.authentication.Utils;
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
@RequestMapping("/scheda")
public class SchedaController {

    @Autowired
    private SchedaService schedaService;

    @PreAuthorize("hasAuthority('paziente')")
    @GetMapping
    public ResponseEntity<Collection<Scheda>> getScheda(){
        try{
            Collection<Scheda> schede = schedaService.getSchede(Utils.getUsername());
            return ResponseEntity.ok(schede);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Utente non trovato!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @PostMapping("/{username}")
    public ResponseEntity<Scheda> addScheda(@PathVariable String username, @RequestBody Scheda scheda){
        try{
            Scheda schedaAggiunta = schedaService.aggiungi(username,scheda);
            return ResponseEntity.ok(schedaAggiunta);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Utente non trovato!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Scheda non supportata!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @PutMapping("/{username}/{id}")
    public ResponseEntity<Scheda> updateScheda(@PathVariable String username, @PathVariable Long id, @RequestBody Scheda scheda){
        try{
            Scheda schedaAggiornata = schedaService.modifica(username,id, scheda);
            return ResponseEntity.ok(schedaAggiornata);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Scheda non trovata!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Scheda non supportata!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @DeleteMapping("/{username}/{id}")
    public ResponseEntity<Scheda> deleteScheda(@PathVariable String username, @PathVariable Long id){
        try{
            Scheda schedaRimossa = schedaService.rimuovi(username,id);
            return ResponseEntity.ok(schedaRimossa);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Scheda non trovata!",e);
        }
    }

}
