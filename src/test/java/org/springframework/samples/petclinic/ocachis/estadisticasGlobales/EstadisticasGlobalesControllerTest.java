package org.springframework.samples.petclinic.ocachis.estadisticasGlobales;


import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;



@WebMvcTest(controllers = EstadisticasGlobalesController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
public class EstadisticasGlobalesControllerTest {
    @MockBean
    EstadisticasGlobalesService egs;

    @Autowired
    MockMvc mockMvc; 

    @WithMockUser(value = "spring")
    @Test
    void testShowEstadisticasGlobales() throws Exception{
        given(egs.getEstadisticasGlobales()).willReturn(new EstadisticasGlobales());
        mockMvc.perform(get("/estadisticasGlobales"))
            .andExpect(status().isOk())
            .andExpect(view().name("estadisticas/estadisticasGlobales"))
            .andExpect(model().attributeExists("estadisticaGlobal"));
    }
}
