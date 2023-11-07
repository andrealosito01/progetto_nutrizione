package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Piano;
import com.example.pswproject.services.PianoService;
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

    @PreAuthorize("hasAuthority('paziente')")
    @PostMapping
    public ResponseEntity<Piano> addPiano(@RequestBody Piano piano){
        try{
            Piano pianoAggiunto = pianoService.aggiungi(Utils.getUsername(),piano);
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
    @PostMapping("/{username}")
    public ResponseEntity<Piano> addPiano(@PathVariable String username, @RequestBody Piano piano){
        try{
            Piano pianoAggiunto = pianoService.aggiungi(username,piano);
            return ResponseEntity.ok(pianoAggiunto);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!",e);
        }catch(PianoAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"L'utente ha già un piano",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Piano non supportato!",e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @PutMapping
    public ResponseEntity<Piano> updatePiano(@RequestBody Piano piano){
        try{
            Piano pianoAggiornato = pianoService.modifica(Utils.getUsername(), piano);
            return ResponseEntity.ok(pianoAggiornato);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Piano non trovato!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Piano non supportato!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @PutMapping("/{username}")
    public ResponseEntity<Piano> updatePiano(@PathVariable String username, @RequestBody Piano piano){
        try{
            Piano pianoAggiornato = pianoService.modifica(username, piano);
            return ResponseEntity.ok(pianoAggiornato);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Piano non trovato!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Piano non supportato!",e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @DeleteMapping
    public ResponseEntity<Piano> deletePiano(){
        try{
            Piano pianoRimosso = pianoService.rimuovi(Utils.getUsername());
            return ResponseEntity.ok(pianoRimosso);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Piano non trovato!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @DeleteMapping("/{username}")
    public ResponseEntity<Piano> deletePiano(@PathVariable String username){
        try{
            Piano pianoRimosso = pianoService.rimuovi(username);
            return ResponseEntity.ok(pianoRimosso);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Piano non trovato!",e);
        }
    }

}
