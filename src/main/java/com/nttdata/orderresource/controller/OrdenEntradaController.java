package com.nttdata.orderresource.controller;

import com.nttdata.orderresource.model.OrdenEntrada;
import com.nttdata.orderresource.service.OrdenEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ordenEntrada")
public class OrdenEntradaController {
    @Autowired
    private OrdenEntradaService service;

    @PostMapping("/registro")
    public ResponseEntity<Object> registroOrdenEntrada(@RequestBody OrdenEntrada oe) throws Exception {
        try {
            service.registroOrdenEntrada(oe);
            return ResponseEntity.ok(oe);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
