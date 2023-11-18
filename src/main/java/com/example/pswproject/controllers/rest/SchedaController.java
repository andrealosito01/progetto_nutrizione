package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Scheda;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.services.SchedaService;
import com.example.pswproject.services.UtenteService;
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
    private UtenteService utenteService;

    @Autowired
    private SchedaService schedaService;

    @PreAuthorize("hasAuthority('paziente')")
    @GetMapping
    public ResponseEntity<Collection<Scheda>> getSchede(){
        try{
            Collection<Scheda> schede = schedaService.getSchede(Utils.getUsername());
            return ResponseEntity.ok(schede);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Utente non trovato!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @PostMapping("/{idUtente}")
    public ResponseEntity<Scheda> addScheda(@PathVariable Long idUtente, @RequestBody Scheda scheda){
        try{
            Utente utente = utenteService.getUtenteById(idUtente);
            Scheda schedaAggiunta = schedaService.aggiungi(utente.getUsername(),scheda);
            return ResponseEntity.ok(schedaAggiunta);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Utente non trovato!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Scheda non supportata!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @PutMapping("/{idUtente}/{id}")
    public ResponseEntity<Scheda> updateScheda(@PathVariable Long idUtente, @PathVariable Long id, @RequestBody Scheda scheda){
        try{
            Utente utente = utenteService.getUtenteById(idUtente);
            Scheda schedaAggiornata = schedaService.modifica(utente.getUsername(),id, scheda);
            return ResponseEntity.ok(schedaAggiornata);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Scheda non trovata!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Scheda non supportata!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @DeleteMapping("/{idUtente}/{id}")
    public ResponseEntity<Scheda> deleteScheda(@PathVariable Long idUtente, @PathVariable Long id){
        try{
            Utente utente = utenteService.getUtenteById(idUtente);
            Scheda schedaRimossa = schedaService.rimuovi(utente.getUsername(),id);
            return ResponseEntity.ok(schedaRimossa);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Scheda non trovata!",e);
        }
    }

}
