package com.backend.proyectoClinica.controller;


import com.backend.proyectoClinica.dto.entrada.PacienteEntradaDto;
import com.backend.proyectoClinica.dto.entrada.TurnoEntradaDto;
import com.backend.proyectoClinica.dto.salida.PacienteSalidaDto;
import com.backend.proyectoClinica.dto.salida.TurnoSalidaDto;
import com.backend.proyectoClinica.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    //GET
    @GetMapping()
    public ResponseEntity<List<TurnoSalidaDto>> listarTurnos(){
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }

    @GetMapping("/{id}") //localhost:8080/turnos/x
    public ResponseEntity<TurnoSalidaDto> buscarTurnoPorId(@PathVariable Long id){
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
    }


    //POST
    @PostMapping("/guardar")
    public ResponseEntity<TurnoSalidaDto> guardarTurno(@RequestBody @Valid TurnoEntradaDto turno){
        return new ResponseEntity<>(turnoService.guardarTurno(turno), HttpStatus.CREATED);
    }


    //PUT
    @PutMapping("/actualizar/{id}")//localhost:8080/turnos/actualizar/x
    public ResponseEntity<TurnoSalidaDto> actualizarTurno(@RequestBody @Valid TurnoEntradaDto turno){
        return null; //turnoService.actualizar(turno);
    }

    //DELETE
    @DeleteMapping("/eliminar")//localhost:8080/turnos/eliminar?id=x
    public ResponseEntity<?> eliminarTurno(@RequestParam Long id){
        //turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}
