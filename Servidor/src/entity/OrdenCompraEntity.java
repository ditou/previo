package entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OrdenCompra")
public class OrdenCompraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer ordenCompraId;
    @ManyToOne
    private ArticuloEntity articulo;
    private int cantidad;
    @ManyToMany
    private List<PedidoEntity> pedidosOrigen;
    private LocalDate fecha;
    private boolean pendiente;
    
    public OrdenCompraEntity() {
    	pedidosOrigen = new ArrayList<>();
    }

    public Integer getOrdenCompraId() {
        return ordenCompraId;
    }

    public void setOrdenCompraId(Integer ordenCompraId) {
        this.ordenCompraId = ordenCompraId;
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

   
    public List<PedidoEntity> getPedidosOrigen() {
		return pedidosOrigen;
	}

	public void setPedidosOrigen(List<PedidoEntity> pedidosOrigen) {
		this.pedidosOrigen = pedidosOrigen;
	}

	public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

	public boolean isPendiente() {
		return pendiente;
	}

	public void setPendiente(boolean pendiente) {
		this.pendiente = pendiente;
	}



}
