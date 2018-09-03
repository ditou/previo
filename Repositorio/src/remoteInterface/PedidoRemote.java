package remoteInterface;

import dto.PedidoDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public interface PedidoRemote extends Remote {
    Integer altaPedido(PedidoDTO pedido) throws RemoteException;

    void aprobarPedido(Integer pedidoId, String aclaracion) throws RemoteException;

    void rechazarPedido(Integer pedidoId, String aclaracion) throws RemoteException;

    void despacharPedido(Integer pedidoId, LocalDate fechaEntrega) throws RemoteException;

    PedidoDTO buscarPedido(Integer pedidoId) throws RemoteException;

    PedidoDTO buscarPedido(Integer pedidoId, Integer clienteId) throws RemoteException;
    
    List<PedidoDTO> buscarPedidosByCliente(Integer clienteId) throws RemoteException;

    List<PedidoDTO> buscarPedidosEnRevision() throws RemoteException;

    List<PedidoDTO> buscarPedidosPendientesDespacho() throws RemoteException;
}
