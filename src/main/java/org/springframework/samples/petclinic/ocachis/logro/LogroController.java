package org.springframework.samples.petclinic.ocachis.logro;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.ocachis.logro.exceptions.MetaNegativaException;
import org.springframework.samples.petclinic.ocachis.logro.exceptions.MultiplesMetasDefinidasException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logro")
public class LogroController {
    
    private LogroService logroService;

    @Autowired
    public LogroController(LogroService logroService){
        this.logroService = logroService;
    }

    @GetMapping(value="/listLogros")
    public String listarLogros(Map<String,Object> model){
        Collection<Logro> results = this.logroService.findAllLogros();
		model.put("logros", results);
		return "logro/listLogros";
    }
    
    @GetMapping("/new")
    public String initCrearLogro(ModelMap model){
        Logro l = new Logro();
        model.put("logro", l);
        return "logro/createOrUpdateLogroForm";
    }

    @PostMapping("/new")
    public String processCrearLogro(@Valid Logro logro, BindingResult result, ModelMap model) throws IllegalAccessException{
        if (result.hasErrors()) {
			model.put("logro", logro);
			return "logro/createOrUpdateLogroForm";
		}else{
            try{
                this.logroService.saveLogro(logro);
            }catch(MultiplesMetasDefinidasException e ){
                model.put("message", "Se debe definir una única meta por logro");
                model.put("logro", logro);
                return "logro/createOrUpdateLogroForm";
            }catch(MetaNegativaException e){
                model.put("message", "No se pueden definir metas negativas");
                model.put("logro", logro);
                return "logro/createOrUpdateLogroForm";
            }
            }
            return "redirect:/logro/listLogros";
        }
    @GetMapping("/{logroId}/edit")
    public String initEditarLogro(@PathVariable("logroId") int logroId, ModelMap model){
        Logro l = this.logroService.findById(logroId);
        model.put("logro", l);
        return "logro/createOrUpdateLogroForm";
    }

    @PostMapping("/{logroId}/edit")
    public String processEditarLogro(@PathVariable("logroId") int logroId, @Valid Logro logro, BindingResult result, ModelMap model) throws IllegalAccessException{
        if (result.hasErrors()) {
			model.put("logro", logro);
			return "logro/createOrUpdateLogroForm";
		}else{
            Logro logroToBeUpdated = logroService.findById(logroId);
            BeanUtils.copyProperties(logro, logroToBeUpdated,"id");
            try {
                this.logroService.saveLogro(logroToBeUpdated);
            }catch (MultiplesMetasDefinidasException e) {
                model.put("message", "Se debe definir una única meta por logro");
                model.put("logro", logro);
                return "logro/createOrUpdateLogroForm";
            }catch(MetaNegativaException e){
                model.put("message", "No se pueden definir metas negativas");
                model.put("logro", logro);
                return "logro/createOrUpdateLogroForm";
            }
            return "redirect:/logro/listLogros";
        }
    }
    
   
}
