package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -8655239895983395351L;
	
	private Integer clienteId;
	private String nombre;
	private String nroIdentificacion;
	private String contrasenia;
	private CuentaCorrienteDTO cuentaCorriente;
	private String direccion;
	private String telefono;
	private String email;
	private List<ClienteObservacionDTO> observaciones;
	private boolean activo;
	private String categoriaFiscal;
	
	public ClienteDTO() {
		this.observaciones = new ArrayList<>();
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

	public CuentaCorrienteDTO getCuentaCorriente() {
		return cuentaCorriente;
	}

	public void setCuentaCorriente(CuentaCorrienteDTO cuentaCorriente) {
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

	public List<ClienteObservacionDTO> getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(List<ClienteObservacionDTO> observaciones) {
		this.observaciones = observaciones;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public String getCategoriaFiscal() {
		return categoriaFiscal;
	}

	public void setCategoriaFiscal(String categoriaFiscal) {
		this.categoriaFiscal = categoriaFiscal;
	}


	public Integer getClienteId() {
		return clienteId;
	}


	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
	
	public void agregarObservacion(ClienteObservacionDTO obs) {
		if(this.observaciones == null) {
			observaciones = new ArrayList<>();
		}
		observaciones.add(obs);
	}

}
