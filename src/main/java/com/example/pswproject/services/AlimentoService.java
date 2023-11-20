package com.example.pswproject.services;

import com.example.pswproject.entities.Alimento;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.repositories.AlimentoRepository;
import com.example.pswproject.support.exceptions.AlimentoAlreadyExistsException;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class AlimentoService {

    @Autowired
    private UtenteService utenteService;

    @Autowired
    private AlimentoRepository alimentoRepository;

    @Transactional(readOnly = true)
    public Collection<Alimento> getAlimenti(String username) throws ResourceNotFoundException {
        Utente utente = utenteService.getUtente(username);
        return utente.getAlimenti();
    }

    public Alimento aggiungi(String username, Alimento alimento) throws ResourceNotFoundException, AlimentoAlreadyExistsException, BadRequestException {
        if(isNotValid(alimento))
            throw new BadRequestException();

        Collection<Alimento> alimenti = this.getAlimenti(username);
        for(Alimento a: alimenti) {
            if (a.getNome().equals(alimento.getNome()))
                throw new AlimentoAlreadyExistsException();
        }

        alimenti.add(alimento);
        return alimentoRepository.save(alimento);
    }

    private boolean isNotValid(Alimento alimento){
        return alimento.getId() != null;
    }

    public Alimento modifica(Long id, String username, Alimento alimento) throws ResourceNotFoundException, AlimentoAlreadyExistsException, BadRequestException {
        if(isNotValid(alimento))
            throw new BadRequestException();

        Collection<Alimento> alimenti = this.getAlimenti(username);

        for(Alimento a:alimenti)
            if((a.getNome().equalsIgnoreCase(alimento.getNome())) && (!a.getId().equals(id)))
                // c'è già un altro alimento con lo stesso nome che noi stiamo cercando di assegnare
                throw new AlimentoAlreadyExistsException();

        boolean trovato = false;
        for(Alimento a:alimenti)
            if(a.getId().equals(id)){
                alimentoRepository.delete(a);
                trovato = true;
            }

        if(trovato){
            alimenti.add(alimento);
            return alimentoRepository.save(alimento);
        }else
            throw new ResourceNotFoundException();
    }

    public Alimento rimuovi(Long id, String username) throws ResourceNotFoundException{
        Collection<Alimento> alimenti = this.getAlimenti(username);

        for(Alimento a: alimenti)
            if(a.getId().equals(id)){
                alimentoRepository.delete(a);
                return a;
            }

        throw new ResourceNotFoundException();
    }

}
