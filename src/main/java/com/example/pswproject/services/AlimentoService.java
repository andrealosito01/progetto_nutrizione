package com.example.pswproject.services;

import com.example.pswproject.entities.Alimento;
import com.example.pswproject.entities.Utente;
import com.example.pswproject.repositories.AlimentoRepository;
import com.example.pswproject.repositories.UtenteRepository;
import com.example.pswproject.support.exceptions.AlimentoAlreadyExistsException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AlimentoService {

    @Autowired
    UtenteRepository utenteRepository;

    @Autowired
    AlimentoRepository alimentoRepository;

    @Transactional(readOnly = true)
    public Collection<Alimento> getAlimenti(String username) throws ResourceNotFoundException {
        Optional<Utente> opUtente = utenteRepository.findByUsername(username);
        if(opUtente.isEmpty())
            throw new ResourceNotFoundException();

        Utente utente = opUtente.get();
        return utente.getAlimenti();
    }

    public Alimento aggiungi(String username, Alimento alimento) throws ResourceNotFoundException, AlimentoAlreadyExistsException {
        Collection<Alimento> alimenti = this.getAlimenti(username);
        for(Alimento a: alimenti)
            if(a.getNome().equals(alimento.getNome()))
                throw new AlimentoAlreadyExistsException();

        alimenti.add(alimento);
        return alimentoRepository.save(alimento);
    }

    public Alimento modifica(Long id, String username, Alimento alimento) throws ResourceNotFoundException, AlimentoAlreadyExistsException{
        Collection<Alimento> alimenti = this.getAlimenti(username);

        for(Alimento a:alimenti)
            if((a.getNome().equalsIgnoreCase(alimento.getNome())) && (a.getId() != id))
                // c'è già un altro alimento con lo stesso nome che noi stiamo cercando di assegnare
                throw new AlimentoAlreadyExistsException();

        for(Alimento a:alimenti)
            if(a.getId() == id){
                a.setNome(alimento.getNome());
                a.setDescrizione(alimento.getDescrizione());
                a.setEnergia(alimento.getEnergia());
                a.setProteine(alimento.getProteine());
                a.setCarboidrati(alimento.getCarboidrati());
                a.setFibre(alimento.getFibre());
                a.setZuccheri(alimento.getZuccheri());
                a.setGrassiTotali(alimento.getGrassiTotali());
                a.setGrassiSaturi(alimento.getGrassiSaturi());
                a.setGrassiPolinsaturi(alimento.getGrassiPolinsaturi());
                a.setGrassiMonoinsaturi(alimento.getGrassiMonoinsaturi());
                a.setGrassiTrans(alimento.getGrassiTrans());
                a.setColesterolo(alimento.getColesterolo());
                a.setSodio(alimento.getSodio());
                a.setPotassio(alimento.getPotassio());
                a.setVitaminaA(alimento.getVitaminaA());
                a.setVitaminaC(alimento.getVitaminaC());
                a.setCalcio(alimento.getCalcio());
                a.setFerro(alimento.getFerro());
                return a;
            }
        throw new ResourceNotFoundException();
    }

    public Alimento rimuovi(Long id, String username) throws ResourceNotFoundException{
        Collection<Alimento> alimenti = this.getAlimenti(username);

        for(Alimento a: alimenti)
            if(a.getId() == id){
                alimentoRepository.delete(a);
                return a;
            }

        throw new ResourceNotFoundException();
    }

}
