package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Peso;
import com.example.pswproject.services.PesoService;
import com.example.pswproject.support.authentication.Utils;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import com.example.pswproject.support.exceptions.WeightAlreadyInsertedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("/peso")
public class PesoController {

    @Autowired
    private PesoService pesoService;

    @PreAuthorize("hasAuthority('paziente')")
    @GetMapping
    public ResponseEntity<Collection<Peso>> getPesi(){  // NON USATO
        try {
            Collection<Peso> pesi = pesoService.getPesi(Utils.getUsername());
            return ResponseEntity.ok(pesi);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!", e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @PostMapping
    public ResponseEntity<Peso> addPeso(@RequestBody Peso peso){
        try {
            Peso pesoAggiunto = pesoService.aggiungi(peso, Utils.getUsername());
            return ResponseEntity.ok(pesoAggiunto);
        } catch (WeightAlreadyInsertedException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Peso di oggi già inserito!", e);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!", e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Peso> deletePeso(@PathVariable Long id){
        try {
            Peso pesoRimosso = pesoService.rimuovi(id, Utils.getUsername());
            return ResponseEntity.ok(pesoRimosso);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Peso non trovato!", e);
        }
    }

}
