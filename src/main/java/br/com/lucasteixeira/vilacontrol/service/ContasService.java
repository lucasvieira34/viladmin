package br.com.lucasteixeira.vilacontrol.service;

import br.com.lucasteixeira.vilacontrol.models.Contas;
import br.com.lucasteixeira.vilacontrol.repository.ContasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ContasService {

    @Autowired
    private ContasRepository contasRepository;

    public Contas contaPorId(long id) {
        return contasRepository.findById(id).get();
    }

    //OrderBy
    public List<Contas> todasContas(){
        return contasRepository.findAllByResidencia();
    }

    public List<Contas> contasPorAno(Integer ano, long id_residencia){
        return contasRepository.findByYear(ano,id_residencia);
    }

    public List<Contas> contasPorResidencia(long id){
        return contasRepository.findAllById(Collections.singleton(id));
    }

    public void salvarConta(Contas conta){
        contasRepository.save(conta);
    }

}
