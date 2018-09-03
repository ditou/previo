package vistas;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.ClienteDTO;
import dto.CuentaCorrienteItemDTO;
import dto.PagoDTO;

public class pnlMovmientos extends JPanel{
	
	ImageIcon fondo;
	JFrame principal;
	List<CuentaCorrienteItemDTO> movimientos;
	JTable tblMovmientos;
	JScrollPane scrollBar;
	DefaultTableModel model;
	String[][] datos;
	CuentaCorrienteItemDTO auxPnlPagos;
	String[] columnas = {"# Movimiento","# Factura","Factura Tipo","Fecha Factura", "Monto total","Saldo"};
	int i=0, row=-1, idM;
	
	public pnlMovmientos(JFrame frm,List<CuentaCorrienteItemDTO> mov) {
		principal=frm;
		movimientos= mov;
		Inicializar();
		this.setVisible(true);
	}
	private void Inicializar(){
		this.setLayout(null);
		this.setSize(600, 450);
		
		datos= new String[movimientos.size()][6];
		for(CuentaCorrienteItemDTO itemCCList : movimientos) {
			datos[i][0]=String.valueOf(itemCCList.getCuentaCorrienteItemId());
			datos[i][1]=String.valueOf(itemCCList.getFactura().getFacturaId());
			datos[i][2]=itemCCList.getFactura().getLetra();
			datos[i][3]=String.valueOf(itemCCList.getFecha());
			datos[i][4]=String.valueOf(itemCCList.getFactura().getTotal());
			datos[i][5]=String.valueOf(itemCCList.getFactura().getSaldo());
			i++;
		}
		model= new DefaultTableModel(datos,columnas);
		tblMovmientos= new JTable(model);
		tblMovmientos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = tblMovmientos.getSelectedRow();
				if (row >= 0) {
					idM = Integer.parseInt(datos[row][0]);
					auxPnlPagos = buscarItem(idM);
					abrirPagos(auxPnlPagos.getPagos());
				}
			}
		});
		scrollBar = new JScrollPane();
		scrollBar.setViewportView(tblMovmientos);
		scrollBar.setBounds(20, 15, 550, 390);
		
		this.add(scrollBar);
	}
	
	public void paint(Graphics g){
	fondo=new ImageIcon(getClass().getResource("/vistas/main3.jpg"));
	g.drawImage(fondo.getImage(),0, 0,600, 450,null);
	setOpaque(false);
	super.paint(g);
	}
	
	public void abrirPagos(List<PagoDTO> pagos) {
		frmPagos frmp= new frmPagos(pagos);
		frmp.setVisible(true);
	}
	public CuentaCorrienteItemDTO buscarItem(int id) {
		
		CuentaCorrienteItemDTO aux= null;;
		for(CuentaCorrienteItemDTO item: movimientos) {
			if(item.getCuentaCorrienteItemId()==id) {
				aux=item;
			}
		}
		return aux;
	}
}
