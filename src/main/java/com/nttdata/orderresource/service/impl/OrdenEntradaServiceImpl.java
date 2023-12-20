package com.nttdata.orderresource.service.impl;

import com.nttdata.orderresource.model.Articulo;
import com.nttdata.orderresource.model.OrdenEntrada;
import com.nttdata.orderresource.model.OrdenEntradaDetalle;
import com.nttdata.orderresource.model.Proveedor;
import com.nttdata.orderresource.repository.ArticuloRepository;
import com.nttdata.orderresource.repository.OrdenEntradaDetalleRepository;
import com.nttdata.orderresource.repository.OrdenEntradaRepository;
import com.nttdata.orderresource.repository.ProveedorRepository;
import com.nttdata.orderresource.service.OrdenEntradaDetalleService;
import com.nttdata.orderresource.service.OrdenEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrdenEntradaServiceImpl implements OrdenEntradaService {

    @Autowired
    private OrdenEntradaRepository ordenEntradaRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private OrdenEntradaDetalleService detalleService;

    @Autowired
    private OrdenEntradaDetalleRepository detalleRepository;

    @Transactional
    @Override
    public void registroOrdenEntrada(OrdenEntrada oe) throws Exception {

        Optional<Proveedor> proveedor = proveedorRepository.findById(oe.getProveedor().getIdProveedor());

        if (proveedor.isEmpty()) {
            System.out.println("EL proveedor no existe!");
            throw new Exception("Error al Registrar la Orden Entrada, El proveedor no existe");
        }

        ordenEntradaRepository.save(oe);

        detalleService.enviarMensaje(oe.getDetalle());

    }
}
