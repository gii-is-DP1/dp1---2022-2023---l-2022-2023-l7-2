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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sala")
public class PartidaController {
    
    private PartidaService partidaService;

    @Autowired
	public PartidaController(PartidaService partidaService) {
		this.partidaService=partidaService;
	}

    private static final String VIEWS_SALAS = "partidas/salaList";
    private static final String VIEWS_SALAS_CREATE_FORM = "partidas/createPartidaForm";
	
    @GetMapping("/")
    public ModelAndView showSalaList() {
        ModelAndView mav = new ModelAndView(VIEWS_SALAS);
        mav.addObject("partidaOca", partidaService.findAllOca());
		mav.addObject("partidaParchis", partidaService.findAllParchis());
        return mav;
    }

    @InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
 
/*	@GetMapping(value = "/list")
	public String verSalas(Map<String, Object> model) {
		PartidaOca pO = new PartidaOca();
		PartidaParchis pP = new PartidaParchis();
		model.put("partidaoca", pO);
		model.put("partidaparchis", pP);
		return VIEWS_SALAS;
	}*/

    @PostMapping(value = "/create")
	public String processCreationForm(@Valid PartidaOca partida, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return VIEWS_SALAS_CREATE_FORM;
		}
		else {
			this.partidaService.saveOca(partida);
			return VIEWS_SALAS_CREATE_FORM;
		}
	}   
}