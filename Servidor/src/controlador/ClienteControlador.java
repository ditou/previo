package controlador;

import dao.ArticuloDAO;
import dao.ClienteDAO;
import dto.ClienteDTO;
import model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteControlador {

    private static ClienteControlador instancia;

    private ClienteControlador() {
    }

    public static ClienteControlador getInstancia() {
        if (instancia == null) {
            instancia = new ClienteControlador();
        }
        return instancia;
    }

    public List<ClienteDTO> findAllClientes() {
        List<ClienteDTO> clientesDTO = new ArrayList<>();
        List<Cliente> clientes = ClienteDAO.getInstancia().getAll();

        for (Cliente cliente : clientes) {
            clientesDTO.add(cliente.toDTO());
        }

        return clientesDTO;
    }

    public Integer altaCliente(ClienteDTO cliente) {
        return new Cliente(cliente).save().getClienteId();
    }

    public void bajaCliente(Integer clienteId) {
		ClienteDAO.getInstancia().findById(clienteId).darDeBaja();
    }

    public void modificarCliente(ClienteDTO cliente) {
        new Cliente(cliente).save();
    }

    public ClienteDTO buscarCliente(Integer clienteId) {
        Cliente cliente = ClienteDAO.getInstancia().findById(clienteId);
        return cliente != null ? cliente.toDTO() : null;
    }

    public Integer validarLogin(String email, String contrasenia) {
        Cliente cliente = ClienteDAO.getInstancia().findClienteByEmail(email);
        if(cliente != null && cliente.validarLogin(contrasenia) && cliente.isActivo())
        	return cliente.getClienteId();
        else
        	return null;
    }

}
