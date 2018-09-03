package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Remito")
public class RemitoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer remitoId;
    @ManyToOne
    private ClienteEntity cliente;
    @OneToMany(cascade = CascadeType.ALL)
    private List<RemitoItemEntity> items;
    private LocalDate fecha;

    public RemitoEntity() {

    }

    public Integer getRemitoId() {
        return remitoId;
    }

    public void setRemitoId(Integer remitoId) {
        this.remitoId = remitoId;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public List<RemitoItemEntity> getItems() {
        return items;
    }

    public void setItems(List<RemitoItemEntity> items) {
        this.items = items;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
