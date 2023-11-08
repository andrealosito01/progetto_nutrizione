package com.example.pswproject.services;

import com.example.pswproject.entities.Diario;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.repositories.DiarioRepository;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.DiarioAlreadyExistsException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class DiarioService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private DiarioRepository diarioRepository;

    @Transactional(readOnly = true)
    public Collection<Diario> getDiari(String username) throws ResourceNotFoundException{
        Utente utente = utenteService.getUtente(username);
        return utente.getDiari();
    }

    public Diario aggiungi(String username, Diario diario) throws ResourceNotFoundException, DiarioAlreadyExistsException, BadRequestException {
        if(isNotValid(diario))
            throw new BadRequestException();

        Collection<Diario> diari = this.getDiari(username);
        for(Diario d:diari)
            if(d.getGiorno().equals(diario.getGiorno()))
                throw new DiarioAlreadyExistsException();

        diari.add(diario);
        return diarioRepository.save(diario);
    }

    private boolean isNotValid(Diario diario){
        return (diario.getId() != null)||(!diario.getVociDiario().isEmpty());
    }

    public Diario modifica(Long id, String username, Diario diario) throws ResourceNotFoundException, BadRequestException {
        if(isNotValid(diario))
            throw new BadRequestException();

        Collection<Diario> diari = this.getDiari(username);
        for(Diario d:diari)
            if(d.getId().equals(id)){
                d.setAcqua(diario.getAcqua());
                d.setPassi(diario.getPassi());
                return d;
            }

        throw new ResourceNotFoundException();
    }

    public Diario rimuovi(Long id, String username) throws ResourceNotFoundException{
        Collection<Diario> diari = this.getDiari(username);
        for(Diario d:diari)
            if(d.getId().equals(id)){
                diarioRepository.delete(d);
                return d;
            }

        throw new ResourceNotFoundException();
    }

}
