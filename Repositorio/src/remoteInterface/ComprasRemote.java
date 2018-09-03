package remoteInterface;

import dto.CompraDTO;
import dto.OrdenCompraDTO;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ComprasRemote extends Remote {
    void crearCompra(List<Integer> ordenesCompraId, String proveedor, List<BigDecimal> precios) throws RemoteException;
    List<OrdenCompraDTO> findAllOrdenCompraPendientes() throws RemoteException;
    List<CompraDTO> findAllComprasPendientes() throws RemoteException;
    CompraDTO findCompraById(Integer compraId) throws RemoteException;
    
    List<String> getUltimosProveedores() throws RemoteException;
}
