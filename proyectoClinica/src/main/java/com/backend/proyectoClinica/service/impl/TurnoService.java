package com.backend.proyectoClinica.service.impl;

import com.backend.proyectoClinica.dto.entrada.TurnoEntradaDto;
import com.backend.proyectoClinica.dto.salida.OdontologoSalidaDto;
import com.backend.proyectoClinica.dto.salida.PacienteSalidaDto;
import com.backend.proyectoClinica.dto.salida.TurnoSalidaDto;
import com.backend.proyectoClinica.entity.Turno;
import com.backend.proyectoClinica.exceptions.BadRequestException;
import com.backend.proyectoClinica.exceptions.ResourceNotFoundException;
import com.backend.proyectoClinica.repository.TurnoRepository;
import com.backend.proyectoClinica.service.ITurnoService;
import com.backend.proyectoClinica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private TurnoRepository turnoRepository;
    private ModelMapper modelMapper;

    private final PacienteService pacienteService;
    private final OdontologoService odontologoService;

    public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, PacienteService pacienteService, OdontologoService odontologoService) {
        this.turnoRepository = turnoRepository;
        this.modelMapper = modelMapper;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @Override
    public TurnoSalidaDto guardarTurno(TurnoEntradaDto turnoEntradaDto) throws BadRequestException {
        TurnoSalidaDto turnoSalidaDto;
        PacienteSalidaDto paciente = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());
        OdontologoSalidaDto odontologo = odontologoService.buscarOdontologoPorId(turnoEntradaDto.getOdontologoId());

        String pacienteNoEnBdd = "El paciente no se encuentra en nuestra base de datos";
        String odontologoNoEnBdd = "El odontologo no se encuentra en nuestra base de datos";
        String ambosNulos = "El paciente y el odontologo no se encuentran en nuestra base de datos";

        if (paciente == null || odontologo == null) {
            if (paciente == null && odontologo == null) {
                LOGGER.error(ambosNulos);
                throw new BadRequestException(ambosNulos);
            } else if (paciente == null) {
                LOGGER.error(pacienteNoEnBdd);
                throw new BadRequestException(pacienteNoEnBdd);
            } else {
                LOGGER.error(odontologoNoEnBdd);
                throw new BadRequestException(odontologoNoEnBdd);
            }

        } else {
            Turno turnoNuevo = turnoRepository.save(modelMapper.map(turnoEntradaDto, Turno.class));
            turnoSalidaDto = entidadADtoSalida(turnoNuevo);
            LOGGER.info("Nuevo turno registrado con exito: {}", turnoSalidaDto);
        }


        return turnoSalidaDto;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnos = turnoRepository.findAll().stream()
                .map(this::entidadADtoSalida).toList();

        LOGGER.info("Listado de todos los turnos: {}", turnos);

        return turnos;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if(turnoBuscado != null){
            turnoEncontrado = entidadADtoSalida(turnoBuscado);
            LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));

        } else LOGGER.error("El id no se encuentra registrado en la base de datos");


        return turnoEncontrado;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {

        if (buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        } else {
            throw new ResourceNotFoundException("No existe registro de turno con id " + id);
        }

    }

    @Override
    public TurnoSalidaDto modificarTurno(TurnoEntradaDto turnoEntradaDto, Long id) throws ResourceNotFoundException{
        Turno turnoRecibido = modelMapper.map(turnoEntradaDto, Turno.class);
        Turno turnoParaActualizar = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if(turnoParaActualizar != null){
            turnoParaActualizar.setOdontologo(turnoRecibido.getOdontologo());
            turnoParaActualizar.setPaciente(turnoRecibido.getPaciente());
            turnoParaActualizar.setFechaYHora(turnoRecibido.getFechaYHora());
            turnoRepository.save(turnoParaActualizar);


            turnoSalidaDto = entidadADtoSalida(turnoParaActualizar);
            LOGGER.warn("El turno fue actualizado con exito: {}", JsonPrinter.toString(turnoSalidaDto));
        }else {
            LOGGER.error("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
            throw new ResourceNotFoundException("No es posible actualizar el turno con id " + id + " ya que no se encuentra en nuestra base de datos");
        }
        return turnoSalidaDto;
    }


    private PacienteSalidaDto pacienteSalidaDtoASalidaTurnoDto(Long id) {
        return pacienteService.buscarPacientePorId(id);
    }

    private OdontologoSalidaDto odontologoSalidaDtoASalidaTurnoDto(Long id) {
        return odontologoService.buscarOdontologoPorId(id);
    }

    private TurnoSalidaDto entidadADtoSalida(Turno turno) {
        TurnoSalidaDto turnoSalidaDto = modelMapper.map(turno, TurnoSalidaDto.class);
        turnoSalidaDto.setPacienteSalidaDto(pacienteSalidaDtoASalidaTurnoDto(turno.getPaciente().getId()));
        turnoSalidaDto.setOdontologoSalidaDto(odontologoSalidaDtoASalidaTurnoDto(turno.getOdontologo().getId()));
        return turnoSalidaDto;
    }
}
