package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Misura;
import com.example.pswproject.services.MisuraService;
import com.example.pswproject.support.authentication.Utils;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.MisuraAlreadyInsertedException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("/misura")
public class MisuraController {

    @Autowired
    private MisuraService misuraService;

    @PreAuthorize("hasAuthority('paziente')")
    @GetMapping
    public ResponseEntity<Collection<Misura>> getMisure(){  // NON USATO
        try {
            Collection<Misura> misure = misuraService.getMisure(Utils.getUsername());
            return ResponseEntity.ok(misure);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!", e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @PostMapping
    public ResponseEntity<Misura> addMisura(@RequestBody Misura misura){
        try {
            Misura misuraAggiunta = misuraService.aggiungi(misura, Utils.getUsername());
            return ResponseEntity.ok(misuraAggiunta);
        }catch(MisuraAlreadyInsertedException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Misura già inserita per questa data!", e);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!", e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Misura non supportata!",e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @PutMapping("/{id}")
    public ResponseEntity<Misura> updateMisura(@PathVariable Long id, @RequestBody Misura misura){
        try {
            Misura misuraAggiornata = misuraService.modifica(id,misura,Utils.getUsername());
            return ResponseEntity.ok(misuraAggiornata);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Misura non trovata!", e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Misura non supportata!",e);
        }catch(MisuraAlreadyInsertedException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Trovata altra misura con stessa data!",e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Misura> deleteMisura(@PathVariable Long id){
        try {
            Misura misuraRimossa = misuraService.rimuovi(id, Utils.getUsername());
            return ResponseEntity.ok(misuraRimossa);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Misura non trovata!", e);
        }
    }
}
