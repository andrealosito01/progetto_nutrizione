package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Piano;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.services.PianoService;
import com.example.pswproject.services.UtenteService;
import com.example.pswproject.support.authentication.Utils;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.PianoAlreadyExistsException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/piano")
public class PianoController {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private PianoService pianoService;

    @PreAuthorize("hasAuthority('paziente')")
    @GetMapping
    public ResponseEntity<Piano> getPiano(){
        try {
            Piano piano = pianoService.getPiano(Utils.getUsername());
            return ResponseEntity.ok(piano);
        } catch (ResourceNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Piano non trovato!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @PostMapping("/{idUtente}")
    public ResponseEntity<Piano> addPiano(@PathVariable Long idUtente, @RequestBody Piano piano){
        try{
            Utente utente = utenteService.getUtenteById(idUtente);
            Piano pianoAggiunto = pianoService.aggiungi(utente.getUsername(),piano);
            return ResponseEntity.ok(pianoAggiunto);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!",e);
        }catch(PianoAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"L'utente ha già un piano",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Piano non supportato!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @PutMapping("/{idUtente}")
    public ResponseEntity<Piano> updatePiano(@PathVariable Long idUtente, @RequestBody Piano piano){
        try{
            Utente utente = utenteService.getUtenteById(idUtente);
            Piano pianoAggiornato = pianoService.modifica(utente.getUsername(), piano);
            return ResponseEntity.ok(pianoAggiornato);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Piano non trovato!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Piano non supportato!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @DeleteMapping("/{idUtente}")
    public ResponseEntity<Piano> deletePiano(@PathVariable Long idUtente){
        try{
            Utente utente = utenteService.getUtenteById(idUtente);
            Piano pianoRimosso = pianoService.rimuovi(utente.getUsername());
            return ResponseEntity.ok(pianoRimosso);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Piano non trovato!",e);
        }
    }

}
