package br.com.lucasteixeira.vilacontrol.controllers;

import br.com.lucasteixeira.vilacontrol.models.Contas;
import br.com.lucasteixeira.vilacontrol.models.Residencia;
import br.com.lucasteixeira.vilacontrol.service.ContasService;
import br.com.lucasteixeira.vilacontrol.service.ResidenciaService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class PdfController {

    @Autowired
    private ContasService contasService;

    @Autowired
    private ResidenciaService residenciaService;

    // GERAÇÃO DE RELATÓRIO GERAL EM PDF
    @GetMapping("/dashboard/contas/geral/pdf")
    public void contasGeralPdf(HttpServletResponse response) throws Exception {
        List<Contas> contas = contasService.todasContas();
        gerarPdf(response, contas);
    }


    // GERAÇÃO DE RELATÓRIO DE CONTAS DO MORADOR EM PDF
    @GetMapping("/dashboard/contas/{id_residencia}/pdf")
    public void contasMoradorPdf(HttpServletResponse response, @PathVariable("id_residencia") long id) throws Exception {
        Residencia residencia = residenciaService.residenciaPorId(id);
        List<Contas> contas = residencia.getContas();
        gerarPdf(response, contas);
    }

    private void gerarPdf(HttpServletResponse response, List<Contas> contas) throws JRException, IOException {
        response.setContentType("text/html");
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(contas);

        File file = ResourceUtils.getFile("classpath:templates/reports/relatorio_contas.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);

        JRPdfExporter pdfExporter = new JRPdfExporter();
        pdfExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        ByteArrayOutputStream pdfReportStream = new ByteArrayOutputStream();
        pdfExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfReportStream));
        pdfExporter.exportReport();

        response.setContentType("application/pdf");
        response.setHeader("Content-Length", String.valueOf(pdfReportStream.size()));
        response.addHeader("Content-Disposition", "inline; filename=vila.pdf;");

        OutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(pdfReportStream.toByteArray());
        responseOutputStream.close();
        pdfReportStream.close();
    }

}
