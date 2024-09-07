package br.com.duxusdesafio.controllers;

import br.com.duxusdesafio.dto.ComposicaoTimeDTO;
import br.com.duxusdesafio.dto.IntegranteDTO;
import br.com.duxusdesafio.dto.TimeDTO;
import br.com.duxusdesafio.models.ComposicaoTime;
import br.com.duxusdesafio.models.Time;
import br.com.duxusdesafio.services.ComposicaoTimeService;
import br.com.duxusdesafio.services.IntegranteService;
import br.com.duxusdesafio.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/integrantes")
public class IntegranteController {

    @Autowired
    private IntegranteService service;

    @Autowired
    private TimeService timeService;

    @Autowired
    private ComposicaoTimeService composicaoTimeService;

    @ModelAttribute("composicaoTimes")
    public List<ComposicaoTimeDTO> composicaoTimes(){
        return composicaoTimeService.findAll();
    }

    @ModelAttribute("times")
    public List<TimeDTO> times() {
        return timeService.findAll();
    }

    @GetMapping("/form")
    public String loadForm(Model model) {
        model.addAttribute("integranteDTO", new IntegranteDTO());
        return "integrante/novo-integrante";
    }

    @PostMapping()
    public String insert(@Valid IntegranteDTO integranteDTO,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "integrante/novo-integrante";
        }
        integranteDTO = service.insert(integranteDTO);
        attributes.addFlashAttribute("mensagem", "Integrante salvo com sucesso");
        return "redirect:/integrantes/form";
    }

    @GetMapping()
    public String findAll(Model model) {
        model.addAttribute("integrantes", service.findAll());
        return "/integrante/listar-integrantes";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        IntegranteDTO integranteDTO = service.findById(id);
        model.addAttribute("integranteDTO", integranteDTO);
        return "/integrante/editar-integrante";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid IntegranteDTO integranteDTO,
                         BindingResult result) {
        if (result.hasErrors()) {
            integranteDTO.setId(id);
            return "/integrante/editar-integrante";
        }
        service.update(id, integranteDTO);
        return "redirect:/integrantes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        service.delete(id);
        return "redirect:/integrantes";
    }


}
