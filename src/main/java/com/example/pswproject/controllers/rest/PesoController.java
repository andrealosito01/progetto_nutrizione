package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Peso;
import com.example.pswproject.services.PesoService;
import com.example.pswproject.support.authentication.Utils;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import com.example.pswproject.support.exceptions.WeightAlreadyInsertedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/peso")
public class PesoController {

    @Autowired
    private PesoService pesoService;

    @PreAuthorize("hasAuthority('paziente')")
    @GetMapping
    public ResponseEntity<Page<Peso>> getPaginePesi(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        try {
            Page<Peso> pesi = pesoService.getPaginePesiOrdinatiPerData(Utils.getUsername(),page,size);
            return ResponseEntity.ok(pesi);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pesi non trovati!", e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @PostMapping
    public ResponseEntity<Peso> addPeso(@RequestBody Peso peso){
        try {
            Peso pesoAggiunto = pesoService.aggiungi(peso, Utils.getUsername());
            return ResponseEntity.ok(pesoAggiunto);
        }catch(WeightAlreadyInsertedException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Peso già inserito per questa data!", e);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!", e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Peso non supportato!",e);
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
