package remoteImplementation;

import controlador.FacturacionControlador;
import dto.FacturaDTO;
import dto.PagoDTO;
import remoteInterface.FacturacionRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class FacturacionRemoteImpl extends UnicastRemoteObject implements FacturacionRemote {

    private static final long serialVersionUID = -5340364551431274002L;

    public FacturacionRemoteImpl() throws RemoteException {
        super();
    }


    @Override
    public List<FacturaDTO> buscarFacturasPendientesDeCancelacionByCliente(Integer clienteId) throws RemoteException {
        return FacturacionControlador.getInstancia().buscarFacturasPendientesDeCancelacionByCliente(clienteId);
    }

    @Override
    public void registrarPago(PagoDTO pago) throws RemoteException {
        FacturacionControlador.getInstancia().registrarPago(pago);
    }
    
    @Override
    public FacturaDTO buscarFactura(Integer facturaId) throws RemoteException {
    	return FacturacionControlador.getInstancia().buscarFactura(facturaId);
    }
}
