package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Passi;
import com.example.pswproject.services.PassiService;
import com.example.pswproject.support.authentication.Utils;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.PassiAlreadyInsertedException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("/passi")
public class PassiController {

    @Autowired
    private PassiService passiService;

    @PreAuthorize("hasAuthority('paziente')")
    @GetMapping
    public ResponseEntity<Collection<Passi>> getPassi(){
        try{
            Collection<Passi> passi = passiService.getPassi(Utils.getUsername());
            return ResponseEntity.ok(passi);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Utente non trovato!",e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @PostMapping
    public ResponseEntity<Passi> addPassi(@RequestBody Passi passi){
        try{
            Passi passiAggiunti = passiService.aggiungi(Utils.getUsername(),passi);
            return ResponseEntity.ok(passiAggiunti);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Utente non trovato!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Passi non supportati!",e);
        }catch(PassiAlreadyInsertedException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Passi già inseriti per questa data!",e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Passi> deletePassi(@PathVariable Long id){
        try{
            Passi passiRimossi = passiService.rimuovi(Utils.getUsername(),id);
            return ResponseEntity.ok(passiRimossi);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Passi non trovati!",e);
        }
    }

}
