package com.backend.test;

import com.backend.dao.impl.OdontologoDaoH2;
import com.backend.dao.impl.OdontologoDaoMemoria;
import com.backend.entity.Odontologo;
import com.backend.service.OdontologoService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {

    private OdontologoService odontologoService;

    @Test
    void deberiaInsertarYRetornarIdDeOdontologoEnH2(){
        odontologoService = new OdontologoService(new OdontologoDaoH2());
        Odontologo odontologoARegistrar = new Odontologo(123, "Gonzalo", "Leone");

        Odontologo odontologoRegistrado = odontologoService.guardarOdontologo(odontologoARegistrar);

        assertNotNull(odontologoRegistrado.getId());
    }

    @Test
    void deberiaInsertarYRetornarIdDeOdontologoEnMemoria(){
        odontologoService = new OdontologoService(new OdontologoDaoMemoria(new ArrayList<>()));
        Odontologo odontologoARegistrar = new Odontologo(123, "Gonzalo", "Leone");

        Odontologo odontologoRegistrado = odontologoService.guardarOdontologo(odontologoARegistrar);

        assertNotNull(odontologoRegistrado.getId());
    }

}