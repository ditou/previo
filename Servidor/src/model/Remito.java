package model;

import dao.RemitoDAO;
import dto.RemitoDTO;
import dto.RemitoItemDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Remito {

    private Integer remitoId;
    private Cliente cliente;
    private List<RemitoItem> items;
    private LocalDate fecha;

    public Remito() {
        items = new ArrayList<>();
    }

    public Remito(RemitoDTO remito) {

        this();
        this.remitoId = remito.getRemitoId();
        this.cliente = new Cliente(remito.getCliente());
        this.fecha = remito.getFecha();
        for (RemitoItemDTO item : remito.getItems()) {
            this.items.add(new RemitoItem(item));
        }
    }

    public RemitoDTO toDTO() {
        RemitoDTO remito = new RemitoDTO();
        remito.setCliente(this.getCliente().toDTO());
        remito.setFecha(this.getFecha());
        remito.setRemitoId(this.remitoId);
        for (RemitoItem item : this.items) {
            remito.getItems().add(item.toDTO());
        }

        return remito;
    }

    public Integer getRemitoId() {
        return remitoId;
    }

    public void setRemitoId(Integer remitoId) {
        this.remitoId = remitoId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<RemitoItem> getItems() {
        return items;
    }

    public void setItems(List<RemitoItem> items) {
        this.items = items;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void remitir(Pedido pedido) {
        this.cliente = pedido.getCliente();
        this.fecha = LocalDate.now();
        for (PedidoItem item : pedido.getItems()) {
            RemitoItem ritem = new RemitoItem();
            ritem.setArticulo(item.getArticulo());
            ritem.setCantidad(item.getCantidad());
            this.items.add(ritem);
        }
        save();
    }

    public Remito save() {

        return RemitoDAO.getInstancia().save(this);
    }
}