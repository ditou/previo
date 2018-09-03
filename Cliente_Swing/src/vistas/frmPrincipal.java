package vistas;

import javax.swing.JFrame;

public class frmPrincipal extends JFrame{
	
	private static final long serialVersionUID = -963645121778562301L;

	public frmPrincipal(){
		Inicializar();
		this.setVisible(true);
	}
	
	private void Inicializar(){
		pnlMain pnl= new pnlMain(this);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Das Verrückte Lagerhaus");
		this.setBounds(170, 90, 1000, 600);
		this.add(pnl);
		this.setResizable(false);
	}
}
