package model;

import java.util.ArrayList;
import java.util.List;

import dao.ClienteDAO;
import dto.ClienteSmallDTO;
import dto.ClienteDTO;
import dto.ClienteObservacionDTO;

public class Cliente {
	private Integer clienteId;
	private String nombre;
	private String nroIdentificacion;
	private String contrasenia;
	private CuentaCorriente cuentaCorriente;
	private String direccion;
	private String telefono;
	private String email;
	private List<ClienteObservacion> observaciones;
	private boolean activo;
	private CategoriaFiscal categoriaFiscal;
	
	public Cliente() {
		this.observaciones = new ArrayList<>();
	}
	
	public Cliente(ClienteDTO cliente) {
		this();
		this.nombre = cliente.getNombre();
		this.nroIdentificacion = cliente.getNroIdentificacion();
		this.contrasenia = cliente.getContrasenia();
		this.direccion = cliente.getDireccion();
		this.telefono = cliente.getTelefono();
		this.email = cliente.getEmail();
		if(cliente.getCategoriaFiscal() != null)
			this.categoriaFiscal = CategoriaFiscal.valueOf(cliente.getCategoriaFiscal());
		this.clienteId = cliente.getClienteId();
		
		if(clienteId == null) {
			this.activo = true;
			this.cuentaCorriente = new CuentaCorriente(cliente.getCuentaCorriente());
		}else {
			Cliente clienteDB = ClienteDAO.getInstancia().findById(clienteId);
			this.cuentaCorriente = clienteDB.getCuentaCorriente();
			if(cliente.getCuentaCorriente().getLimiteCredito() != null)
				this.cuentaCorriente.setLimiteCredito(cliente.getCuentaCorriente().getLimiteCredito());
			this.observaciones = clienteDB.getObservaciones();

			this.activo = clienteDB.isActivo();
			//Agrega observaciones?
			if(cliente.getObservaciones().size() > this.getObservaciones().size()) {
				for(ClienteObservacionDTO obs : cliente.getObservaciones()) {
					if(obs.getClienteObservacionId() == null)
						this.agregarObservacion(new ClienteObservacion(obs));
				}
			}else {
				//Modifica alguna?
				for(int i = 0; i < cliente.getObservaciones().size(); i++) {
					if(!this.getObservaciones().get(i).getDescripcion().equals(cliente.getObservaciones().get(i).getDescripcion())){
						this.getObservaciones().get(i).setDescripcion(cliente.getObservaciones().get(i).getDescripcion());
						break;
					}
				}
			}
			


		}	
			
	}
	
	public ClienteDTO toDTO() {
		ClienteDTO cliente = new ClienteDTO();
		cliente.setClienteId(this.clienteId);
		cliente.setNombre(this.getNombre());
		cliente.setNroIdentificacion(this.nroIdentificacion);
		cliente.setContrasenia(this.contrasenia);
		cliente.setCuentaCorriente(this.cuentaCorriente.toDTO());
		cliente.setDireccion(this.direccion);
		cliente.setTelefono(this.telefono);
		cliente.setEmail(this.email);
		for(ClienteObservacion observacion : this.observaciones) {
			cliente.agregarObservacion(observacion.toDTO());
		}
		cliente.setActivo(this.activo);
		if(this.categoriaFiscal != null)
			cliente.setCategoriaFiscal(this.categoriaFiscal.toString());
		return cliente;
	}
	
	public ClienteSmallDTO toSmallDTO() {
		ClienteSmallDTO dto = new ClienteSmallDTO();
		dto.setClienteId(this.clienteId);
		dto.setNombre(this.getNombre());
		return dto;
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

	public CuentaCorriente getCuentaCorriente() {
		return cuentaCorriente;
	}

	public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
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

	public List<ClienteObservacion> getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(List<ClienteObservacion> observaciones) {
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


    public Cliente save() {
		return ClienteDAO.getInstancia().save(this);
    }

	public void darDeBaja() {
		this.activo = false;
		save();
	}

	public boolean validarLogin(String contrasenia) {
		return this.contrasenia.equals(contrasenia);
	}

    public void agregarMovimientoCC(Factura factura) {
		this.getCuentaCorriente().agregarMovimientoCC(factura);
		this.save();
    }

	public void agregarMovimientoCC(Pago pago) {
		this.getCuentaCorriente().agregarMovimientoCC(pago);
		this.save();
	}
	
	public void agregarObservacion(ClienteObservacion observacion) {
		if(this.observaciones == null) {
			observaciones = new ArrayList<>();
		}
		this.observaciones.add(observacion);
	}
}
