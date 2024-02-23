package com.backend.dao.impl;

import com.backend.dao.IDao;
import com.backend.entity.Odontologo;
import org.apache.log4j.Logger;

import java.util.List;

public class OdontologoDaoMemoria implements IDao<Odontologo> {
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoMemoria.class);
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
        return null;
    }
}
