package com.backend.proyectoClinica.service.impl;

import com.backend.proyectoClinica.dto.entrada.DomicilioEntradaDto;
import com.backend.proyectoClinica.dto.entrada.PacienteEntradaDto;
import com.backend.proyectoClinica.dto.salida.PacienteSalidaDto;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class PacienteServiceTest {

    @Autowired
    private PacienteService pacienteService;


    @Test
    @Order(1)
    void deberiaRegistrarseUnPacienteYRetornarSuId() {
        //arrange
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Gonzalo", "Leone", 44797626, LocalDate.of(2025, 5, 15), new DomicilioEntradaDto("Simbron", 4321, "Devoto", "Buenos Aires"));

        //act
        PacienteSalidaDto pacienteSalidaDto = pacienteService.guardarPaciente(pacienteEntradaDto);

        //assert
        assertNotNull(pacienteSalidaDto);
        assertNotNull(pacienteSalidaDto.getId());

    }

    @Test
    @Order(2)
    void deberiaEliminarseElPacienteConId1() {


        assertDoesNotThrow(() -> pacienteService.eliminarPaciente(1L));
    }


    @Test
    @Order(3)
    void deberiaDevolverUnaListaVaciaDePacientes() {
        List<PacienteSalidaDto> pacientes = pacienteService.listarPacientes();

        assertTrue(pacientes.isEmpty());
    }




}