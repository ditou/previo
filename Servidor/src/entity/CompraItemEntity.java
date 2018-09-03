package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CompraItem")
public class CompraItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer compraItemId;
    @OneToOne
    private OrdenCompraEntity ordenCompra;
    private java.math.BigDecimal precio;
    
    public CompraItemEntity() {
    	
    }

	public Integer getCompraItemId() {
		return compraItemId;
	}

	public void setCompraItemId(Integer compraItemId) {
		this.compraItemId = compraItemId;
	}

	

	public OrdenCompraEntity getOrdenCompra() {
		return ordenCompra;
	}

	public void setOrdenCompra(OrdenCompraEntity ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public java.math.BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(java.math.BigDecimal precio) {
		this.precio = precio;
	}
    
	
    
}
