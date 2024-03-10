package com.backend.proyectoClinica.service;

import com.backend.proyectoClinica.dto.entrada.PacienteEntradaDto;
import com.backend.proyectoClinica.dto.salida.PacienteSalidaDto;

import java.util.List;

public interface IPacienteService {
    PacienteSalidaDto guardarPaciente(PacienteEntradaDto paciente);

    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto buscarPacientePorId(int id);
}
