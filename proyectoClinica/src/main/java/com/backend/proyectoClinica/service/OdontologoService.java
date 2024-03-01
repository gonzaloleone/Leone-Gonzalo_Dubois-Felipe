package com.backend.proyectoClinica.service;

import com.backend.proyectoClinica.dao.IDao;
import com.backend.proyectoClinica.entity.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class OdontologoService {

    private IDao<Odontologo> odontologoIDao;

    public OdontologoService(IDao<Odontologo> odontologoIDao) {
        this.odontologoIDao = odontologoIDao;
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoIDao.guardar(odontologo);
    }

    public List<Odontologo> listarOdontologos(){
        return odontologoIDao.listarTodos();

    }


}
