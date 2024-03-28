package com.backend.proyectoClinica.service.impl;

import com.backend.proyectoClinica.dto.entrada.OdontologoEntradaDto;
import com.backend.proyectoClinica.dto.salida.OdontologoSalidaDto;
import com.backend.proyectoClinica.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void deberiaRegistrarUnOdontologoYDevolverloConId() {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("1234567891", "Ricardo", "Darin");

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.guardarOdontologo(odontologoEntradaDto);

        assertNotNull(odontologoSalidaDto);
        assertNotNull(odontologoSalidaDto.getId());
        assertEquals("1234567891", odontologoSalidaDto.getNumeroMatricula());
    }

    @Test
    @Order(2)
    void deberiaBuscarYEncontrarUnOdontologoConId1(){
        OdontologoSalidaDto odontologoBuscado = odontologoService.buscarOdontologoPorId(1L);

        assertNotNull(odontologoBuscado);
    }

    @Test
    @Order(3)
    void deberiaEliminarseElOdontologoConId1() {

        assertDoesNotThrow(() -> odontologoService.eliminarOdontologo(1L));

    }

    @Test
    @Order(4)
    void deberiaLanzarLaExcepcionPorqueYaNoExisteElOdontologoConId1() throws ResourceNotFoundException {
        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("1234567896", "Guillermo", "Francella" );

        OdontologoSalidaDto modificacion = odontologoService.modificarOdontologo(odontologoEntradaDto, 1l);

        assertNull(modificacion);

    }
}