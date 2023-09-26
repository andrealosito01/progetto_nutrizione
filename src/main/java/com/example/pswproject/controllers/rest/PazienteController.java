package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Paziente;
import com.example.pswproject.services.PazienteService;
import com.example.pswproject.support.exceptions.EmailAlreadyExistsException;
import com.example.pswproject.support.exceptions.UserNotFoundException;
import com.example.pswproject.support.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("/paziente")
public class PazienteController {

    @Autowired
    PazienteService pazienteService;

    @PreAuthorize("hasAuthority('nutrizionista')")
    @GetMapping
    public ResponseEntity<Collection<Paziente>> getPazienti(){
        return ResponseEntity.ok(pazienteService.getPazienti());
    }

    @PreAuthorize("(hasAuthority('paziente') and #username == T(com.example.pswproject.support.authentication.Utils).getUsername()) or hasAuthority('nutrizionista')")
    @GetMapping("/{username}")
    public ResponseEntity<Paziente> getPaziente(@PathVariable String username){
        try {
            return ResponseEntity.ok(pazienteService.getPaziente(username));
        }catch(UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!", e);
        }
    }

    @PostMapping
    public ResponseEntity<Paziente> addPaziente(@RequestBody Paziente c){
        try{
            Paziente paziente = pazienteService.registra(c);
            return ResponseEntity.ok(paziente);
        }catch(UsernameAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username già usato!", e);
        }catch(EmailAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email già usata!", e);
        }
    }

    @PreAuthorize("hasAuthority('paziente') and #username == T(com.example.pswproject.support.authentication.Utils).getUsername()")
    @PutMapping("/{username}")
    public ResponseEntity<Paziente> updatePaziente(@PathVariable String username, @RequestBody Paziente p){
        try{
            Paziente paziente = pazienteService.aggiorna(username,p);
            return ResponseEntity.ok(paziente);
        }catch(UsernameAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username già usato!", e);
        }catch(EmailAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email già usata!", e);
        }
    }

    @PreAuthorize("hasAuthority('paziente') and #username == T(com.example.pswproject.support.authentication.Utils).getUsername()")
    @DeleteMapping("/{username}")
    public ResponseEntity<Paziente> deletePaziente(@PathVariable String username){
        try{
            Paziente paziente = pazienteService.rimuovi(username);
            return ResponseEntity.ok(paziente);
        }catch(UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!", e);
        }
    }

}
