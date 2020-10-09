package br.com.lucasteixeira.vilacontrol.service;

import br.com.lucasteixeira.vilacontrol.models.Morador;
import br.com.lucasteixeira.vilacontrol.repository.MoradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoradorService {

    @Autowired
    private MoradorRepository moradorRepository;

    public List<Morador> todosMoradores(){
        return moradorRepository.findAll();
    }

    public Morador moradorPorId(long id) {
        return moradorRepository.findById(id).get();
    }
}
