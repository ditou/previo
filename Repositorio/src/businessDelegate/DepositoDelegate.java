package businessDelegate;

import dto.ArticuloUbicacionDTO;
import dto.MovimientoStockDTO;
import exception.GenericRemoteException;
import remoteInterface.DepositoRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

public class DepositoDelegate {
    private static DepositoDelegate instancia;

    private DepositoRemote depositoRemote;

    private DepositoDelegate() throws GenericRemoteException {
        try {
            depositoRemote = (DepositoRemote) Naming.lookup("DepositoRemote");
        } catch (MalformedURLException e) {
            throw new GenericRemoteException(e);
        } catch (RemoteException e) {
            throw new GenericRemoteException(e);
        } catch (NotBoundException e) {
            throw new GenericRemoteException(e);
        }
    }

    public static DepositoDelegate getInstancia() throws GenericRemoteException {
        if (instancia == null) {
            instancia = new DepositoDelegate();
        }
        return instancia;
    }

    public int altaMovimiento(MovimientoStockDTO movimientoStockDTO, Integer articuloId, Integer loteId, Integer ubicacionId) throws GenericRemoteException {
        try {
            return depositoRemote.altaMovimiento(movimientoStockDTO, articuloId, loteId, ubicacionId);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

	public void recepcionarCompra(Integer compraId, List<LocalDate> fechas, List<Integer> nroLotes, List<Integer> lotesId )throws GenericRemoteException{  
        try {
            depositoRemote.recepcionarCompra(compraId, fechas, nroLotes, lotesId);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

	public List<ArticuloUbicacionDTO> findAllUbicaciones() throws GenericRemoteException{
	     try {
	           return depositoRemote.findAllUbicaciones();
	        } catch (RemoteException re) {
	            throw new GenericRemoteException(re);
	        }
	}
	
}
