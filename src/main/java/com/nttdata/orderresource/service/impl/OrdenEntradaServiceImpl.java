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

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
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
            throw new Exception("Error al Registrar la Orden Entrada, El proveedor no existe");
        }

        ordenEntradaRepository.save(oe);

        detalleService.enviarMensaje(oe.getDetalle());

    }

    @Override
    public List<OrdenEntrada> listarOrdenEntradaxFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return ordenEntradaRepository.findByFechaentradaBetween(fechaInicio, fechaFin);
    }

    @Override
    public OrdenEntrada obtenerOrdenEntrada(Integer id) {
        Optional<OrdenEntrada> ordenEntrada = ordenEntradaRepository.findById(id);

        return ordenEntrada.isPresent() ? ordenEntrada.get() : new OrdenEntrada();
    }
}
