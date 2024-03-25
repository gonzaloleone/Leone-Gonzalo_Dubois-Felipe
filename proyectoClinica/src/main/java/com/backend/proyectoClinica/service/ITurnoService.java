package com.backend.proyectoClinica.service;

import com.backend.proyectoClinica.dto.entrada.TurnoEntradaDto;
import com.backend.proyectoClinica.dto.salida.TurnoSalidaDto;
import com.backend.proyectoClinica.exceptions.BadRequestException;
import com.backend.proyectoClinica.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ITurnoService {

    TurnoSalidaDto guardarTurno(TurnoEntradaDto turno) throws BadRequestException;

    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto buscarTurnoPorId(Long id);

    void eliminarTurno(Long id) throws ResourceNotFoundException;

    TurnoSalidaDto modificarTurno(TurnoEntradaDto turnoEntradaDto, Long id) throws ResourceNotFoundException;
}
