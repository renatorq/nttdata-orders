package com.nttdata.orderresource.service.impl;

import com.nttdata.orderresource.model.Orden;
import com.nttdata.orderresource.model.Proveedor;
import com.nttdata.orderresource.repository.ArticuloRepository;
import com.nttdata.orderresource.repository.OrdenDetalleRepository;
import com.nttdata.orderresource.repository.OrdenRepository;
import com.nttdata.orderresource.repository.ProveedorRepository;
import com.nttdata.orderresource.service.OrdenDetalleService;
import com.nttdata.orderresource.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImpl implements OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private OrdenDetalleService detalleService;

    @Autowired
    private OrdenDetalleRepository detalleRepository;

    @Transactional
    @Override
    public void registroOrdenEntrada(Orden oe) throws Exception {

        if (oe.getTipoOrden().equals("")) {
            throw new Exception("Error al Registrar la Orden, no se expecifica el tipo de Orden a registrar");
        }

        if (oe.getTipoOrden().equalsIgnoreCase("ENTRADA")) {
            Optional<Proveedor> proveedor = proveedorRepository.findById(oe.getProveedor().getIdProveedor());

            if (proveedor.isEmpty()) {
                throw new Exception("Error al Registrar la Orden de " + oe.getTipoOrden() + ", El proveedor no existe");
            }
        }

        ordenRepository.save(oe);

        detalleService.enviarMensaje(oe.getDetalle(), oe.getTipoOrden());

    }

    @Override
    public List<Orden> listarOrdenxFechasyTipoOrden(LocalDate fechaInicio, LocalDate fechaFin,String tipoOrden) {
        return ordenRepository.findByFecharegistroBetweenAndTipoOrden(fechaInicio, fechaFin,tipoOrden);
    }

    @Override
    public Orden obtenerOrden(Integer id) {
        Optional<Orden> orden = ordenRepository.findById(id);

        return orden.isPresent() ? orden.get() : new Orden();
    }

    @Override
    public Boolean anularOrden(Integer id) throws Exception {

        Optional<Orden> orden = ordenRepository.findById(id);
        if (orden.isEmpty()) {
            throw new Exception("Error al Anular Orden, Orden no existe!");
        }



        return null;
    }
}
