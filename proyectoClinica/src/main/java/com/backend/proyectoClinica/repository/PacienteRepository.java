package com.backend.proyectoClinica.repository;

import com.backend.proyectoClinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    Paciente findByDni(int dni);

    Paciente findByDniAndNombre(int dni, String nombre);
}
