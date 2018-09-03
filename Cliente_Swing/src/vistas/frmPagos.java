package vistas;

import java.util.List;

import javax.swing.JFrame;

import dto.ClienteDTO;
import dto.PagoDTO;

public class frmPagos extends JFrame{
	
	List<PagoDTO> pagos;
	
	public frmPagos(List<PagoDTO> p){
		pagos=p;
		Inicializar();
		this.setVisible(true);
	}
	
	private void Inicializar(){
		pnlPagos pnl= new pnlPagos(this,pagos);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Pagos");
		this.setBounds(250, 150, 400, 400);
		this.add(pnl);
		this.setResizable(false);
	}

}
