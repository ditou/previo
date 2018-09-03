package vistas;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessDelegate.ClienteDelegate;
import businessDelegate.PedidoDelegate;
import dto.ClienteDTO;
import dto.PedidoDTO;
import exception.GenericRemoteException;
import java.awt.Color;

public class pnlAdminPedidos extends JPanel implements ActionListener{
	ImageIcon fondo;
	JFrame principal;
	JButton btnAtras,btnAprobar, btnRechazar;
	JLabel lblIdPedido, lblID,lblnombreC,lblObs,lblN, lblFechaC,lblF,lblMontoP,lblM, lblLimiteC, lblL, lblSaldoCCc, lblS, lblAclaracion;
	JTextField txtAclaracion;
	JTable tblPedidos;
	List<PedidoDTO> pedidos = new ArrayList<PedidoDTO>();
	String[][] datos;
	String[] columnas = {"# Pedido", "Cliente","Fecha Creación", "Monto Total"};
	int row=-1, i=0,idP, idC;
	DefaultTableModel model;
	JScrollPane scrollBar;
	frmObservaciones frmObs;
	ClienteDTO clAux;
	
	public pnlAdminPedidos(JFrame frm){
		principal= frm;
		principal.setTitle("Administración - Pedidos");
		Inicializar();
		asignarEventos();
		this.setVisible(true);
	}
	
