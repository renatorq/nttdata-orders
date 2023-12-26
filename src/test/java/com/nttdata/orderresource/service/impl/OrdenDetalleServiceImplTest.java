package com.nttdata.orderresource.service.impl;

import com.nttdata.orderresource.dto.DetalleOrdenDTO;
import com.nttdata.orderresource.kafka.KafkaProducer;
import com.nttdata.orderresource.model.Articulo;
import com.nttdata.orderresource.model.Orden;
import com.nttdata.orderresource.model.OrdenDetalle;
import com.nttdata.orderresource.repository.OrdenDetalleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrdenDetalleServiceImplTest {
    @Mock
    private OrdenDetalleRepository detalleConsultaRepository;
    @Mock
    private KafkaProducer kafkaProducer;

    @InjectMocks
    private OrdenDetalleServiceImpl ordenDetalleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void enviarMensaje() throws Exception {
        Articulo articulo = new Articulo();
        Orden orden = new Orden();

        orden.setIdOrden(123);
        articulo.setIdArticulo(123);

        OrdenDetalle detalle = new OrdenDetalle(1,orden,articulo,20,new BigDecimal(20.00));

        List<OrdenDetalle> detalles = Arrays.asList(detalle,detalle);

        ordenDetalleService.enviarMensaje(detalles, "TIPO_ORDEN", "TIPO_OPERACION");

         verify(kafkaProducer, times(detalles.size())).sendMessage(any(DetalleOrdenDTO.class));
    }

    @Test
    void testEnviarMensajeConExcepcion() {
        Articulo articulo = new Articulo();
        Orden orden = new Orden();

        OrdenDetalle detalle = new OrdenDetalle(1,orden,articulo,20,new BigDecimal(20.00));

        List<OrdenDetalle> detalles = Collections.singletonList(detalle);

        doThrow(new RuntimeException("Error en Kafka")).when(kafkaProducer).sendMessage(any(DetalleOrdenDTO.class));

        assertThrows(Exception.class, () -> ordenDetalleService.enviarMensaje(detalles, "TIPO_ORDEN", "TIPO_OPERACION"));
    }


}
