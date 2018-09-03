package businessDelegate;

import dto.ClienteDTO;
import exception.GenericRemoteException;
import remoteInterface.ClienteRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ClienteDelegate {

    private static ClienteDelegate instancia;

    private ClienteRemote clienteRemote;

    private ClienteDelegate() throws GenericRemoteException {
        try {
            clienteRemote = (ClienteRemote) Naming.lookup("ClienteRemote");
        } catch (MalformedURLException e) {
            throw new GenericRemoteException(e);
        } catch (RemoteException e) {
            throw new GenericRemoteException(e);
        } catch (NotBoundException e) {
            throw new GenericRemoteException(e);
        }
    }

    public static ClienteDelegate getInstancia() throws GenericRemoteException {
        if (instancia == null) {
            instancia = new ClienteDelegate();
        }
        return instancia;
    }

    public List<ClienteDTO> findAllClientes() throws GenericRemoteException {
        try {
            return clienteRemote.findAllClientes();
        } catch (RemoteException e) {
            throw new GenericRemoteException(e);
        }
    }

    public int altaCliente(ClienteDTO cliente) throws GenericRemoteException {
        try {
            return clienteRemote.altaCliente(cliente);
        } catch (RemoteException e) {
            throw new GenericRemoteException(e);
        }
    }

    public void bajaCliente(Integer clienteId) throws GenericRemoteException {
        try {
            clienteRemote.bajaCliente(clienteId);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

    public void modificarCliente(ClienteDTO cliente) throws GenericRemoteException {
        try {
            clienteRemote.modificarCliente(cliente);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

    public ClienteDTO buscarCliente(Integer clienteId) throws GenericRemoteException {
        try {
            return clienteRemote.buscarCliente(clienteId);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

    public Integer validarLogin(String email, String contrasenia) throws GenericRemoteException {
        try {
            return clienteRemote.validarLogin(email, contrasenia);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

}
