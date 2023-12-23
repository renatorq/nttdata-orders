package com.nttdata.orderresource.controller;

import com.nttdata.orderresource.model.OrdenEntrada;
import com.nttdata.orderresource.model.OrdenEntradaDetalle;
import com.nttdata.orderresource.service.OrdenEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ordenEntrada")
public class OrdenEntradaController {
    @Autowired
    private OrdenEntradaService service;

    @PostMapping("/registro")
    public ResponseEntity<Object> registroOrdenEntrada(@RequestBody OrdenEntrada oe) throws Exception {
        try {

            for (OrdenEntradaDetalle detalle : oe.getDetalle()) {
                detalle.setOrdenEntrada(oe);
            }

            service.registroOrdenEntrada(oe);
            return ResponseEntity.ok().body("Orden de entrada registrada con ID: " + oe.getIdOrdenEntrada());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/filtrarxfecha")
    public ResponseEntity<List<OrdenEntrada>> obtenerOrdenesxFechas(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin) {

        return ResponseEntity.ok(service.listarOrdenEntradaxFechas(fechaInicio, fechaFin));
    }

    @GetMapping("/buscar")
    public ResponseEntity<OrdenEntrada> obtenerOrdenes(
            @RequestParam("id") Integer id) {

        return ResponseEntity.ok(service.obtenerOrdenEntrada(id));
    }

}
