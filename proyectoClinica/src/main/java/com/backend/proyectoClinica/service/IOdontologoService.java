package com.backend.proyectoClinica.service;

import com.backend.proyectoClinica.dto.entrada.OdontologoEntradaDto;
import com.backend.proyectoClinica.dto.salida.OdontologoSalidaDto;
import com.backend.proyectoClinica.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {

    OdontologoSalidaDto guardarOdontologo(OdontologoEntradaDto odontologo);

    List<OdontologoSalidaDto> listarOdontologos();

    Odontologo buscarOdontologoPorId(int id);
}
