package model;

import dto.RemitoItemDTO;

public class RemitoItem {

    private Integer remitoItemId;
    private int cantidad;
    private Articulo articulo;

    public RemitoItem() {

    }

    public RemitoItem(RemitoItemDTO item) {
        this.remitoItemId = item.getRemitoItemId();
        this.cantidad = item.getCantidad();
        this.articulo = new Articulo(item.getArticulo());
    }

    public Integer getRemitoItemId() {
        return remitoItemId;
    }

    public void setRemitoItemId(Integer remitoItemId) {
        this.remitoItemId = remitoItemId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public RemitoItemDTO toDTO() {
        RemitoItemDTO item = new RemitoItemDTO();
        item.setRemitoItemId(remitoItemId);
        item.setCantidad(cantidad);
        item.setArticulo(this.articulo.toDTO());

        return item;
    }

}
