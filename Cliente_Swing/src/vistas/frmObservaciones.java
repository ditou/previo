package vistas;

import java.util.List;

import javax.swing.JFrame;

import dto.ClienteDTO;
import dto.ClienteObservacionDTO;
import dto.CuentaCorrienteItemDTO;

public class frmObservaciones extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 156670636186795028L;
	ClienteDTO cliente;
	
	public frmObservaciones(ClienteDTO c){
		cliente=c;
		Inicializar();
		this.setVisible(true);
	}
	
	private void Inicializar(){
		pnlObservaciones pnl= new pnlObservaciones(this,cliente);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Observaciones");
		this.setBounds(250, 150, 400, 400);
		this.add(pnl);
		this.setResizable(false);
	}
	
}
