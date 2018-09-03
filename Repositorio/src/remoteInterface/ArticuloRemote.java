package remoteInterface;

import dto.ArticuloDTO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ArticuloRemote extends Remote {
    List<ArticuloDTO> findAllArticulos() throws RemoteException;

    Integer altaArticulo(ArticuloDTO articuloDTO) throws RemoteException;

    void bajaArticulo(Integer articuloId) throws RemoteException;

    void modificarArticulo(ArticuloDTO articuloDTO) throws RemoteException;

    ArticuloDTO buscarArticulo(Integer articuloId) throws RemoteException;

}
