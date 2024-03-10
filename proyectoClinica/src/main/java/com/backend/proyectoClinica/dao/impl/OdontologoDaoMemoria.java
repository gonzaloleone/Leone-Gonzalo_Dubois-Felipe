package com.backend.proyectoClinica.dao.impl;



import com.backend.proyectoClinica.dao.IDao;
import com.backend.proyectoClinica.entity.Odontologo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class OdontologoDaoMemoria implements IDao<Odontologo> {
    private final Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoMemoria.class);
    private List<Odontologo> odontologoRepositorio;

    public OdontologoDaoMemoria(List<Odontologo> odontologoRepositorio) {
        this.odontologoRepositorio = odontologoRepositorio;
    }

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        int id = odontologoRepositorio.size() + 1;
        Odontologo odontologoGuardado = new Odontologo(id, odontologo.getNumeroMatricula(), odontologo.getNombre(), odontologo.getApellido());

        odontologoRepositorio.add(odontologo);
        LOGGER.info("Odontologo guardado: " + odontologoGuardado);
        return odontologoGuardado;
    }

    @Override
    public List<Odontologo> listarTodos() {
        LOGGER.info("Listando todos los odontologos: " + odontologoRepositorio);
        return odontologoRepositorio;
    }

    @Override
    public Odontologo buscarPorId(int id){
        return null;
    }
}
