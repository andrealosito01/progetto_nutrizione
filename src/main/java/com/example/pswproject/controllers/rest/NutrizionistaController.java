package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Nutrizionista;
import com.example.pswproject.services.NutrizionistaService;
import com.example.pswproject.support.exceptions.EmailAlreadyExistsException;
import com.example.pswproject.support.exceptions.UserNotFoundException;
import com.example.pswproject.support.exceptions.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/nutrizionista")
public class NutrizionistaController {

    @Autowired
    private NutrizionistaService nutrizionistaService;
    @PreAuthorize("hasAuthority('nutrizionista')")
    @GetMapping("/{username}")
    public ResponseEntity<Nutrizionista> getNutrizionista(@PathVariable String username){
        try {
            return ResponseEntity.ok(nutrizionistaService.getNutrizionista(username));
        }catch(UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!", e);
        }
    }

    @PostMapping
    public ResponseEntity<Nutrizionista> addNutrizionista(@RequestBody Nutrizionista n){
        try{
            Nutrizionista nutrizionista = nutrizionistaService.registra(n);
            return ResponseEntity.ok(nutrizionista);
        }catch(UsernameAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username già usato!", e);
        }catch(EmailAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email già usata!", e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista') and #username == T(com.example.pswproject.support.authentication.Utils).getUsername()")
    @PutMapping("/{username}")
    public ResponseEntity<Nutrizionista> updateNutrizionista(@PathVariable String username, @RequestBody Nutrizionista n){
        try{
            Nutrizionista nutrizionista = nutrizionistaService.aggiorna(username,n);
            return ResponseEntity.ok(nutrizionista);
        }catch(UsernameAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username già usato!", e);
        }catch(EmailAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email già usata!", e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista') and #username == T(com.example.pswproject.support.authentication.Utils).getUsername()")
    @DeleteMapping("/{username}")
    public ResponseEntity<Nutrizionista> deleteNutrizionista(@PathVariable String username){
        try{
            Nutrizionista nutrizionista = nutrizionistaService.rimuovi(username);
            return ResponseEntity.ok(nutrizionista);
        }catch(UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!", e);
        }
    }

}
