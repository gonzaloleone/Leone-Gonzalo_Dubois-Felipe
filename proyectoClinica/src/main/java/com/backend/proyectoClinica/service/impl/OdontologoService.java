package com.backend.proyectoClinica.service.impl;

import com.backend.proyectoClinica.dao.IDao;
import com.backend.proyectoClinica.dto.entrada.OdontologoEntradaDto;
import com.backend.proyectoClinica.dto.salida.OdontologoSalidaDto;
import com.backend.proyectoClinica.entity.Odontologo;
import com.backend.proyectoClinica.service.IOdontologoService;
import com.backend.proyectoClinica.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OdontologoService implements IOdontologoService {

    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

    private IDao<Odontologo> odontologoIDao;

    private ModelMapper modelMapper;

    public OdontologoService(IDao<Odontologo> odontologoIDao, ModelMapper modelMapper) {
        this.odontologoIDao = odontologoIDao;
        this.modelMapper = modelMapper;
    }


    @Override
    public OdontologoSalidaDto guardarOdontologo(OdontologoEntradaDto odontologo){
        //Logueamos lo que recibimos
        LOGGER.info("OdontologoEntradaDto: {}", JsonPrinter.toString(odontologo));
        //convertimos mediante el mapper de dtoEntrada a entidad
        Odontologo odontologoEntidad = modelMapper.map(odontologo, Odontologo.class);
        //mandamos a persistir a la capa dao y obtenemos una entidad con ID
        Odontologo odontologoEntidaConId = odontologoIDao.guardar(odontologoEntidad);
        //transformamos la entidad obtenida en salidaDto
        OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoEntidaConId, OdontologoSalidaDto.class);
        //Logueamos lo que sale
        LOGGER.info("OdontologoSalidaDto: {}",  JsonPrinter.toString(odontologoSalidaDto));
        return odontologoSalidaDto;
    }

    @Override
    public List<OdontologoSalidaDto> listarOdontologos(){
        List<OdontologoSalidaDto> odontologoSalidaDto =  odontologoIDao.listarTodos()
                .stream()
                .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
                .toList();

        LOGGER.info("Listado de todos los odontologos: {}", JsonPrinter.toString(odontologoSalidaDto));
        return odontologoSalidaDto;
    }

    @Override
    public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
        Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
        OdontologoSalidaDto odontologoEncontrado = null;

        if(odontologoBuscado != null){
            odontologoEncontrado = modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
            LOGGER.info("Odontologo encontrado: {}", JsonPrinter.toString(odontologoEncontrado));

        } else LOGGER.error("El id no se encuentra registrado en la base de datos");


        return odontologoEncontrado;
    }


}