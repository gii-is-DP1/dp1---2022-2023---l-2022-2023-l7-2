package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class EstadisticasGlobalesController {
    private static final String VIEW_ESTADISTICAS_GLOBALES = "estadisticas/estadisticasGlobales";


    private EstadisticasGlobalesService estadisticasGlobalesService;
    @Autowired
    public EstadisticasGlobalesController(EstadisticasGlobalesService estadisticasGlobalesService){
        this.estadisticasGlobalesService = estadisticasGlobalesService;
    }


    @GetMapping("/estadisticasGlobales")
    public String get(ModelMap model){
        EstadisticasGlobales estadisticasGlobales = estadisticasGlobalesService.getEstadisticasGlobales();
        model.addAttribute("estadisticaGlobal", estadisticasGlobales);
        return VIEW_ESTADISTICAS_GLOBALES;
    }
}
