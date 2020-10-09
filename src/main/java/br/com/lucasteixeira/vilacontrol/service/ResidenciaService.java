package br.com.lucasteixeira.vilacontrol.service;

import br.com.lucasteixeira.vilacontrol.models.Residencia;
import br.com.lucasteixeira.vilacontrol.repository.ResidenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidenciaService {

    @Autowired
    private ResidenciaRepository residenciaRepository;

    public Residencia residenciaPorId(long id){
        return residenciaRepository.findById(id).get();
    }

    public List<Residencia> todasResidencias(){
        return residenciaRepository.findAll();
    }

}
