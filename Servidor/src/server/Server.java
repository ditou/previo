package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remoteImplementation.ArticuloRemoteImpl;
import remoteImplementation.ClienteRemoteImpl;
import remoteImplementation.ComprasRemoteImpl;
import remoteImplementation.DepositoRemoteImpl;
import remoteImplementation.FacturacionRemoteImpl;
import remoteImplementation.PedidoRemoteImpl;
import remoteInterface.ArticuloRemote;
import remoteInterface.ClienteRemote;
import remoteInterface.ComprasRemote;
import remoteInterface.DepositoRemote;
import remoteInterface.FacturacionRemote;
import remoteInterface.PedidoRemote;

public class Server {

    public Server() {
        iniciar();
    }

    public static void main(String[] args) {
        new Server();
    }

    public void iniciar() {
        try {
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);

            ClienteRemote clienteRemote = new ClienteRemoteImpl();
            ArticuloRemote articuloRemote = new ArticuloRemoteImpl();
            PedidoRemote pedidoRemote = new PedidoRemoteImpl();
            ComprasRemote comprasRemote = new ComprasRemoteImpl();
            DepositoRemote depositoRemote = new DepositoRemoteImpl();
            FacturacionRemote facturacionRemote = new FacturacionRemoteImpl();

            Naming.bind("//localhost:1099/ClienteRemote", clienteRemote);
            Naming.bind("//localhost:1099/ArticuloRemote", articuloRemote);
            Naming.bind("//localhost:1099/PedidoRemote", pedidoRemote);
            Naming.bind("//localhost:1099/ComprasRemote", comprasRemote);
            Naming.bind("//localhost:1099/DepositoRemote", depositoRemote);
            Naming.bind("//localhost:1099/FacturacionRemote", facturacionRemote);

            System.out.println("Servicios registrados exitosamente");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
