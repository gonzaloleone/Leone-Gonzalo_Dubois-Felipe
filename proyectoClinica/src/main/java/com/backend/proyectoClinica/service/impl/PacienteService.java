package com.backend.proyectoClinica.service.impl;

import com.backend.proyectoClinica.dao.IDao;
import com.backend.proyectoClinica.dto.entrada.PacienteEntradaDto;
import com.backend.proyectoClinica.dto.salida.PacienteSalidaDto;
import com.backend.proyectoClinica.entity.Paciente;
import com.backend.proyectoClinica.service.IPacienteService;

import java.util.List;

public class PacienteService implements IPacienteService {

    private IDao<Paciente> pacienteIDao;

    public PacienteService(IDao<Paciente> pacienteIDao) {
        this.pacienteIDao = pacienteIDao;
    }
    @Override
    public PacienteSalidaDto guardarPaciente(PacienteEntradaDto paciente) {
        return pacienteIDao.guardar(paciente);
    }

    @Override
    public List<PacienteSalidaDto> listarPacientes() {
        return pacienteIDao.listarTodos();
    }

    @Override
    public PacienteSalidaDto buscarPacientePorId(int id) {
        return pacienteIDao.buscarPorId(id);
    }
}
