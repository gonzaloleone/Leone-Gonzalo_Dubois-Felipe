package com.backend.proyectoClinica.repository;

import com.backend.proyectoClinica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public class PacienteRepository extends JpaRepository<Paciente, Long> {

    //@Query("SELECT Paciente p FROM Paciente WHERE p.dni = ?1") HQL
    //@Query(value = "SELECT * FROM PACIENTES WHERE DNI = ?1", nativeQuery = true) SQL
    Paciente findByDni(int dni);

    Paciente findByDniAndNombre(int dni, String nombre);


}
