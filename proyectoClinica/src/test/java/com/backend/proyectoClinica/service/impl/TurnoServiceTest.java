package com.backend.proyectoClinica.service.impl;

import com.backend.proyectoClinica.dto.entrada.DomicilioEntradaDto;
import com.backend.proyectoClinica.dto.entrada.OdontologoEntradaDto;
import com.backend.proyectoClinica.dto.entrada.PacienteEntradaDto;
import com.backend.proyectoClinica.dto.entrada.TurnoEntradaDto;
import com.backend.proyectoClinica.dto.salida.OdontologoSalidaDto;
import com.backend.proyectoClinica.dto.salida.PacienteSalidaDto;
import com.backend.proyectoClinica.dto.salida.TurnoSalidaDto;
import com.backend.proyectoClinica.exceptions.BadRequestException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestPropertySource(locations = "classpath:application-test.properties")
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    void deberiaRegistrarseUnTurnoConUnPacienteYUnOdontologoYRetornarSuId() throws BadRequestException {

        OdontologoEntradaDto odontologoEntradaDto = new OdontologoEntradaDto("1234567891", "Ricardo", "Darin");
        PacienteEntradaDto pacienteEntradaDto = new PacienteEntradaDto("Gonzalo", "Leone", 44797626, LocalDate.of(2025, 5, 15), new DomicilioEntradaDto("Simbron", 4321, "Devoto", "Buenos Aires"));
        TurnoEntradaDto turnoEntradaDto = new TurnoEntradaDto(1L,1L, LocalDateTime.of(2025, 3, 13, 13, 30));

        OdontologoSalidaDto odontologoSalidaDto = odontologoService.guardarOdontologo(odontologoEntradaDto);
        PacienteSalidaDto pacienteSalidaDto = pacienteService.guardarPaciente(pacienteEntradaDto);
        TurnoSalidaDto turnoSalidaDto = turnoService.guardarTurno(turnoEntradaDto);


        assertNotNull(turnoSalidaDto);
    }

    @Test
    @Order(2)
    void deberiaBuscarYEncontrarUnTurnoConId1(){
        TurnoSalidaDto turnoBuscado = turnoService.buscarTurnoPorId(1L);

        assertNotNull(turnoBuscado);
    }

    @Test
    @Order(3)
    void deberiaEliminarseElTurnoConId1() {


        assertDoesNotThrow(() -> turnoService.eliminarTurno(1L));
    }

    }