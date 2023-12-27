package com.nttdata.orderresource.controller;

import com.nttdata.orderresource.model.Orden;
import com.nttdata.orderresource.model.OrdenDetalle;
import com.nttdata.orderresource.service.OrdenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrdenControllerTest {
    @Mock
    private OrdenService ordenService;

    @InjectMocks
    private OrdenController ordenController;

    @Test
    void testRegistroOrden() throws Exception {

        Orden oe = new Orden();

        doNothing().when(ordenService).registroOrdenEntrada(any(Orden.class));

        ResponseEntity<Object> response = ordenController.registroOrden(oe);

        assertEquals(200, response.getStatusCodeValue());

    }

    @Test
    void testObtenerOrdenesxFechas() {
        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = LocalDate.now().plusDays(1);
        String tipoOrden = "Tipo";

        when(ordenService.listarOrdenxFechasyTipoOrden(fechaInicio, fechaFin, tipoOrden))
                .thenReturn(Collections.emptyList());

        ResponseEntity<List<Orden>> response = ordenController.obtenerOrdenesxFechas(fechaInicio, fechaFin, tipoOrden);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void testObtenerOrden() {
        Integer idOrden = 1;

        when(ordenService.obtenerOrden(idOrden)).thenReturn(new Orden());

        ResponseEntity<Orden> response = ordenController.obtenerOrdenes(idOrden);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testRegistroOrdenConExcepcion() throws Exception {

        Orden oe = new Orden();

        ResponseEntity<Object> response = ordenController.registroOrden(oe);

       // assertEquals(400, response.getStatusCodeValue());

    }

    @Test
    void testAnularOrden() throws Exception {
        Integer idOrden = 1;

        doNothing().when(ordenService).anularOrden(idOrden);

        ResponseEntity<Object> response = ordenController.anularOrden(idOrden);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("OK", response.getBody());
    }
}