package entity;

import model.EstadoPedido;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pedido", indexes= {	@Index(name="index_pedido_estado", columnList = "estado")})
public class PedidoEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer pedidoId;
    @ManyToOne
    private ClienteEntity cliente;
    private String direccionEnvio;
    private LocalDate fechaCreacion;
    private LocalDate fechaEntrega;
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
    private String aclaracion;
    @OneToMany(cascade = CascadeType.ALL)
    private List<PedidoItemEntity> items;

    public PedidoEntity() {
        this.items = new ArrayList<>();
    }

    public Integer getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Integer pedidoId) {
        this.pedidoId = pedidoId;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public String getAclaracion() {
        return aclaracion;
    }

    public void setAclaracion(String aclaracion) {
        this.aclaracion = aclaracion;
    }

    public List<PedidoItemEntity> getItems() {
        return items;
    }

    public void setItems(List<PedidoItemEntity> items) {
        this.items = items;
    }


}
