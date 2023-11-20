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

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

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

    public Diario getDiario(String username, Date giorno) throws ResourceNotFoundException{
        Collection<Diario> diari = this.getDiari(username);
        for(Diario diario:diari)
            if(diario.getGiorno().equals(giorno))
                return diario;
        throw new ResourceNotFoundException();
    }


    public Diario aggiungi(String username, Diario diario) throws ResourceNotFoundException, DiarioAlreadyExistsException, BadRequestException {
        if(isNotValid(diario))
            throw new BadRequestException();

        String dataFormattata = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(diario.getGiorno());
        Collection<Diario> diari = this.getDiari(username);
        for(Diario d:diari)
            if(d.getGiorno().toString().equals(dataFormattata))
                throw new DiarioAlreadyExistsException();

        diari.add(diario);
        return diarioRepository.save(diario);
    }

    private boolean isNotValid(Diario diario){
        return (diario.getId() != null)||(!diario.getVociDiario().isEmpty());
    }

    public Diario modifica(Long id, String username, Diario diario) throws ResourceNotFoundException{
        Collection<Diario> diari = this.getDiari(username);
        for(Diario d:diari)
            if(d.getId().equals(id)){
                d.setAcqua(diario.getAcqua());
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
