package remoteInterface;

import dto.FacturaDTO;
import dto.PagoDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface FacturacionRemote extends Remote {

    List<FacturaDTO> buscarFacturasPendientesDeCancelacionByCliente(Integer clienteId) throws RemoteException;

    void registrarPago(PagoDTO pago) throws RemoteException;
    
    public FacturaDTO buscarFactura(Integer facturaId) throws RemoteException;
}
