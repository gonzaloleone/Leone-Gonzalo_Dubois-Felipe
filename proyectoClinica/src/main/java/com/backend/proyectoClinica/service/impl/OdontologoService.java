package com.backend.proyectoClinica.service.impl;

import com.backend.proyectoClinica.dao.IDao;
import com.backend.proyectoClinica.dto.entrada.OdontologoEntradaDto;
import com.backend.proyectoClinica.dto.salida.OdontologoSalidaDto;
import com.backend.proyectoClinica.entity.Odontologo;
import com.backend.proyectoClinica.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OdontologoService implements IOdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public OdontologoSalidaDto guardarOdontologo(OdontologoEntradaDto odontologo){
        return odontologoIDao.guardar(odontologo);
    }

    public List<OdontologoSalidaDto> listarOdontologos(){
        return odontologoIDao.listarTodos();
    }

    public Odontologo buscarOdontologoPorId(int id) {
        return odontologoIDao.buscarPorId(id);
    }


}
