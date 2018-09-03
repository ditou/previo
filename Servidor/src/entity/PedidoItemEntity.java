package entity;

import javax.persistence.*;

@Entity
@Table(name = "PedidoItem")
public class PedidoItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer pedidoItemId;
    @ManyToOne
    private ArticuloEntity articulo;
    private int cantidad;
    private java.math.BigDecimal totalBruto;


    public PedidoItemEntity() {

    }

    public Integer getPedidoItemId() {
        return pedidoItemId;
    }

    public void setPedidoItemId(Integer pedidoItemId) {
        this.pedidoItemId = pedidoItemId;
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

    public java.math.BigDecimal getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(java.math.BigDecimal totalBruto) {
        this.totalBruto = totalBruto;
    }


}
