package businessDelegate;

import dto.PedidoDTO;
import exception.GenericRemoteException;
import remoteInterface.PedidoRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public class PedidoDelegate {
    private static PedidoDelegate instancia;

    private PedidoRemote pedidoRemote;

    private PedidoDelegate() throws GenericRemoteException {
        try {
            pedidoRemote = (PedidoRemote) Naming.lookup("PedidoRemote");
        } catch (MalformedURLException e) {
            throw new GenericRemoteException(e);
        } catch (RemoteException e) {
            throw new GenericRemoteException(e);
        } catch (NotBoundException e) {
            throw new GenericRemoteException(e);
        }
    }

    public static PedidoDelegate getInstancia() throws GenericRemoteException {
        if (instancia == null) {
            instancia = new PedidoDelegate();
        }
        return instancia;
    }

    public Integer altaPedido(PedidoDTO pedido) throws GenericRemoteException {
        try {
            return pedidoRemote.altaPedido(pedido);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

    public void aprobarPedido(Integer pedidoId, String aclaracion) throws GenericRemoteException {
        try {
            pedidoRemote.aprobarPedido(pedidoId, aclaracion);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

    public void rechazarPedido(Integer pedidoId, String aclaracion) throws GenericRemoteException {
        try {
            pedidoRemote.rechazarPedido(pedidoId, aclaracion);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

    public void despacharPedido(Integer pedidoId, LocalDate fechaEntrega) throws GenericRemoteException {
        try {
            pedidoRemote.despacharPedido(pedidoId, fechaEntrega);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

    public PedidoDTO buscarPedido(Integer pedidoId) throws GenericRemoteException {
        try {
            return pedidoRemote.buscarPedido(pedidoId);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }
    
    public PedidoDTO buscarPedido(Integer pedidoId, Integer clienteId) throws GenericRemoteException {
        try {
            return pedidoRemote.buscarPedido(pedidoId, clienteId);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

    public List<PedidoDTO> buscarPedidosByCliente(Integer clienteId) throws GenericRemoteException {
        try {
            return pedidoRemote.buscarPedidosByCliente(clienteId);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

    public List<PedidoDTO> buscarPedidosEnRevision() throws GenericRemoteException {
        try {
            return pedidoRemote.buscarPedidosEnRevision();
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

    public List<PedidoDTO> buscarPedidosPendientesDespacho() throws GenericRemoteException {
        try {
            return pedidoRemote.buscarPedidosPendientesDespacho();
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

}
