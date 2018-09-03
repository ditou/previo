package remoteImplementation;

import controlador.DepositoControlador;
import dto.ArticuloUbicacionDTO;
import dto.MovimientoStockDTO;
import dto.UbicacionDTO;
import remoteInterface.DepositoRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.List;

public class DepositoRemoteImpl extends UnicastRemoteObject implements DepositoRemote {

    private static final long serialVersionUID = -9035786495540524766L;

    public DepositoRemoteImpl() throws RemoteException {
        super();
    }

    @Override
    public int altaMovimiento(MovimientoStockDTO movimientoStockDTO, Integer articuloId, Integer loteId, Integer ubicacionId) throws RemoteException{
        return DepositoControlador.getInstancia().altaMovimiento(movimientoStockDTO, articuloId, loteId, ubicacionId);
    }


    @Override
	public void recepcionarCompra(Integer compraId, List<LocalDate> fechas, List<Integer> nroLotes, List<Integer> lotesId )throws RemoteException{ 
    	DepositoControlador.getInstancia().recepcionarCompra(compraId, fechas, nroLotes, lotesId);
    }

    @Override
	public List<ArticuloUbicacionDTO> findAllUbicaciones() throws RemoteException{
    	return DepositoControlador.getInstancia().findAllUbicaciones();
    }
}
