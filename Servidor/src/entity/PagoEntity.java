package entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Pago")
public class PagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer pagoId;
    private LocalDate fecha;
 
    @ManyToOne
    private ClienteEntity cliente;
  
	private java.math.BigDecimal total;

    public PagoEntity() {

    }

    public Integer getPagoId() {
        return pagoId;
    }

    public void setPagoId(Integer pagoId) {
        this.pagoId = pagoId;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

 
    public java.math.BigDecimal getTotal() {
        return total;
    }

    public void setTotal(java.math.BigDecimal total) {
        this.total = total;
    }
    
    public ClienteEntity getCliente() {
		return cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}


}
