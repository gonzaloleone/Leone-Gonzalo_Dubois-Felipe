package com.backend.proyectoClinica.service.impl;

import com.backend.proyectoClinica.dao.IDao;
import com.backend.proyectoClinica.dto.entrada.TurnoEntradaDto;
import com.backend.proyectoClinica.dto.salida.TurnoSalidaDto;
import com.backend.proyectoClinica.entity.Turno;
import com.backend.proyectoClinica.service.ITurnoService;
import com.backend.proyectoClinica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

    private IDao<Turno> turnoIDao;

    private ModelMapper modelMapper;

    public TurnoService(IDao<Turno> turnoIDao, ModelMapper modelMapper) {
        this.turnoIDao = turnoIDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoSalidaDto guardarTurno(TurnoEntradaDto turno) {
        //Logueamos lo que recibimos
        LOGGER.info("TurnoEntradaDto: {}", JsonPrinter.toString(turno));
        //convertimos mediante el mapper de dtoEntrada a entidad
        Turno turnoEntidad = modelMapper.map(turno, Turno.class);
        //mandamos a persistir a la capa dao y obtenemos una entidad con ID
        Turno pacienteEntidaConId = turnoIDao.guardar(turnoEntidad);
        //transformamos la entidad obtenida en salidaDto
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(pacienteEntidaConId, TurnoSalidaDto.class);
        //Logueamos lo que sale
        LOGGER.info("TurnoSalidaDto: {}",  JsonPrinter.toString(turnoSalidaDto));
        return turnoSalidaDto;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnosSalidaDto =  turnoIDao.listarTodos()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();

        LOGGER.info("Listado de todos los pacientes: {}", JsonPrinter.toString(turnosSalidaDto));
        return turnosSalidaDto;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = pacienteRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if(turnoBuscado != null){
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));

        } else LOGGER.error("El id no se encuentra registrado en la base de datos");


        return turnoEncontrado;
    }
}
