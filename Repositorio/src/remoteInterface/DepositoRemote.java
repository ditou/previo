package remoteInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import dto.ArticuloUbicacionDTO;
import dto.MovimientoStockDTO;

public interface DepositoRemote extends Remote {
    int altaMovimiento(MovimientoStockDTO movimientoStockDTO, Integer articuloId, Integer loteId, Integer ubicacionId) throws RemoteException;
	void recepcionarCompra(Integer compraId, List<LocalDate> fechas, List<Integer> nroLotes, List<Integer> lotesId )throws RemoteException; 
	List<ArticuloUbicacionDTO> findAllUbicaciones() throws RemoteException;
}
