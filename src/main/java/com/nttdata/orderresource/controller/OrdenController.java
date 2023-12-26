package com.nttdata.orderresource.controller;

import com.nttdata.orderresource.model.Orden;
import com.nttdata.orderresource.model.OrdenDetalle;
import com.nttdata.orderresource.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orden")
public class OrdenController {
    @Autowired
    private OrdenService service;

    @PostMapping("/registro")
    public ResponseEntity<Object> registroOrden(@RequestBody Orden oe) throws Exception {
        try {

            for (OrdenDetalle detalle : oe.getDetalle()) {
                detalle.setOrden(oe);
            }

            service.registroOrdenEntrada(oe);
            return ResponseEntity.ok().body("Orden de " + oe.getTipoOrden() + " registrada con ID: " + oe.getIdOrden());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/filtrarxfecha")
    public ResponseEntity<List<Orden>> obtenerOrdenesxFechas(
            @RequestParam("fechaInicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaFin,
            @RequestParam("tipoOrden") String tipoOrden) {

        return ResponseEntity.ok(service.listarOrdenxFechasyTipoOrden(fechaInicio, fechaFin, tipoOrden));
    }

    @GetMapping("/buscar")
    public ResponseEntity<Orden> obtenerOrdenes(
            @RequestParam("id") Integer id) {

        return ResponseEntity.ok(service.obtenerOrden(id));
    }

    public ResponseEntity<Object> anularOrden(@RequestParam("id") Integer id) throws Exception {
        service.anularOrden(id);
        return ResponseEntity.ok("OK");
    }

}
