package com.nttdata.orderresource.service.impl;

import com.nttdata.orderresource.model.Orden;
import com.nttdata.orderresource.model.Proveedor;
import com.nttdata.orderresource.repository.ArticuloRepository;
import com.nttdata.orderresource.repository.OrdenDetalleRepository;
import com.nttdata.orderresource.repository.OrdenRepository;
import com.nttdata.orderresource.repository.ProveedorRepository;
import com.nttdata.orderresource.service.OrdenEntradaDetalleService;
import com.nttdata.orderresource.service.OrdenEntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrdenServiceImpl implements OrdenEntradaService {

    @Autowired
    private OrdenRepository ordenRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;
    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private OrdenEntradaDetalleService detalleService;

    @Autowired
    private OrdenDetalleRepository detalleRepository;

    @Transactional
    @Override
    public void registroOrdenEntrada(Orden oe) throws Exception {

        Optional<Proveedor> proveedor = proveedorRepository.findById(oe.getProveedor().getIdProveedor());

        if (proveedor.isEmpty()) {
            throw new Exception("Error al Registrar la Orden Entrada, El proveedor no existe");
        }

        ordenRepository.save(oe);

        detalleService.enviarMensaje(oe.getDetalle(), oe.getTipoOrden());

    }

    @Override
    public List<Orden> listarOrdenEntradaxFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return ordenRepository.findByFecharegistroBetween(fechaInicio, fechaFin);
    }

    @Override
    public Orden obtenerOrdenEntrada(Integer id) {
        Optional<Orden> ordenEntrada = ordenRepository.findById(id);

        return ordenEntrada.isPresent() ? ordenEntrada.get() : new Orden();
    }
}
