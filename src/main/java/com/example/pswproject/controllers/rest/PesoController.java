package com.example.pswproject.controllers.rest;

import com.example.pswproject.entities.Peso;
import com.example.pswproject.services.PazienteService;
import com.example.pswproject.services.PesoService;
import com.example.pswproject.support.authentication.Utils;
import com.example.pswproject.support.exceptions.UserNotFoundException;
import com.example.pswproject.support.exceptions.WeightAlreadyInsertedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/peso")
public class PesoController {

    @Autowired
    private PesoService pesoService;

    @PreAuthorize("(hasAuthority('paziente') and #username == T(com.example.pswproject.support.authentication.Utils).getUsername()) or hasAuthority('nutrizionista')")
    @GetMapping
    public ResponseEntity<List<Peso>> getPesi(@RequestParam String username){
        try {
            List<Peso> pesi = pesoService.getPesi(username);
            return ResponseEntity.ok(pesi);
        }catch(UserNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!", e);
        }
    }

    @PreAuthorize("hasAuthority('paziente')")
    @PostMapping
    public ResponseEntity<Peso> addPeso(@RequestParam("giorno") String giorno, @RequestParam("peso") BigDecimal peso){
        try {
            Peso pesoAggiunto = pesoService.addPeso(giorno, peso, Utils.getUsername());
            return ResponseEntity.ok(pesoAggiunto);
        }catch(ParseException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato data non valida!", e);
        } catch (WeightAlreadyInsertedException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Peso di oggi già inserito!", e);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utente non trovato!", e);
        }
    }

}
