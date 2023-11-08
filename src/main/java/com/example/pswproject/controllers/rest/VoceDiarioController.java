package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.VoceDiario;
import com.example.pswproject.services.VoceDiarioService;
import com.example.pswproject.support.authentication.Utils;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
@RequestMapping("/voceDiario")
public class VoceDiarioController {

    @Autowired
    private VoceDiarioService voceDiarioService;

    @GetMapping("/{idDiario}")
    public ResponseEntity<Collection<VoceDiario>> getVociDiario(@PathVariable long idDiario){   // NON USATO
        try{
            Collection<VoceDiario> vociDiario = voceDiarioService.getVociDiario(Utils.getUsername(),idDiario);
            return ResponseEntity.ok(vociDiario);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Diario non trovato!",e);
        }
    }

    @PostMapping("/{idDiario}")
    public ResponseEntity<VoceDiario> addVoceDiario(@PathVariable long idDiario, @RequestBody VoceDiario voceDiario){
        try{
            VoceDiario voceDiarioAggiunta = voceDiarioService.aggiungi(Utils.getUsername(),idDiario,voceDiario);
            return ResponseEntity.ok(voceDiarioAggiunta);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Diario non trovato!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Voce diario non supportata!",e);
        }
    }

    @PutMapping("/{idDiario}/{id}")
    public ResponseEntity<VoceDiario> updateVoceDiario(@PathVariable long idDiario, @PathVariable long id, @RequestBody VoceDiario voceDiario){
        try{
            VoceDiario voceDiarioAggiornata = voceDiarioService.modifica(Utils.getUsername(),idDiario,id,voceDiario);
            return ResponseEntity.ok(voceDiarioAggiornata);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Voce diario non trovata!",e);
        }catch(BadRequestException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Voce diario non supportata!",e);
        }
    }

    @DeleteMapping("/{idDiario}/{id}")
    public ResponseEntity<VoceDiario> deleteVoceDiario(@PathVariable long idDiario, @PathVariable long id){
        try{
            VoceDiario voceDiarioRimossa = voceDiarioService.rimuovi(Utils.getUsername(),idDiario,id);
            return ResponseEntity.ok(voceDiarioRimossa);
        }catch(ResourceNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Voce diario non trovata!",e);
        }
    }

}
