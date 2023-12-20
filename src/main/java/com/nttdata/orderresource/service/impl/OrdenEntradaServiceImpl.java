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

        OrdenEntrada ordenEntradaRegistrada = ordenEntradaRepository.save(oe);

        for (OrdenEntradaDetalle detalle : oe.getDetalle()) {

            Articulo a = articuloRepository.findById(detalle.getArticulo().getIdArticulo()).orElse(null);
            if (a == null) continue;

            detalle.setOrdenEntrada(ordenEntradaRegistrada);
            detalleService.guardarDetalleOrden(detalle);
        }

    }


//    public void registroOrdenEntrada(OrdenEntrada oe) throws Exception {
//        // Guardar la orden de entrada primero para obtener su ID generado
//        OrdenEntrada nuevaOrdenEntrada = ordenEntradaRepository.save(oe);
//
//        // Asociar la orden de entrada con los detalles y guardarlos
//        if (oe.getDetalle() != null) {
//            for (OrdenEntradaDetalle detalle : oe.getDetalle()) {
//                // Asignar la orden de entrada al detalle
//                detalle.setOrdenEntrada(nuevaOrdenEntrada);
//
//                // Asegurarse de que el artículo no sea nulo
//                if (detalle.getArticulo() != null) {
//                    // Guardar el detalle con la relación a la orden de entrada y al artículo
//                    detalleRepository.save(detalle);
//                }
//                // Puedes manejar el caso donde el artículo es nulo según tus requisitos
//            }
//        }
//
//    }

}
