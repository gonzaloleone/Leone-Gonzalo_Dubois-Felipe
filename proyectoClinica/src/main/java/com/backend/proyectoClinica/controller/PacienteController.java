package com.backend.proyectoClinica.controller;


import com.backend.proyectoClinica.dto.entrada.PacienteEntradaDto;
import com.backend.proyectoClinica.dto.salida.PacienteSalidaDto;
import com.backend.proyectoClinica.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    //GET
    @GetMapping()
    public ResponseEntity<List<PacienteSalidaDto>> listarPacientes(){
        return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
    }

    @GetMapping("/{id}") //localhost:8080/pacientes/x
    public ResponseEntity<PacienteSalidaDto> buscarPacientePorId(@PathVariable Long id){
        return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
    }


    //POST
    @PostMapping("/guardar")
    public ResponseEntity<PacienteSalidaDto> guardarPaciente(@RequestBody @Valid PacienteEntradaDto paciente){
        return new ResponseEntity<>(pacienteService.guardarPaciente(paciente), HttpStatus.CREATED);
    }


    //PUT
    @PutMapping("/actualizar/{id}")//localhost:8080/pacientes/actualizar/x
    public ResponseEntity<PacienteSalidaDto> actualizarPaciente(@RequestBody @Valid PacienteEntradaDto paciente){
        return null; //pacienteService.actualizar(paciente);
    }

    //DELETE
    @DeleteMapping("/eliminar")//localhost:8080/pacientes/eliminar?id=x
    public ResponseEntity<?> eliminarPaciente(@RequestParam Long id){
        //pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}