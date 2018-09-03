package entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import model.CategoriaFiscal;
import model.Cliente;

@Entity
@Table(name = "Cliente", indexes= {	@Index(name="index_cliente_email", columnList = "email")})
public class ClienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer clienteId;
	private String nombre;
	@Column(unique = true)
	private String nroIdentificacion;
	private String contrasenia;
	@OneToOne(cascade = CascadeType.ALL)
	private CuentaCorrienteEntity cuentaCorriente;
	private String direccion;
	private String telefono;
	@Column(unique = true)
	private String email;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ClienteObservacionEntity> observaciones;
	private boolean activo;
	@Enumerated(EnumType.STRING)
	private CategoriaFiscal categoriaFiscal;

	public ClienteEntity() {
		this.observaciones = new ArrayList<>();
	}

	public Integer getClienteId() {
		return clienteId;
	}

	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNroIdentificacion() {
		return nroIdentificacion;
	}

	public void setNroIdentificacion(String nroIdentificacion) {
		this.nroIdentificacion = nroIdentificacion;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public CuentaCorrienteEntity getCuentaCorriente() {
		return cuentaCorriente;
	}

	public void setCuentaCorriente(CuentaCorrienteEntity cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<ClienteObservacionEntity> getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(List<ClienteObservacionEntity> observaciones) {
		this.observaciones = observaciones;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public CategoriaFiscal getCategoriaFiscal() {
		return categoriaFiscal;
	}

	public void setCategoriaFiscal(CategoriaFiscal categoriaFiscal) {
		this.categoriaFiscal = categoriaFiscal;
	}

}
