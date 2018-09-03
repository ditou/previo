package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Factura", indexes= {	@Index(name="index_factura_saldo", columnList = "saldo"), @Index(name="index_factura_letra", columnList="letra"), @Index(name="index_factura_cliente", columnList="cliente_clienteid")})
public class FacturaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer facturaId;
    @ManyToOne
    private ClienteEntity cliente;
    private String letra;
    private LocalDate fecha;
    @OneToMany(cascade = CascadeType.ALL)
    private List<FacturaItemEntity> items;
    private java.math.BigDecimal saldo;
    private int numero;
    private java.math.BigDecimal iva;

    public FacturaEntity() {
        items = new ArrayList<>();
    }

    public Integer getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(Integer facturaId) {
        this.facturaId = facturaId;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<FacturaItemEntity> getItems() {
        return items;
    }

    public void setItems(List<FacturaItemEntity> items) {
        this.items = items;
    }

    public java.math.BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(java.math.BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

	public java.math.BigDecimal getIva() {
		return iva;
	}

	public void setIva(java.math.BigDecimal iva) {
		this.iva = iva;
	}


}