	private void Inicializar(){
		this.setLayout(null);
		this.setSize(1000,600);
				
		try {
			pedidos= PedidoDelegate.getInstancia().buscarPedidosEnRevision();
		} 
		catch (GenericRemoteException e1) {
			e1.printStackTrace();
		}
			
		datos= new String[pedidos.size()][7];
		
		for(PedidoDTO pedidoList : pedidos) {
			datos[i][0]=String.valueOf(pedidoList.getPedidoId());
			datos[i][1]=pedidoList.getCliente().getNombre();
			datos[i][2]=String.valueOf(pedidoList.getFechaCreacion());
			datos[i][3]=String.valueOf(pedidoList.getTotal());
			datos[i][4]=String.valueOf(pedidoList.getCliente().getCuentaCorriente().getTotal());
			datos[i][5]=String.valueOf(pedidoList.getCliente().getCuentaCorriente().getLimiteCredito());
			datos[i][6]=String.valueOf(pedidoList.getCliente().getClienteId());

			i++;
		}

		model= new DefaultTableModel(datos,columnas);
		tblPedidos= new JTable(model);		
		scrollBar = new JScrollPane();
		scrollBar.setViewportView(tblPedidos);
		scrollBar.setBounds(35, 75, 425, 420);
		
		tblPedidos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = tblPedidos.getSelectedRow();
				idP=Integer.parseInt(datos[row][0]);
				
				lblID.setText(datos[row][0]);
				lblN.setText(datos[row][1]);
				lblF.setText(datos[row][2]);
				lblM.setText(datos[row][3]);
				lblL.setText(datos[row][5]);
				lblS.setText(datos[row][4]);
			}
		});
		lblIdPedido= new JLabel("# Pedido: ");
		lblIdPedido.setBounds(527, 110, 150, 30);
		lblID= new JLabel("");
		lblID.setBounds(731, 110, 150, 30);
		lblnombreC= new JLabel("Cliente:");
		lblnombreC.setBounds(527, 150, 150, 30);
		lblN= new JLabel("");
		lblN.setBounds(731, 150, 150, 30);
		lblFechaC= new JLabel("Fecha Pedido: ");
		lblFechaC.setBounds(527, 190, 150, 30);
		lblF= new JLabel("");
		lblF.setBounds(731, 190, 150, 30);
		lblMontoP= new JLabel("Monto Pedido: ");
		lblMontoP.setBounds(527, 230, 150, 30);
		lblM= new JLabel("");
		lblM.setBounds(731, 230, 150, 30);
		lblLimiteC= new JLabel("Límite Cliente: ");
		lblLimiteC.setBounds(527, 310, 150, 30);
		lblL= new JLabel("");
		lblL.setBounds(731, 310, 150, 30);
		lblSaldoCCc= new JLabel("Deuda actual Cliente: ");
		lblSaldoCCc.setBounds(527, 270, 150, 30);
		lblS= new JLabel("");
		lblS.setBounds(731, 270, 150, 30);
		lblAclaracion= new JLabel("Aclaración: ");
		lblAclaracion.setBounds(527, 350, 150, 30);
		
		txtAclaracion= new JTextField();
		txtAclaracion.setBounds(706, 350, 217, 71);
		
		btnAprobar= new JButton("Aprobar");
		btnAprobar.setBounds(753, 446, 150, 30);
		btnRechazar= new JButton("Rechazar");
		btnRechazar.setBounds(552, 446, 150, 30);
		btnAtras= new JButton("Atras");
		btnAtras.setBounds(20, 25, 80, 30);
		
		this.add(btnAprobar);
		this.add(btnRechazar);
		this.add(lblIdPedido);
		this.add(lblID);
		this.add(lblnombreC);
		this.add(lblN);
		this.add(lblFechaC);
		this.add(lblF);
		this.add(lblMontoP);
		this.add(lblM);
		this.add(lblLimiteC);
		this.add(lblL);
		this.add(lblSaldoCCc);
		this.add(lblS);
		this.add(lblAclaracion);
		this.add(txtAclaracion);
		this.add(btnAtras);
		this.add(scrollBar);
		
		lblObs = new JLabel("Ver Observaciones cliente");
		lblObs.setForeground(Color.BLUE);
		lblObs.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (row >= 0) {
					idC = Integer.parseInt(datos[row][6]);
					try {
						clAux = ClienteDelegate.getInstancia().buscarCliente(idC);
						frmObs = new frmObservaciones(clAux);
						frmObs.setVisible(true);
					} catch (GenericRemoteException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un pedido para poder ver las observaciones del cliente");
				}
			}
		});
		lblObs.setBounds(740, 68, 150, 30);
		add(lblObs);
		
	}
	private void asignarEventos(){
		btnAtras.addActionListener(this);
		btnAprobar.addActionListener(this);
		btnRechazar.addActionListener(this);
	}
	public void actionPerformed(ActionEvent click){
		if(click.getActionCommand().equals("Atras")){
			JPanel pnlA= new pnlAdministracion(principal);
			pnlA.setBounds(0, 0, 1000, 600);
			principal.remove(this);
			principal.getContentPane().add(pnlA);
			principal.repaint();
		}
		else {
			if(click.getActionCommand().equals("Aprobar")){
				if (row >= 0) {
					try {
						PedidoDelegate.getInstancia().aprobarPedido(idP, txtAclaracion.getText());
						JOptionPane.showMessageDialog(null, "Pedido aprobado con éxito");

						JPanel pnlAdmP = new pnlAdminPedidos(principal);
						pnlAdmP.setBounds(0, 0, 1000, 600);
						principal.remove(this);
						principal.getContentPane().add(pnlAdmP);
						principal.repaint();

					} catch (GenericRemoteException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "No se pudo aprobar el pedido");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un pedido antes de aprobar");
				}
			}
			else {
				if(click.getActionCommand().equals("Rechazar")){
					if (row >= 0) {
						try {
							PedidoDelegate.getInstancia().rechazarPedido(idP, txtAclaracion.getText());
							JOptionPane.showMessageDialog(null, "Pedido rechazado con éxito");

							JPanel pnlAdmP = new pnlAdminPedidos(principal);
							pnlAdmP.setBounds(0, 0, 1000, 600);
							principal.remove(this);
							principal.getContentPane().add(pnlAdmP);
							principal.repaint();
						} catch (GenericRemoteException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "No se pudo rechazar el pedido");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un pedido antes de rechazar");
					}
				}
			}
		}
	}
	
	public void paint(Graphics g){
		fondo=new ImageIcon(getClass().getResource("/vistas/adminP.jpg"));
		g.drawImage(fondo.getImage(),0, 0,994,580,null);
		//g.drawImage(fondo.getImage(),0, 0,1000,600,null);
		setOpaque(false);
		super.paint(g);
	}
}