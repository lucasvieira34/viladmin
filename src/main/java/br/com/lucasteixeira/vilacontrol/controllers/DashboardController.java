package br.com.lucasteixeira.vilacontrol.controllers;

import br.com.lucasteixeira.vilacontrol.enums.StatusPagamento;
import br.com.lucasteixeira.vilacontrol.models.Contas;
import br.com.lucasteixeira.vilacontrol.models.Residencia;
import br.com.lucasteixeira.vilacontrol.service.ContasService;
import br.com.lucasteixeira.vilacontrol.service.MoradorService;
import br.com.lucasteixeira.vilacontrol.service.ResidenciaService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
public class DashboardController {

    @Autowired
    private ResidenciaService residenciaService;

    @Autowired
    private MoradorService moradorService;

    @Autowired
    private ContasService contasService;

    @Autowired
    private ApplicationContext applicationContext;

    // LISTA DE MORADORES E RESIDÊNCIAS
    @GetMapping("/dashboard/residencias")
    public ModelAndView dashboard(){
        List<Residencia> residencias = residenciaService.todasResidencias();

        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("residencias", residencias);
        return mv;
    }

    // RELATÓRIO GERAL DE TODAS AS CONTAS
    @GetMapping("/dashboard/contas/geral")
    public ModelAndView todasContas(){
        List<Contas> contas = contasService.todasContas();
        ordenarPorData(contas);
        ModelAndView mv = new ModelAndView("contas/contas_geral");
        mv.addObject("contas", contas);
        return mv;
    }

    // LISTA DE CONTAS DO MORADOR
    @GetMapping("/dashboard/contas/{id_residencia}")
    public ModelAndView listarContas(@PathVariable("id_residencia") long id){
        Residencia residencia = residenciaService.residenciaPorId(id);
        
        List<Contas> contas = residencia.getContas();
        ordenarPorData(contas);

        ModelAndView mv = new ModelAndView("contas/contas-residencia");
        mv.addObject("residencia", residencia);
        mv.addObject("contas", contas);
        return mv;
    }

    // LISTA DE CONTAS DO MORADOR PELO ANO
    @GetMapping("/dashboard/contas/filtro/{id_residencia}")
    public ModelAndView listarContasPeloAno(@PathVariable("id_residencia") long id, @RequestParam(required = false) Integer ano){

        Residencia residencia = residenciaService.residenciaPorId(id);
        List<Contas> contas = contasService.contasPorAno(ano,residencia.getId_residencia());

        ModelAndView mv = new ModelAndView("contas/contas-residencia");
        mv.addObject("residencia", residencia);
        mv.addObject("contas", contas);
        return mv;
    }

    @GetMapping("/dashboard/contas/nova/{id_residencia}")
    public ModelAndView novaConta(@PathVariable("id_residencia") long id){
        Residencia residencia = residenciaService.residenciaPorId(id);

        ModelAndView mv = new ModelAndView("contas/nova-conta");
        mv.addObject("residencia", residencia);
        return mv;
    }

    @PostMapping("/dashboard/contas/nova/{id_residencia}")
    public String salvarNovaConta(Contas conta, @PathVariable("id_residencia") long id){
        Residencia residencia = residenciaService.residenciaPorId(id);
        conta.setResidencia(residencia);
        contasService.salvarConta(conta);
        return "redirect:/dashboard/contas/{id_residencia}";
    }


    @ModelAttribute("todosStatusPagamento")
    public List<StatusPagamento> todosStatusPagamento() {
        return Arrays.asList(StatusPagamento.values());
    }

    private void ordenarPorData(List<Contas> contas) {
        Collections.sort(contas, new Comparator<Contas>() {
            public int compare(Contas o1, Contas o2) {
                if (o1.getPagamento() == null || o2.getPagamento() == null)
                    return 0;
                return o1.getPagamento().compareTo(o2.getPagamento());
            }
        });
    }

}
