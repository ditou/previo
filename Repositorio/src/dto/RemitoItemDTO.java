package dto;

import java.io.Serializable;

public class RemitoItemDTO implements Serializable {

    private static final long serialVersionUID = 7769765575578769874L;

    private Integer remitoItemId;
    private int cantidad;
    private ArticuloDTO articulo;


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

    public ArticuloDTO getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloDTO articulo) {
        this.articulo = articulo;
    }


}
