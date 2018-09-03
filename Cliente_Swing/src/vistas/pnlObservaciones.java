package vistas;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessDelegate.ClienteDelegate;
import dto.ClienteDTO;
import dto.ClienteObservacionDTO;
import dto.PagoDTO;
import exception.GenericRemoteException;

import javax.swing.JButton;
import javax.swing.JTextPane;

public class pnlObservaciones extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7805332039269254714L;
	JFrame principal;
	List<ClienteObservacionDTO> observaciones;
	String[] columnas = {"# Observacion","Descripción", "Fecha creación"};
	int i=0, row=-1, idO=-1;
	JTable tblObs;
	JScrollPane scrollBar;
	JTextPane textDesc;
	ClienteDTO cliente, clModif;
	JButton btnAgregar,btnModificar;
	DefaultTableModel model;
	String[][] datos;
	ImageIcon fondo;
	Timestamp timestamp;
	
	public pnlObservaciones (JFrame frm, ClienteDTO c) {
		principal=frm;
		cliente=c;
		observaciones=c.getObservaciones();
		Inicializar();
		AsignarEvetos();
		this.setVisible(true);
		
		
	}
	private void Inicializar() {
		this.setLayout(null);
		this.setSize(400, 400);
		
		datos= new String[observaciones.size()][3];
		for(ClienteObservacionDTO obsList : observaciones) {
			datos[i][0]=String.valueOf(obsList.getClienteObservacionId());
			datos[i][1]=obsList.getDescripcion();
			datos[i][2]=String.valueOf(obsList.getFecha());
			i++;
		}
		model= new DefaultTableModel(datos,columnas);
		tblObs= new JTable(model);
		tblObs.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = tblObs.getSelectedRow(); //que fila elegiste
				idO=Integer.parseInt(datos[row][0]); //toma el id de l fila seleccionada
				
				textDesc.setText(datos[row][1]);
		    	
			}
		});
		
		scrollBar = new JScrollPane();
		scrollBar.setViewportView(tblObs);
		scrollBar.setBounds(40, 15, 318, 133);
		this.add(scrollBar);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(40, 328, 130, 30);
		add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(228, 328, 130, 30);
		add(btnModificar);
		
		textDesc = new JTextPane();
		textDesc.setBounds(40, 161, 318, 140);
		add(textDesc);
	}
	
	private void AsignarEvetos() {
		btnAgregar.addActionListener(this);
		btnModificar.addActionListener(this);
	}


	public void actionPerformed(ActionEvent click) {
		if (click.getActionCommand().equals("Agregar")) {
			String aux=textDesc.getText();
			ClienteObservacionDTO auxO= new ClienteObservacionDTO();
			auxO.setDescripcion(aux);
			timestamp = new Timestamp(System.currentTimeMillis());
			auxO.setFecha(timestamp.toLocalDateTime().toLocalDate());
			
			cliente.getObservaciones().add(auxO);
			try {
				ClienteDelegate.getInstancia().modificarCliente(cliente);
				ClienteDTO c=ClienteDelegate.getInstancia().buscarCliente(cliente.getClienteId());
				cliente=c;
				JOptionPane.showMessageDialog(null,"Observación agregada con exito");
				JPanel pnlO= new pnlObservaciones(principal, cliente);
				principal.remove(this);
				principal.getContentPane().add(pnlO);
				principal.repaint();
			} catch (GenericRemoteException e) {
				JOptionPane.showMessageDialog(null,"No se pudo agregar la observación ");
				e.printStackTrace();
			}
			
		}
		else {
			if (click.getActionCommand().equals("Modificar")) {
				String desc =textDesc.getText();
				timestamp = new Timestamp(System.currentTimeMillis());
				
				cliente.setObservaciones(remplazarObs(idO,desc, timestamp));
				try {
					ClienteDelegate.getInstancia().modificarCliente(cliente);
					ClienteDTO c=ClienteDelegate.getInstancia().buscarCliente(cliente.getClienteId());
					cliente=c;
					
					JOptionPane.showMessageDialog(null,"Observación modificada con óxito");
					JPanel pnlO= new pnlObservaciones(principal, cliente);
					principal.remove(this);
					principal.getContentPane().add(pnlO);
					principal.repaint();
				} catch (GenericRemoteException e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"No se pudo modificar la observación");
				}
			}			
		}
	}
	
	public void paint(Graphics g) {
		fondo = new ImageIcon(getClass().getResource("/vistas/main3.jpg"));
		g.drawImage(fondo.getImage(), 0, 0, 400, 400, null);
		setOpaque(false);
		super.paint(g);
	}
	public List<ClienteObservacionDTO> remplazarObs(int idO,String descO, Timestamp t) {
		int index=0;
		ClienteObservacionDTO aux=new ClienteObservacionDTO();
		for(ClienteObservacionDTO o:cliente.getObservaciones()) {
			if(o.getClienteObservacionId()==idO) {
				aux.setClienteObservacionId(idO);
				aux.setDescripcion(descO);
				aux.setFecha(timestamp.toLocalDateTime().toLocalDate());
				cliente.getObservaciones().set(index, aux);
			}
			index++;
		}
		return cliente.getObservaciones();
	}
}
