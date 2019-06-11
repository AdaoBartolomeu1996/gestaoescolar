package com.gestaoescolar.controller;

import com.gestaoescolar.config.USLocalDateFormatter;
import com.gestaoescolar.domain.AnoLetivo;
import com.gestaoescolar.service.SchedulingImpl;
import com.gestaoescolar.service.map.AnoLetivoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDate;
import java.util.List;
import java.util.Locale;




@Slf4j
@Controller
@RequestMapping("/imel/anoletivo")
public class AnoLetivoController {


    @Autowired
    private SchedulingImpl scheduling;

    @Autowired
    private AnoLetivoService anoLetivoService;

    @ModelAttribute("dateFormat")
    public String localeFormat(Locale locale) {
        return USLocalDateFormatter.getPattern(locale);
    }

    @GetMapping("/registar")
    public String anoLetivoAdicionarGet(Model model) {

        AnoLetivo anoLetivo = new AnoLetivo();
        model.addAttribute("anoLetivo", anoLetivo);
        return "addAnoLetivo";
    }

    @PostMapping("/registar")
    public String professorAdicionarPost(@ModelAttribute("anoLetivo") AnoLetivo anoLetivo, RedirectAttributes redirectAttributes) {

        LocalDate localDate = LocalDate.now();

        List<AnoLetivo> list = anoLetivoService.listarAnoLetivoEstado();


        if (list.isEmpty()){

           if (anoLetivo.getAnoAbertura().isEqual(localDate) || anoLetivo.getAnoAbertura().isAfter(localDate)) {

            if (anoLetivo.getDataInscricaoInicio().isAfter(anoLetivo.getAnoAbertura())){

                if (anoLetivo.getDataInscricaoFim().isAfter(anoLetivo.getDataInscricaoInicio())){

                  if (anoLetivo.getDataMatriculaInicio().isAfter(anoLetivo.getDataInscricaoFim())){

                        if (anoLetivo.getDataMatriculaFim().isAfter(anoLetivo.getDataMatriculaInicio())){

                            anoLetivoService.registrarAnoLetivo(anoLetivo);
                            redirectAttributes.addFlashAttribute("message", "Dados salvo");
                            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

                            return "redirect:/imel/anoletivo/registar";
                        }

                        redirectAttributes.addFlashAttribute("message", "Erro a data de  término das  matriculas  "+anoLetivo.getDataMatriculaFim()+" deve ser maior  que a data de início das matriculas "+anoLetivo.getDataMatriculaInicio()+".");
                        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                        return "redirect:/imel/anoletivo/registar";

                    }
                    redirectAttributes.addFlashAttribute("message", "Erro a data de  início das  matriculas "+anoLetivo.getDataMatriculaInicio()+" deve ser maior  que a data de iníco das inscrições "+anoLetivo.getDataInscricaoInicio()+".");
                    redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                    return "redirect:/imel/anoletivo/registar";
                  }
                redirectAttributes.addFlashAttribute("message", "Erro a data de témino das  inscrições "+anoLetivo.getDataInscricaoFim()+"  deve ser maior  que a data de início das inscrições  "+anoLetivo.getDataInscricaoInicio()+".");
                redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                return "redirect:/imel/anoletivo/registar";

            }

            redirectAttributes.addFlashAttribute("message", "Erro a data de  início das  inscrições "+anoLetivo.getDataInscricaoInicio()+" deve ser maior  que a data de abertura "+anoLetivo.getAnoAbertura()+".");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/imel/anoletivo/registar";
            }

            redirectAttributes.addFlashAttribute("message", "Erro a data de início do ano letivo  " + anoLetivo.getAnoAbertura() + " deve ser maior que a data actual "+localDate+" .");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/imel/anoletivo/registar";
        }

        redirectAttributes.addFlashAttribute("message", "Erro por favor antes de registar o novo ano letivo feche o ano letivo que se encontra atualmente aberto.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/imel/anoletivo/registar";
    }


    @GetMapping("/pesquisar")
    public String pesquisarAnoLetivo(Model model){

        model.addAttribute("anoletivos",anoLetivoService.listarAnoletivo());
        return "PesquisarAnoLetivo";
    }

    @PostMapping("/eliminar")
    public String anoLetivoEliminar(@RequestParam long id) {

        anoLetivoService.apagarAnoletivo(id);
        return "redirect:/imel/anoletivo/pesquisar";
    }

    @PostMapping("/fechar")
    public String fecharAnoLetivo(@RequestParam long id, RedirectAttributes redirectAttributes) {
        AnoLetivo anoLetivo = anoLetivoService.procurarAnoLetivoPorId(id);

        if (anoLetivo.isEstado()==true){
            log.error("ENTROU");
            anoLetivoService.fecharAnoLetivo(id);
            return "redirect:/imel/anoletivo/pesquisar";
        }
        redirectAttributes.addFlashAttribute("message", "Erro este ano letivo já está com o estado Fechado.");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/imel/anoletivo/pesquisar";
    }

    @GetMapping("/editar/{id}")
    public String anoLetivoPesquisarId(@PathVariable("id") Long id ,Model model){

        model.addAttribute("anoLetivo",anoLetivoService.procurarAnoLetivoPorId(id));
        return "editarAnoLetivo";
    }

    @PostMapping("/editar")
    public String anoLetivoEditar(@ModelAttribute("anoLetivo") AnoLetivo anoLetivo, RedirectAttributes redirectAttributes, BindingResult result, Model model) {

        LocalDate localDate = LocalDate.now();

        if (anoLetivo.getAnoAbertura().isEqual(localDate) || anoLetivo.getAnoAbertura().isAfter(localDate)) {

            if (anoLetivo.getDataInscricaoInicio().isAfter(anoLetivo.getAnoAbertura())) {

                if (anoLetivo.getDataInscricaoFim().isAfter(anoLetivo.getDataInscricaoInicio())) {

                    if (anoLetivo.getDataMatriculaInicio().isAfter(anoLetivo.getDataInscricaoFim())) {

                        if (anoLetivo.getDataMatriculaFim().isAfter(anoLetivo.getDataMatriculaInicio())) {

                            anoLetivoService.atualizarAnoLetivo(anoLetivo);
                            redirectAttributes.addFlashAttribute("message", "Dados salvo");
                            redirectAttributes.addFlashAttribute("alertClass", "alert-success");

                            return "redirect:/imel/anoletivo/editar/"+anoLetivo.getId()+"";
                        }

                        redirectAttributes.addFlashAttribute("message", "Erro a data de  término das  matriculas  " + anoLetivo.getDataMatriculaFim() + " deve ser maior  que a data de início das matriculas " + anoLetivo.getDataMatriculaInicio() + ".");
                        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                        return "redirect:/imel/anoletivo/editar/"+anoLetivo.getId()+"";

                    }
                    redirectAttributes.addFlashAttribute("message", "Erro a data de  início das  matriculas " + anoLetivo.getDataMatriculaInicio() + " deve ser maior  que a data de iníco das inscrições " + anoLetivo.getDataInscricaoInicio() + ".");
                    redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                    return "redirect:/imel/anoletivo/editar/"+anoLetivo.getId()+"";
                }
                redirectAttributes.addFlashAttribute("message", "Erro a data de témino das  inscrições " + anoLetivo.getDataInscricaoFim() + "  deve ser maior  que a data de início das inscrições  " + anoLetivo.getDataInscricaoInicio() + ".");
                redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
                return "redirect:/imel/anoletivo/editar/"+anoLetivo.getId()+"";

            }

            redirectAttributes.addFlashAttribute("message", "Erro a data de  início das  inscrições " + anoLetivo.getDataInscricaoInicio() + " deve ser maior  que a data de abertura " + anoLetivo.getAnoAbertura() + ".");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            return "redirect:/imel/anoletivo/editar/"+anoLetivo.getId()+"";
        }

        redirectAttributes.addFlashAttribute("message", "Erro a data de início do ano letivo  " + anoLetivo.getAnoAbertura() + " deve ser maior que a data actual " + localDate + " .");
        redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
        return "redirect:/imel/anoletivo/editar/"+anoLetivo.getId()+"";
    }

}
