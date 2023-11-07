package com.example.pswproject.services;

import com.example.pswproject.entities.Diario;
import com.example.pswproject.entities.VoceDiario;
import com.example.pswproject.repositories.VoceDiarioRepository;
import com.example.pswproject.support.exceptions.BadRequestException;
import com.example.pswproject.support.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Transactional
public class VoceDiarioService {

    @Autowired
    private DiarioService diarioService;

    @Autowired
    private VoceDiarioRepository voceDiarioRepository;

    @Transactional(readOnly = true)
    public Collection<VoceDiario> getVociDiario(String username, Long idDiario) throws ResourceNotFoundException {
        Collection<Diario> diariUtente = diarioService.getDiari(username);
        for(Diario diario:diariUtente)
            if(diario.getId().equals(idDiario))
                return diario.getVociDiario();

        throw new ResourceNotFoundException();
    }

    public VoceDiario aggiungi(String username, Long idDiario, VoceDiario voceDiario) throws ResourceNotFoundException, BadRequestException {
        if(isNotValid(voceDiario))
            throw new BadRequestException();

        Collection<VoceDiario> vociDiario = this.getVociDiario(username,idDiario);
        vociDiario.add(voceDiario);
        return voceDiarioRepository.save(voceDiario);
    }

    private boolean isNotValid(VoceDiario voceDiario){
        return voceDiario.getId() != null;
    }

    public VoceDiario modifica(String username, Long idDiario, Long id, VoceDiario voceDiario) throws ResourceNotFoundException, BadRequestException {
        if(isNotValid(voceDiario))
            throw new BadRequestException();

        Collection<VoceDiario> vociDiario = this.getVociDiario(username,idDiario);
        boolean trovato = false;
        for(VoceDiario voce: vociDiario)
            if(voce.getId().equals(id)) {
                voceDiarioRepository.delete(voce);
                trovato = true;
            }
        if(trovato) {
            vociDiario.add(voceDiario);
            return voceDiarioRepository.save(voceDiario);
        }else{
            throw new ResourceNotFoundException();
        }
    }

    public VoceDiario rimuovi(String username, Long idDiario, Long id) throws ResourceNotFoundException {
        Collection<VoceDiario> vociDiario = this.getVociDiario(username,idDiario);
        for(VoceDiario voce: vociDiario)
            if(voce.getId().equals(id)) {
                // se funziona, implementalo così anche in AlimentoService
                voceDiarioRepository.delete(voce);
                return voce;
            }

        throw new ResourceNotFoundException();
    }

}
