package com.backend.proyectoClinica.service;

import com.backend.proyectoClinica.dto.entrada.PacienteEntradaDto;
import com.backend.proyectoClinica.dto.salida.PacienteSalidaDto;
import com.backend.proyectoClinica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface IPacienteService {
    PacienteSalidaDto guardarPaciente(PacienteEntradaDto paciente);

    List<PacienteSalidaDto> listarPacientes();

    PacienteSalidaDto buscarPacientePorId(Long id);

    void eliminarPaciente(Long id) throws ResourceNotFoundException;

    PacienteSalidaDto modificarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id) throws ResourceNotFoundException;
}
