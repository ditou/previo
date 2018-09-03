package remoteImplementation;

import controlador.ClienteControlador;
import dto.ClienteDTO;
import remoteInterface.ClienteRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ClienteRemoteImpl extends UnicastRemoteObject implements ClienteRemote {

    private static final long serialVersionUID = -5331364471431270812L;

    public ClienteRemoteImpl() throws RemoteException {
        super();
    }

    @Override
    public List<ClienteDTO> findAllClientes() {
        return ClienteControlador.getInstancia().findAllClientes();
    }

    @Override
    public int altaCliente(ClienteDTO clienteDTO) throws RemoteException {
        return ClienteControlador.getInstancia().altaCliente(clienteDTO);

    }

    @Override
    public void bajaCliente(Integer clienteId) throws RemoteException {
        ClienteControlador.getInstancia().bajaCliente(clienteId);
    }

    @Override
    public void modificarCliente(ClienteDTO cliente) throws RemoteException {
        ClienteControlador.getInstancia().modificarCliente(cliente);
    }

    @Override
    public ClienteDTO buscarCliente(Integer clienteId) throws RemoteException {
        return ClienteControlador.getInstancia().buscarCliente(clienteId);
    }

    @Override
    public Integer validarLogin(String email, String contrasenia) throws RemoteException {
        return ClienteControlador.getInstancia().validarLogin(email, contrasenia);
    }

}
