package businessDelegate;

import dto.FacturaDTO;
import dto.PagoDTO;
import exception.GenericRemoteException;
import remoteInterface.FacturacionRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class FacturacionDelegate {

    private static FacturacionDelegate instancia;

    private FacturacionRemote facturacionRemote;

    private FacturacionDelegate() throws GenericRemoteException {
        try {
            facturacionRemote = (FacturacionRemote) Naming.lookup("FacturacionRemote");
        } catch (MalformedURLException e) {
            throw new GenericRemoteException(e);
        } catch (RemoteException e) {
            throw new GenericRemoteException(e);
        } catch (NotBoundException e) {
            throw new GenericRemoteException(e);
        }
    }

    public static FacturacionDelegate getInstancia() throws GenericRemoteException {
        if (instancia == null) {
            instancia = new FacturacionDelegate();
        }
        return instancia;
    }

    public List<FacturaDTO> buscarFacturasPendientesDeCancelacionByCliente(Integer clienteId) throws GenericRemoteException {
        try {
            return facturacionRemote.buscarFacturasPendientesDeCancelacionByCliente(clienteId);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

    public void registrarPago(PagoDTO pagoDTO) throws GenericRemoteException {
        try {
        	facturacionRemote.registrarPago(pagoDTO);
        }catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }
    
    public FacturaDTO buscarFactura(Integer facturaId) throws GenericRemoteException {
        try {
            return facturacionRemote.buscarFactura(facturaId);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }
}
