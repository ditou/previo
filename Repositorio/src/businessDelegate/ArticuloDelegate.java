package businessDelegate;

import dto.ArticuloDTO;
import exception.GenericRemoteException;
import remoteInterface.ArticuloRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class ArticuloDelegate {
    private static ArticuloDelegate instancia;

    private ArticuloRemote articuloRemote;

    private ArticuloDelegate() throws GenericRemoteException {
        try {
            articuloRemote = (ArticuloRemote) Naming.lookup("ArticuloRemote");
        } catch (MalformedURLException e) {
            throw new GenericRemoteException(e);
        } catch (RemoteException e) {
            throw new GenericRemoteException(e);
        } catch (NotBoundException e) {
            throw new GenericRemoteException(e);
        }
    }

    public static ArticuloDelegate getInstancia() throws GenericRemoteException {
        if (instancia == null) {
            instancia = new ArticuloDelegate();
        }
        return instancia;
    }

    public List<ArticuloDTO> findAllArticulos() throws GenericRemoteException {
        try {
            return articuloRemote.findAllArticulos();
        } catch (RemoteException e) {
            throw new GenericRemoteException(e);
        }
    }

    public void altaArticulo(ArticuloDTO articulo) throws GenericRemoteException {
        try {
            articuloRemote.altaArticulo(articulo);
        } catch (RemoteException e) {
            throw new GenericRemoteException(e);
        }
    }

    public void bajaArticulo(Integer articuloId) throws GenericRemoteException {
        try {
            articuloRemote.bajaArticulo(articuloId);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

    public void modificarArticulo(ArticuloDTO articulo) throws GenericRemoteException {
        try {
            articuloRemote.modificarArticulo(articulo);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }

    public ArticuloDTO buscarArticulo(Integer articuloId) throws GenericRemoteException {
        try {
            return articuloRemote.buscarArticulo(articuloId);
        } catch (RemoteException re) {
            throw new GenericRemoteException(re);
        }
    }
}
