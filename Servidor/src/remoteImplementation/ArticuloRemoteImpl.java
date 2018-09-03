package remoteImplementation;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import controlador.ArticuloControlador;
import dto.ArticuloDTO;
import remoteInterface.ArticuloRemote;

public class ArticuloRemoteImpl extends UnicastRemoteObject implements ArticuloRemote {

	private static final long serialVersionUID = -5331364471431274002L;

	public ArticuloRemoteImpl() throws RemoteException {
		super();
	}

	@Override
	public List<ArticuloDTO> findAllArticulos() {
		return ArticuloControlador.getInstancia().findAllArticulos();
	}

	@Override
	public Integer altaArticulo(ArticuloDTO articuloDTO) throws RemoteException {
		return ArticuloControlador.getInstancia().altaArticulo(articuloDTO);

	}

	@Override
	public void bajaArticulo(Integer articuloId) throws RemoteException {
		ArticuloControlador.getInstancia().bajaArticulo(articuloId);
	}

	@Override
	public void modificarArticulo(ArticuloDTO articulo) throws RemoteException {
		ArticuloControlador.getInstancia().modificarArticulo(articulo);
	}

	@Override
	public ArticuloDTO buscarArticulo(Integer articuloId) throws RemoteException {
		return ArticuloControlador.getInstancia().buscarArticulo(articuloId);
	}

}
