package com.backend.proyectoClinica.service;

import com.backend.proyectoClinica.dto.entrada.TurnoEntradaDto;
import com.backend.proyectoClinica.dto.salida.TurnoSalidaDto;

import java.util.List;

public interface ITurnoService {

    TurnoSalidaDto guardarTurno(TurnoEntradaDto turno);

    List<TurnoSalidaDto> listarTurnos();

    TurnoSalidaDto buscarTurnoPorId(Long id);
}
