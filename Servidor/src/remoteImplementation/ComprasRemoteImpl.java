package remoteImplementation;

import controlador.ComprasControlador;
import dto.CompraDTO;
import dto.OrdenCompraDTO;
import remoteInterface.ComprasRemote;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class ComprasRemoteImpl extends UnicastRemoteObject implements ComprasRemote {

    private static final long serialVersionUID = -5331364200431274002L;

    public ComprasRemoteImpl() throws RemoteException {
        super();
    }


    @Override
    public void crearCompra(List<Integer> ordenesCompraId, String proveedor, List<BigDecimal> precios) throws RemoteException {
        ComprasControlador.getInstancia().crearCompra(ordenesCompraId, proveedor, precios);
    }


    @Override
    public List<OrdenCompraDTO> findAllOrdenCompraPendientes() throws RemoteException {
        return ComprasControlador.getInstancia().findAllOrdenCompraPendientes();
    }

    @Override
    public List<CompraDTO> findAllComprasPendientes() throws RemoteException {
        return ComprasControlador.getInstancia().findAllComprasPendientes();
    }
    
    @Override
    public CompraDTO findCompraById(Integer compraId) throws RemoteException {
    	return ComprasControlador.getInstancia().findCompraById(compraId);
    }

    @Override
    public List<String> getUltimosProveedores() throws RemoteException{
    	return ComprasControlador.getInstancia().getUltimosProveedores();
    }
}
