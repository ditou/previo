package businessDelegate;

import dto.CompraDTO;
import dto.OrdenCompraDTO;
import exception.GenericRemoteException;
import remoteInterface.ComprasRemote;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ComprasDelegate {

    private static ComprasDelegate instancia;

    private ComprasRemote comprasRemote;

    private ComprasDelegate() throws GenericRemoteException {
        try {
            comprasRemote = (ComprasRemote) Naming.lookup("ComprasRemote");
        } catch (MalformedURLException e) {
            throw new GenericRemoteException(e);
        } catch (RemoteException e) {
            throw new GenericRemoteException(e);
        } catch (NotBoundException e) {
            throw new GenericRemoteException(e);
        }
    }

    public static ComprasDelegate getInstancia() throws GenericRemoteException {
        if (instancia == null) {
            instancia = new ComprasDelegate();
        }
        return instancia;
    }

    public List<OrdenCompraDTO> findAllOrdenCompraPendientes() throws GenericRemoteException {
        try {
            return comprasRemote.findAllOrdenCompraPendientes();
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

    public void crearCompra(List<Integer> ordenesCompraId, String proveedor, List<BigDecimal> precios) throws GenericRemoteException {
        try {
            comprasRemote.crearCompra(ordenesCompraId, proveedor, precios);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }
    
    public List<CompraDTO> findAllComprasPendientes() throws GenericRemoteException {
        try {
            return comprasRemote.findAllComprasPendientes();
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }
    
    
    public List<String> getUltimosProveedores() throws GenericRemoteException {
        try {
            return comprasRemote.getUltimosProveedores();
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }
    
    public CompraDTO findCompraById(Integer compraId) throws GenericRemoteException {
        try {
            return comprasRemote.findCompraById(compraId);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }
}
