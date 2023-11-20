package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Esercizio;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.services.EsercizioService;
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
@RequestMapping("/esercizio")
public class EsercizioController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private EsercizioService esercizioService;

    @PreAuthorize("hasAuthority('paziente')")
    @GetMapping("/{idScheda}")
    public ResponseEntity<Collection<Esercizio>> getEsercizi(@PathVariable Long idScheda){  //NON USATO
        try{
            Collection<Esercizio> esercizi = esercizioService.getEsercizi(Utils.getUsername(),idScheda);
            return ResponseEntity.ok(esercizi);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Diario non trovato!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @PostMapping("/{idUtente}/{idScheda}")
    public ResponseEntity<Esercizio> addEsercizio(@PathVariable Long idUtente, @PathVariable Long idScheda, @RequestBody Esercizio esercizio){
        try{
            Utente utente = utenteService.getUtenteById(idUtente);
            Esercizio esercizioAggiunto = esercizioService.aggiungi(utente.getUsername(),idScheda,esercizio);
            return ResponseEntity.ok(esercizioAggiunto);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Diario non trovato!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Esercizio non supportato!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @PutMapping("/{idUtente}/{idScheda}/{id}")
    public ResponseEntity<Esercizio> updateEsercizio(@PathVariable Long idUtente, @PathVariable Long idScheda, @PathVariable Long id, @RequestBody Esercizio esercizio){
        try{
            Utente utente = utenteService.getUtenteById(idUtente);
            Esercizio esercizioAggiornato = esercizioService.modifica(utente.getUsername(), idScheda, id, esercizio);
            return ResponseEntity.ok(esercizioAggiornato);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Esercizio non trovato!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @DeleteMapping("/{idUtente}/{idScheda}/{id}")
    public ResponseEntity<Esercizio> deleteEsercizio(@PathVariable Long idUtente,@PathVariable Long idScheda, @PathVariable Long id){
        try{
            Utente utente = utenteService.getUtenteById(idUtente);
            Esercizio esercizioRimosso = esercizioService.rimuovi(utente.getUsername(), idScheda, id);
            return ResponseEntity.ok(esercizioRimosso);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Esercizio non trovato!",e);
        }
    }

}
