package com.backend.proyectoClinica.controller;


import com.backend.proyectoClinica.dto.entrada.OdontologoEntradaDto;
import com.backend.proyectoClinica.dto.salida.OdontologoSalidaDto;
import com.backend.proyectoClinica.exceptions.ResourceNotFoundException;
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


    @GetMapping()
    public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologos(){
        return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OdontologoSalidaDto> buscarPacientePorId(@PathVariable Long id){
        return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
    }



    @PostMapping("/registrar")
    public ResponseEntity<OdontologoSalidaDto> guardarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologo){
        return new ResponseEntity<>(odontologoService.guardarOdontologo(odontologo), HttpStatus.CREATED);
    }



    @PutMapping("/actualizar/{id}")
    public ResponseEntity<OdontologoSalidaDto> actualizarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologo, @PathVariable Long id) throws ResourceNotFoundException{
        return new ResponseEntity<>(odontologoService.modificarOdontologo(odontologo, id), HttpStatus.OK);
    }


    @DeleteMapping("/eliminar")
    public ResponseEntity<?> eliminarOdontologo(@RequestParam Long id) throws ResourceNotFoundException {
        odontologoService.eliminarOdontologo(id);
        return new ResponseEntity<>("Odontologo eliminado correctamente", HttpStatus.NO_CONTENT);
    }

}
