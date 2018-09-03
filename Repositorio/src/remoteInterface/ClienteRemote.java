package remoteInterface;

import dto.ClienteDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClienteRemote extends Remote {

    List<ClienteDTO> findAllClientes() throws RemoteException;

    int altaCliente(ClienteDTO clienteDTO) throws RemoteException;

    void bajaCliente(Integer clienteId) throws RemoteException;

    void modificarCliente(ClienteDTO clienteDTO) throws RemoteException;

    ClienteDTO buscarCliente(Integer clienteId) throws RemoteException;

    Integer validarLogin(String email, String contrasenia) throws RemoteException;
}
