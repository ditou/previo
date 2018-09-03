package vistas;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import dto.CuentaCorrienteItemDTO;

public class frmMovimientos extends JFrame{

	private static final long serialVersionUID = 1964051971521879933L;
	List<CuentaCorrienteItemDTO> movimientos;
	
	public frmMovimientos(List<CuentaCorrienteItemDTO> mov){
		movimientos= mov;
		Inicializar();
		this.setVisible(true);
	}
	
	private void Inicializar(){
		pnlMovmientos pnl= new pnlMovmientos(this,movimientos);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Movmientos");
		this.setBounds(250, 150, 600, 450);
		this.add(pnl);
		this.setResizable(false);
	}

}
