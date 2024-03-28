package com.backend.proyectoClinica.controller;

import com.backend.proyectoClinica.dto.entrada.TurnoEntradaDto;
import com.backend.proyectoClinica.dto.salida.TurnoSalidaDto;
import com.backend.proyectoClinica.exceptions.BadRequestException;
import com.backend.proyectoClinica.exceptions.ResourceNotFoundException;
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

    public TurnoController(ITurnoService turnoService){
        this.turnoService = turnoService;
    }


    @GetMapping()
    public ResponseEntity<List<TurnoSalidaDto>> listarTurnos(){
        return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TurnoSalidaDto> buscarTurnoPorId(@PathVariable Long id){
        return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
    }



    @PostMapping("/registrar")
    public ResponseEntity<TurnoSalidaDto> guardarTurno(@RequestBody @Valid TurnoEntradaDto turno) throws BadRequestException {
        return new ResponseEntity<>(turnoService.guardarTurno(turno), HttpStatus.CREATED);
    }



    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TurnoSalidaDto> actualizarTurno(@RequestBody @Valid TurnoEntradaDto turno, @PathVariable Long id) throws ResourceNotFoundException{
        return new ResponseEntity<>(turnoService.modificarTurno(turno, id), HttpStatus.OK);
    }


    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarTurno(@RequestParam Long id) throws ResourceNotFoundException{
        turnoService.eliminarTurno(id);
        return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.NO_CONTENT);
    }
}
