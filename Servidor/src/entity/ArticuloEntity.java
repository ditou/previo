package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "Articulo")
public class ArticuloEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer articuloId;
	private String codigoBarras;
	private String descripcion;
	private int tamanio;
	private String unidad;
	private java.math.BigDecimal precioVenta;
	private int cantFijaCompra;
	private int cantOcupaUbicacion;
	@OneToMany( cascade = CascadeType.ALL)
	@OrderBy("FechaVencimiento ASC")
	private List<LoteEntity> lotes;
	private boolean activo;
	private String presentacion;

	public ArticuloEntity() {
		this.lotes = new ArrayList<>();
	}

	public Integer getArticuloId() {
		return articuloId;
	}

	public void setArticuloId(Integer articuloId) {
		this.articuloId = articuloId;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public java.math.BigDecimal getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(java.math.BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getCantFijaCompra() {
		return cantFijaCompra;
	}

	public void setCantFijaCompra(int cantFijaCompra) {
		this.cantFijaCompra = cantFijaCompra;
	}

	public int getCantOcupaUbicacion() {
		return cantOcupaUbicacion;
	}

	public void setCantOcupaUbicacion(int cantOcupaUbicacion) {
		this.cantOcupaUbicacion = cantOcupaUbicacion;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public List<LoteEntity> getLotes() {
		return lotes;
	}

	public void setLotes(List<LoteEntity> lotes) {
		this.lotes = lotes;
	}

}
