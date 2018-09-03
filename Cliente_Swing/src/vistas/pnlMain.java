package vistas;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class pnlMain extends JPanel implements ActionListener{
	JFrame principal;
	ImageIcon fondo;
	JButton btnIngresar;
	JLabel lblArea, lblCopyright;
	JComboBox cbxArea;
	int opcionCBX;
	
	public pnlMain(JFrame frmPrincipal){
		principal= frmPrincipal;
		Inicializar();
		AsignarEventos();
		this.setVisible(true);
	}
	
	private void Inicializar(){
		this.setLayout(null);
		this.setSize(1000, 600);	
		String[] areas = {"Administración"};

		btnIngresar= new JButton("Ingresar");
		btnIngresar.setBounds(597, 337, 131, 30);
		btnIngresar.setOpaque(false);
		btnIngresar.setContentAreaFilled(false);
		lblArea = new JLabel("Área: ");
		lblArea.setBounds(550, 270, 131, 30);
		lblCopyright = new JLabel("2018 All Right Recerved");
		lblCopyright.setBounds(805, 15, 150, 30);
		
		cbxArea= new JComboBox(areas);
		cbxArea.setBounds(640, 270, 131, 30);
		
		this.add(lblArea);	
		this.add(btnIngresar);
		this.add(cbxArea);
		this.add(lblCopyright);
	}
	private void AsignarEventos(){
		btnIngresar.addActionListener(this);
	}
	public void actionPerformed(ActionEvent click){
		if(click.getActionCommand().equals("Ingresar")){
			opcionCBX=OpCBX();
			
			if (opcionCBX == 3) {
				JPanel pnlA = new pnlAdministracion(principal);
				pnlA.setBounds(0, 0, 1000, 600);
				principal.remove(this);
				principal.add(pnlA);
				principal.repaint();
			} 
		}
	}
	
	private int OpCBX() {
		String aux;
		aux = cbxArea.getSelectedItem().toString();
		
		if (aux == "Administración") {
			return 3;
		} else {
			return 0;
		}
	}
	
	public void paint(Graphics g){
	fondo=new ImageIcon(getClass().getResource("/vistas/main3.jpg"));
	g.drawImage(fondo.getImage(),0, 0,1000,600,null);
	setOpaque(false);
	super.paint(g);
	}
}
