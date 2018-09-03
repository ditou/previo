package entity;

import javax.persistence.*;

@Entity
@Table(name = "FacturaItem")
public class FacturaItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer facturaItemId;
    @ManyToOne
    private ArticuloEntity articulo;
    private int cantidad;
    private java.math.BigDecimal precio;

    public FacturaItemEntity() {

    }

    public Integer getFacturaItemId() {
        return facturaItemId;
    }

    public void setFacturaItemId(Integer facturaItemId) {
        this.facturaItemId = facturaItemId;
    }

    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public java.math.BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(java.math.BigDecimal precio) {
        this.precio = precio;
    }

}
