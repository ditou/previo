package remoteImplementation;

import controlador.PedidoControlador;
import dto.PedidoDTO;
import remoteInterface.PedidoRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class PedidoRemoteImpl extends UnicastRemoteObject implements PedidoRemote {

    private static final long serialVersionUID = -9035786495540524759L;

    public PedidoRemoteImpl() throws RemoteException {
        super();
    }

    @Override
    public Integer altaPedido(PedidoDTO pedido) throws RemoteException {
        return PedidoControlador.getInstancia().altaPedido(pedido);
    }

    @Override
    public void aprobarPedido(Integer pedidoId, String aclaracion) throws RemoteException {
        PedidoControlador.getInstancia().aprobarPedido(pedidoId, aclaracion);
    }

    @Override
    public void rechazarPedido(Integer pedidoId, String aclaracion) throws RemoteException {
        PedidoControlador.getInstancia().rechazarPedido(pedidoId, aclaracion);
    }

    @Override
    public void despacharPedido(Integer pedidoId, LocalDate fechaEntrega) throws RemoteException {
        PedidoControlador.getInstancia().despacharPedido(pedidoId, fechaEntrega);
    }

    @Override
    public PedidoDTO buscarPedido(Integer pedidoId) throws RemoteException {
        return PedidoControlador.getInstancia().buscarPedido(pedidoId);
    }
    
    @Override
    public PedidoDTO buscarPedido(Integer pedidoId, Integer clienteId) throws RemoteException {
        return PedidoControlador.getInstancia().buscarPedido(pedidoId, clienteId);
    }

    @Override
    public List<PedidoDTO> buscarPedidosByCliente(Integer clienteId) throws RemoteException {
        return PedidoControlador.getInstancia().buscarPedidosByCliente(clienteId);
    }

    @Override
    public List<PedidoDTO> buscarPedidosEnRevision() throws RemoteException {
        return PedidoControlador.getInstancia().buscarPedidosEnRevision();
    }

    @Override
    public List<PedidoDTO> buscarPedidosPendientesDespacho() throws RemoteException {
        return PedidoControlador.getInstancia().buscarPedidosPendientesDespacho();
    }

}
