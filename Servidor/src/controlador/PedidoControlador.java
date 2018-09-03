package controlador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import dao.ClienteDAO;
import dao.PedidoDAO;
import dto.PedidoDTO;
import model.EstadoPedido;
import model.Pedido;

public class PedidoControlador {
	private static PedidoControlador instancia;

	private PedidoControlador() {
	}

	public static PedidoControlador getInstancia() {
		if (instancia == null) {
			instancia = new PedidoControlador();
		}
		return instancia;
	}

	public PedidoDTO buscarPedido(Integer pedidoId) {
		Pedido pedido = PedidoDAO.getInstancia().findById(pedidoId);
		return pedido != null ? pedido.toDTO() : null;
	}
	
	public PedidoDTO buscarPedido(Integer pedidoId, Integer clienteId) {
		Pedido pedido = PedidoDAO.getInstancia().findPedidoByIdAndCliente(pedidoId, clienteId);
		return pedido != null ? pedido.toDTO() : null;
	}

	public Integer altaPedido(PedidoDTO pedido) {
		Pedido model = new Pedido(pedido).save(); 
		return model.getPedidoId();
	}

	public void aprobarPedido(Integer pedidoId, String aclaracion) {
		Pedido pedido = PedidoDAO.getInstancia().findById(pedidoId);
		pedido.aprobar(aclaracion);
	}

	public void rechazarPedido(Integer pedidoId, String aclaracion) {
		Pedido pedido = PedidoDAO.getInstancia().findById(pedidoId);
		pedido.rechazar(aclaracion);
	}

	public void despacharPedido(Integer pedidoId, LocalDate fechaEntrega) {
		Pedido pedido = PedidoDAO.getInstancia().findById(pedidoId);
		pedido.despachar(fechaEntrega);
	}

	public List<PedidoDTO> buscarPedidosByCliente(Integer clienteId) {
		List<Pedido> pedidos = PedidoDAO.getInstancia()
				.findAllPedidosByCliente(ClienteDAO.getInstancia().findById(clienteId));

		return pedidos.stream().map(Pedido::toDTO).collect(Collectors.toList());
	}

	public List<PedidoDTO> buscarPedidosEnRevision() {
		return toDTO(PedidoDAO.getInstancia().findAllPedidosByEstado(EstadoPedido.EN_REVISION));
	}

	public List<PedidoDTO> buscarPedidosPendientesDespacho() {
		return toDTO(PedidoDAO.getInstancia().findAllPedidosByEstado(EstadoPedido.PENDIENTE_DESPACHO));
	}

	private List<PedidoDTO> toDTO(List<Pedido> pedidos) {
		List<PedidoDTO> pedidosDTO = new ArrayList<>();
		for (Pedido pedido : pedidos) {
			pedidosDTO.add(pedido.toDTO());
		}
		return pedidosDTO;
	}

}
