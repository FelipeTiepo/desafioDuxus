package br.com.duxusdesafio.controllers;

import br.com.duxusdesafio.dto.TimeDTO;
import br.com.duxusdesafio.services.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/times")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping("/form")
    public String loadFormTime(Model model) {
        model.addAttribute("timeDTO", new TimeDTO());
        return "time/novo-time";
    }

    @PostMapping()
    public String insert(@Valid TimeDTO timeDTO,
                         BindingResult result,
                         RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return "time/novo-time";
        }
        timeDTO = timeService.insert(timeDTO);
        attributes.addFlashAttribute("mensagem", "Time salvo com sucesso!");
        return "redirect:/times";
    }

    @GetMapping()
    public String findAll(Model model) {
        List<TimeDTO> timesDTO = timeService.findAll();
        model.addAttribute("timesDTO", timesDTO);
        return "/time/listar-times";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        TimeDTO timeDTO = timeService.findById(id);
        model.addAttribute("timeDTO", timeDTO);
        return "/time/editar-time";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id,
                         @Valid TimeDTO timeDTO,
                         BindingResult result) {
        if (result.hasErrors()) {
            timeDTO.setId(id);
            return "/time/editar-time";
        }
        timeService.update(id, timeDTO);
        return "redirect:/times";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id, Model model) {
        timeService.delete(id);
        return "redirect:/times";
    }

}
