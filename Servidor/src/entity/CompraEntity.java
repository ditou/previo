package entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Compra")
public class CompraEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer compraId;
    private String proveedor;
    private LocalDate fecha;
    @OneToMany(cascade = CascadeType.ALL)
    private List<CompraItemEntity> items;

    private boolean recepcionada;
    
    public CompraEntity() {
    	
    }

	public Integer getCompraId() {
		return compraId;
	}

	public void setCompraId(Integer compraId) {
		this.compraId = compraId;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public List<CompraItemEntity> getItems() {
		return items;
	}

	public void setItems(List<CompraItemEntity> items) {
		this.items = items;
	}



	public boolean isRecepcionada() {
		return recepcionada;
	}

	public void setRecepcionada(boolean recepcionada) {
		this.recepcionada = recepcionada;
	}
    
    
    
}
