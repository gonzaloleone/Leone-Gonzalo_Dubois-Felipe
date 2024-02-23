package com.backend.test;

import com.backend.dao.impl.OdontologoDaoH2;
import com.backend.dao.impl.OdontologoDaoMemoria;
import com.backend.entity.Odontologo;
import com.backend.service.OdontologoService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    private OdontologoService odontologoService;

    @Test
    void deberiaRegistrarUnOdontologoConIdEnH2(){
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologoARegistrar = new Odontologo(123, "Gonzalo", "Leone");

        Odontologo odontologoRegistrado = odontologoService.guardarOdontologo(odontologoARegistrar);

        assertNotNull(odontologoRegistrado.getId());
    }

    @Test
    void deberiaRegistrarUnOdontologoConIdEnMemoria(){
        odontologoService = new OdontologoService(new OdontologoDaoMemoria(new ArrayList<>()));
        Odontologo odontologoARegistrar = new Odontologo(321, "Felipe", "Dubois");

        Odontologo odontologoRegistrado = odontologoService.guardarOdontologo(odontologoARegistrar);

        assertNotNull(odontologoRegistrado.getId());
    }

    @Test
    void deberiaCrearUnaListaDeOdontologosRegistradosEnH2(){
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        List<Odontologo> listaDeOdontologos = odontologoService.listarOdontologos();
        assertNotNull(listaDeOdontologos);
    }

    @Test
    void deberiaCrearUnaListaDeOdontologosRegistradosEnMemoria(){
        odontologoService = new OdontologoService(new OdontologoDaoMemoria(new ArrayList<>()));
        List<Odontologo> listaDeOdontologos = odontologoService.listarOdontologos();
        assertNotNull(listaDeOdontologos);
    }











}