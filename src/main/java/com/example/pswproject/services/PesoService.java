package com.example.pswproject.services;

import com.example.pswproject.entities.Paziente;
import com.example.pswproject.entities.Peso;
import com.example.pswproject.repositories.PazienteRepository;
import com.example.pswproject.repositories.PesoRepository;
import com.example.pswproject.support.exceptions.UserNotFoundException;
import com.example.pswproject.support.exceptions.WeightAlreadyInsertedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PesoService {

    @Autowired
    private PazienteRepository rep;

    @Autowired
    private PesoRepository prep;

    @Transactional
    public Peso addPeso(String data, BigDecimal peso, String username) throws ParseException, WeightAlreadyInsertedException, UserNotFoundException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date giorno = simpleDateFormat.parse(data);

        Optional<Paziente> res = rep.findById(username);
        if(res.isEmpty())
            throw new UserNotFoundException();

        Paziente c = res.get();
        // NON devono essere presenti due pesi dello stesso cliente nello stesso giorno
        if(prep.existsByDataAndPaziente(giorno,c))
            throw new WeightAlreadyInsertedException();

        Peso p = new Peso();
        p.setPaziente(c);
        p.setData(giorno);
        p.setValore(peso);

        return prep.save(p);
    }


    @Transactional(readOnly = true)
    public List<Peso> getPesi(String username) throws UserNotFoundException {
        Optional<Paziente> res = rep.findById(username);
        if(res.isEmpty())
            throw new UserNotFoundException();

        Paziente c = res.get();
        return prep.findAllByPaziente(c);
    }
}
