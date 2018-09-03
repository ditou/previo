package vistas;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.CuentaCorrienteItemDTO;
import dto.PagoDTO;

public class pnlPagos extends JPanel{
	
	JFrame principal;
	List<PagoDTO> pagos;
	String[] columnas = {"# Pago","Fecha pago","Monto"};
	int i=0, row=-1, idM;
	JTable tblPagos;
	JScrollPane scrollBar;
	DefaultTableModel model;
	String[][] datos;
	ImageIcon fondo;
	
	public pnlPagos(JFrame frm, List<PagoDTO> p) {
		principal=frm;
		pagos=p;
		Inicializar();
		this.setVisible(true);
		
	}
	private void Inicializar() {
		this.setLayout(null);
		this.setSize(600, 450);
		
		datos= new String[pagos.size()][3];
		
		for(PagoDTO pagoList : pagos) {
			datos[i][0]=String.valueOf(pagoList.getPagoId());
			datos[i][1]=String.valueOf(pagoList.getFecha());
			datos[i][2]=String.valueOf(pagoList.getTotal());
	
			i++;
		}
		model= new DefaultTableModel(datos,columnas);
		tblPagos= new JTable(model);
			
		scrollBar = new JScrollPane();
		scrollBar.setViewportView(tblPagos);
		scrollBar.setBounds(20, 15, 360, 350);
		
		this.add(scrollBar);
	}
	
	public void paint(Graphics g) {
		fondo = new ImageIcon(getClass().getResource("/vistas/main3.jpg"));
		g.drawImage(fondo.getImage(), 0, 0, 400, 400, null);
		setOpaque(false);
		super.paint(g);
	}

}
