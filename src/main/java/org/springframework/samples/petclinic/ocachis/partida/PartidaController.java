package org.springframework.samples.petclinic.ocachis.partida;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PartidaController {
    private PartidaService partidaService;

    private static final String VIEWS_SALAS = "partidas/salaList";
	
	@Autowired
	public PartidaController(PartidaService partidaService) {
		this.partidaService=partidaService;
	}
	
    @GetMapping
    public ModelAndView showSalaList() {
        ModelAndView mav = new ModelAndView(VIEWS_SALAS);
        mav.addObject("partidas", partidaService.findAll());
        return mav;
    }

	@InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping(value = "/partida/create")
	public String initCreationForm(Map<String, Object> model) {
		Partida product = new Partida();
		model.put("partida", product);
		return "partida/salaList";
	}

    @PostMapping(value = "/partida/create")
	public String processCreationForm(@Valid Partida partida, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "partida/createPartidaForm";
		}
		else {
			this.partidaService.save(partida);
			return "welcome";
		}
	}   
}