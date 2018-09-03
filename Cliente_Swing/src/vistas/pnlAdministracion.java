package vistas;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class pnlAdministracion extends JPanel implements ActionListener {
	ImageIcon fondo;
	JFrame principal;
	JButton btnClientes, btnArticulos, btnPedidos, btnAtras;
	
	public pnlAdministracion(JFrame frm){
		principal=frm;
		principal.setTitle("Administración");
		Inicializar();
		asignarEventos();
		this.setVisible(true);
	}
	
	private void Inicializar(){
		this.setLayout(null);
		this.setSize(1000, 600);
		btnClientes = new JButton("Clientes");
		btnClientes.setOpaque(false);
		btnClientes.setContentAreaFilled(false);
		btnClientes.setBounds(137, 225, 131, 30);
		
		btnArticulos = new JButton("Artículos");
		btnArticulos.setOpaque(false);
		btnArticulos.setContentAreaFilled(false);
		btnArticulos.setBounds(434, 225, 131, 30);
		
		btnPedidos = new JButton("Pedidos");
		btnPedidos.setOpaque(false);
		btnPedidos.setContentAreaFilled(false);
		btnPedidos.setBounds(725, 225, 131, 30);
		
		btnAtras = new JButton("Atras");
		btnAtras.setBounds(60, 460, 131, 30);
		
		this.add(btnClientes);
		this.add(btnArticulos);
		this.add(btnPedidos);
		this.add(btnAtras);
	}
	
	private void asignarEventos(){
		btnAtras.addActionListener(this);
		btnClientes.addActionListener(this);
		btnArticulos.addActionListener(this);
		btnPedidos.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent click){
		if(click.getActionCommand().equals("Atras")){
			JPanel pnlM= new pnlMain(principal);
			pnlM.setBounds(0, 0, 1000, 600);
			principal.remove(this);
			principal.add(pnlM);
			principal.repaint();
		}
		else{
			if(click.getActionCommand().equals("Clientes")){
				JPanel pnlAdmC= new pnlAdminClientes(principal);
				pnlAdmC.setBounds(0, 0, 1000, 600);
				principal.remove(this);
				principal.add(pnlAdmC);
				principal.repaint();
				}
			else{
				if(click.getActionCommand().equals("Artículos")){
					JPanel pnlAdmA= new pnlAdminArticulos(principal);
					pnlAdmA.setBounds(0, 0, 1000, 600);
					principal.remove(this);
					principal.add(pnlAdmA);
					principal.repaint();
				}
				else{
					if(click.getActionCommand().equals("Pedidos")){
						JPanel pnlAdmP= new pnlAdminPedidos(principal);
						pnlAdmP.setBounds(0, 0, 1000, 600);
						principal.remove(this);
						principal.add(pnlAdmP);
						principal.repaint();
					}
				}
			}
		}
	}
	
	public void paint(Graphics g){
		fondo=new ImageIcon(getClass().getResource("/vistas/admin.jpg"));
		g.drawImage(fondo.getImage(),0, 0,1000,600,null);
		setOpaque(false);
		super.paint(g);
	}
}

