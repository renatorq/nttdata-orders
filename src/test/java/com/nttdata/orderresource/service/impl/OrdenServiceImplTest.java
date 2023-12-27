package com.nttdata.orderresource.service.impl;

import com.nttdata.orderresource.model.Orden;
import com.nttdata.orderresource.model.OrdenDetalle;
import com.nttdata.orderresource.model.Proveedor;
import com.nttdata.orderresource.repository.OrdenRepository;
import com.nttdata.orderresource.repository.ProveedorRepository;
import com.nttdata.orderresource.service.OrdenDetalleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class OrdenServiceImplTest {
    @Mock
    private OrdenRepository ordenRepository;
    @Mock
    private ProveedorRepository proveedorRepository;
    @Mock
    private OrdenDetalleService detalleService;

    @InjectMocks
    private OrdenServiceImpl ordenService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testRegistroOrdenEntrada() throws Exception {

        Orden oe = mock(Orden.class,RETURNS_MOCKS);

        when(proveedorRepository.findById(any())).thenReturn(Optional.ofNullable(mock(Proveedor.class, RETURNS_MOCKS)));
        when(oe.getTipoOrden()).thenReturn("ENTRADA");
        when(oe.getProveedor()).thenReturn(mock(Proveedor.class,RETURNS_MOCKS));
        List<OrdenDetalle> ordenDetalleList = Arrays.asList(mock(OrdenDetalle.class,RETURNS_MOCKS), mock(OrdenDetalle.class,RETURNS_MOCKS));
        when(oe.getDetalle()).thenReturn(ordenDetalleList);

        ordenService.registroOrdenEntrada(oe);

        verify(ordenRepository).save(oe);

    }

    @Test
    void testRegistroOrdenEntradaConTipoOrdenVacio() {

        Orden ordenVacia = new Orden();
        ordenVacia.setTipoOrden("");

        Exception excepcion = assertThrows(Exception.class, () -> ordenService.registroOrdenEntrada(ordenVacia));
        assertEquals("Error al Registrar la Orden, no se expecifica el tipo de Orden a registrar", excepcion.getMessage());
    }

    @Test
    void testRegistroOrdenEntradaConExcepcion() {
        Orden oe = new Orden();
        oe.setTipoOrden("ENTRADA");
        oe.setProveedor(new Proveedor());
        oe.getProveedor().setIdProveedor(1);

        when(proveedorRepository.findById(1)).thenReturn(Optional.empty());

        Exception excepcion = assertThrows(Exception.class, () -> ordenService.registroOrdenEntrada(oe));
        assertEquals("Error al Registrar la Orden de ENTRADA, El proveedor no existe", excepcion.getMessage());
    }

    @Test
    void testListarOrdenxFechasyTipoOrden() {

        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = LocalDate.now();
        String tipoOrden = "ENTRADA";
        when(ordenRepository.findByFecharegistroBetweenAndTipoOrden(fechaInicio, fechaFin, tipoOrden))
                .thenReturn(Collections.emptyList());

        ordenService.listarOrdenxFechasyTipoOrden(fechaInicio, fechaFin, tipoOrden);

        verify(ordenRepository).findByFecharegistroBetweenAndTipoOrden(fechaInicio, fechaFin, tipoOrden);
    }

    @Test
    void testObtenerOrden() {

        int idOrden = 1;
        when(ordenRepository.findById(idOrden)).thenReturn(Optional.of(new Orden()));

        Orden resultado = ordenService.obtenerOrden(idOrden);

        assertNotNull(resultado);
    }

    @Test
    void testAnularOrdenConOrdenInexistente() {

        Integer idOrdenInexistente = 1;
        when(ordenRepository.findById(idOrdenInexistente)).thenReturn(Optional.empty());

        Exception excepcion = assertThrows(Exception.class, () -> ordenService.anularOrden(idOrdenInexistente));
        assertEquals("Error al Anular Orden, Orden no existe!", excepcion.getMessage());
    }

    @Test
    void testAnularOrden() throws Exception {
        int idOrden = 1;
        Orden orden = new Orden();
        orden.setIdOrden(idOrden);

        when(ordenRepository.findById(idOrden)).thenReturn(Optional.of(orden));

        ordenService.anularOrden(idOrden);

        verify(ordenRepository).findById(idOrden);

    }
}