package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Utente;
import com.example.pswproject.services.UtenteService;
import com.example.pswproject.support.authentication.Utils;
import com.example.pswproject.support.exceptions.EmailAlreadyExistsException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import com.example.pswproject.support.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    UtenteService utenteService;

    @PreAuthorize("hasAuthority('nutrizionista')")
    @GetMapping
    public ResponseEntity<Collection<Utente>> getUtenti(){
        return ResponseEntity.ok(utenteService.getUtenti());
    }

    @PreAuthorize("(hasAuthority('paziente') and #username == T(com.example.pswproject.support.authentication.Utils).getUsername()) or hasAuthority('nutrizionista')")
    @GetMapping("/{username}")
    public ResponseEntity<Utente> getUtente(@PathVariable String username){
        try {
            return ResponseEntity.ok(utenteService.getUtente(username));
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!", e);
        }
    }

    @PostMapping
    public ResponseEntity<Utente> addUtente(@RequestBody Utente u){
        try{
            Utente utente = utenteService.aggiungi(u);
            return ResponseEntity.ok(utente);
        }catch(UsernameAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username già usato!", e);
        }catch(EmailAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email già usata!", e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @PutMapping
    public ResponseEntity<Utente> updateUtente(@RequestBody Utente u){
        try {
            Utente utente = utenteService.modifica(Utils.getUsername(), u);
            return ResponseEntity.ok(utente);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!",e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @DeleteMapping
    public ResponseEntity<Utente> deleteUtente(){
        try{
            Utente utente = utenteService.rimuovi(Utils.getUsername());
            return ResponseEntity.ok(utente);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!", e);
        }
    }

}
