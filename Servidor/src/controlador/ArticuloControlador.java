package controlador;

import java.util.ArrayList;
import java.util.List;

import dao.ArticuloDAO;
import dto.ArticuloDTO;
import model.Articulo;

public class ArticuloControlador {
	private static ArticuloControlador instancia;

	private ArticuloControlador() {
	}

	public static ArticuloControlador getInstancia() {
		if (instancia == null) {
			instancia = new ArticuloControlador();
		}
		return instancia;
	}

	public List<ArticuloDTO> findAllArticulos() {
		List<ArticuloDTO> articulos = new ArrayList<ArticuloDTO>();

		for (Articulo articulo : ArticuloDAO.getInstancia().getAll()) {
			articulos.add(articulo.toDTO());
		}

		return articulos;
	}

	public Integer altaArticulo(ArticuloDTO articulo) {
		return new Articulo(articulo).save().toDTO().getArticuloId();
	}

	public void bajaArticulo(Integer articuloId) {
		ArticuloDAO.getInstancia().findById(articuloId).darDeBaja();
	}

	public void modificarArticulo(ArticuloDTO articulo) {
		new Articulo(articulo).save();
	}
	
	public ArticuloDTO buscarArticulo(Integer articuloId) {
		return ArticuloDAO.getInstancia().findById(articuloId).toDTO();
	}

}
