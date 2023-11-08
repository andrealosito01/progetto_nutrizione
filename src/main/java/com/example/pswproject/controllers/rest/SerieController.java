package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Serie;
import com.example.pswproject.services.SerieService;
import com.example.pswproject.support.authentication.Utils;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import com.example.pswproject.support.exceptions.SerieAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.ws.rs.Path;
import java.util.Collection;

@RestController
@RequestMapping("/serie")
public class SerieController {

    @Autowired
    private SerieService serieService;

    @PreAuthorize("hasAuthority('paziente')")
    @GetMapping("/{idScheda}/{idEsercizio}")
    public ResponseEntity<Collection<Serie>> getSerie(@PathVariable Long idScheda, @PathVariable Long idEsercizio){
        try{
            Collection<Serie> serie = serieService.getSerie(Utils.getUsername(),idScheda,idEsercizio);
            return ResponseEntity.ok(serie);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Esercizio non trovato!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @PostMapping("/{username}/{idScheda}/{idEsercizio}")
    public ResponseEntity<Serie> addSerie(@PathVariable String username, @PathVariable Long idScheda, @PathVariable Long idEsercizio, @RequestBody Serie serie){
        try{
            Serie serieAggiunta = serieService.aggiungi(username, idScheda, idEsercizio, serie);
            return ResponseEntity.ok(serieAggiunta);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Esercizio non trvoato!",e);
        }catch(SerieAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Già presente serie con stesso numero!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Serie non supportata!",e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @PutMapping("/{idScheda}/{idEsercizio}/{id}")
    public ResponseEntity<Serie> updateSerie(@PathVariable Long idScheda, @PathVariable Long idEsercizio, @PathVariable Long id, @RequestBody Serie serie){
        try{
            Serie serieAggiornata = serieService.modifica(Utils.getUsername(),idScheda,idEsercizio,id,serie);
            return ResponseEntity.ok(serieAggiornata);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Serie non trovata!",e);
        }catch(SerieAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Già presente serie con stesso numero!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Serie non supportata!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @PutMapping("/{username}/{idScheda}/{idEsercizio}/{id}")
    public ResponseEntity<Serie> updateSerie(@PathVariable String username, @PathVariable Long idScheda, @PathVariable Long idEsercizio, @PathVariable Long id, @RequestBody Serie serie){
        try{
            Serie serieAggiornata = serieService.modifica(username,idScheda,idEsercizio,id,serie);
            return ResponseEntity.ok(serieAggiornata);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Serie non trovata!",e);
        }catch(SerieAlreadyExistsException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Già presente serie con stesso numero!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Serie non supportata!",e);
        }
    }

    @PreAuthorize("hasAuthority('nutrizionista')")
    @DeleteMapping("/{username}/{idScheda}/{idEsercizio}/{id}")
    public ResponseEntity<Serie> deleteSerie(@PathVariable String username, @PathVariable Long idScheda, @PathVariable Long idEsercizio, @PathVariable Long id){
        try{
            Serie serieRimossa = serieService.rimuovi(username,idScheda,idEsercizio,id);
            return ResponseEntity.ok(serieRimossa);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Serie non trovata!",e);
        }
    }

}
