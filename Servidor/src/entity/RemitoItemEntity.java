package entity;

import javax.persistence.*;

@Entity
@Table(name = "RemitoItem")
public class RemitoItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer remitoItemId;
    private int cantidad;
    @ManyToOne
    private ArticuloEntity articulo;

    public RemitoItemEntity() {

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

    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }

}
