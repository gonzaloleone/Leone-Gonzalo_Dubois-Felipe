package com.backend.proyectoClinica.controller;


import com.backend.proyectoClinica.dto.entrada.OdontologoEntradaDto;
import com.backend.proyectoClinica.dto.salida.OdontologoSalidaDto;
import com.backend.proyectoClinica.entity.Odontologo;
import com.backend.proyectoClinica.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private IOdontologoService odontologoService;

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    //GET
    @GetMapping()
    public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologos(){
        return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);
    }

    @GetMapping("/{id}") //localhost:8080/pacientes/x
    public ResponseEntity<OdontologoSalidaDto> buscarPacientePorId(@PathVariable int id){
       return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    //}


    //POST
    @PostMapping("/registrar")
    public ResponseEntity<OdontologoSalidaDto> guardarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologo){
        return new ResponseEntity<>(odontologoService.guardarOdontologo(odontologo), HttpStatus.CREATED);
    }


    //PUT
    @PutMapping("/actualizar/{id}")//localhost:8080/pacientes/actualizar/x
    public ResponseEntity<OdontologoSalidaDto> actualizarPaciente(@RequestBody @Valid OdontologoEntradaDto odontologo){
        return null; //pacienteService.actualizar(paciente);
    }

    //DELETE
    @DeleteMapping("/eliminar")//localhost:8080/pacientes/eliminar?id=x
    public ResponseEntity<?> eliminarPaciente(@RequestParam int id){
        //pacienteService.eliminarPaciente(id);
        return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
    }

}
