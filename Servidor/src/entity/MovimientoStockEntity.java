package entity;

import model.TipoMovimiento;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "MovimientoStock")
public class MovimientoStockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer movimientoStockId;
    @ManyToOne
    private ArticuloEntity articulo;
    private int cantidad;
    private String encargado;
    private String destino;
    private String autorizadoPor;
    private LocalDate fecha;
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipoMovimiento;

    public MovimientoStockEntity() {

    }

    public Integer getMovimientoStockId() {
        return movimientoStockId;
    }

    public void setMovimientoStockId(Integer movimientoStockId) {
        this.movimientoStockId = movimientoStockId;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }


    public String getAutorizadoPor() {
        return autorizadoPor;
    }

    public void setAutorizadoPor(String autorizadoPor) {
        this.autorizadoPor = autorizadoPor;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }


}
