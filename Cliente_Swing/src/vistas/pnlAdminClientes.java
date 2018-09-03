package vistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessDelegate.ClienteDelegate;
import dto.ClienteDTO;
import dto.CuentaCorrienteDTO;
import exception.GenericRemoteException;

public class pnlAdminClientes extends JPanel implements ActionListener{
	ImageIcon fondo;
	JFrame principal,frmCuentaC,frmObs;
	JTable tblClientes;
	JButton btnBaja, btnAlta, btnModificar, btnAtras, btnBuscar;
	JLabel lblLimpiar, lblNombre, lblNroId, lblpswd, lblCuentaC, lblDireccion, lblTelefono, lblMail,lblOvservacion, lblCatF, lblLimiteCC;
	JTextField txtNombre, txtNroId, txtpswd, txtDireccion, txtTelefono, txtMail,txtLimiteCC;
	DefaultTableModel model, modeloBusqueda;	
	ClienteDTO cltAlta, clBaja,cLlenar, clModif,cBuscar,clAux;
	List<ClienteDTO> clientes= new ArrayList<ClienteDTO>();
	JComboBox cbxCatF;
	String[][] datos,datos2, csReturn;
	String[] categorias= {"CONSUMIDOR_FINAL", "RESPONSABLE_INSCRIPTO", "EXENTO", "MONOTRIBUTISTA"},
			columnas = {"# Cliente","Nombre", "Nro Identificación","Categoría Fiscal"};
	JScrollPane scrollBar;
	int idC, row=-1, i=0;
	CuentaCorrienteDTO ccAlta;
	
	public pnlAdminClientes(JFrame frm){
		principal=frm;
		principal.setTitle("Administración - Clientes");
		Inicializar();
		AsignarEvetos();
		this.setVisible(true);
	}
	
	private void Inicializar(){
		this.setLayout(null);
		this.setSize(1000,600);		
		
		try {
			clientes= ClienteDelegate.getInstancia().findAllClientes();
		} 
		catch (GenericRemoteException e1) {
			e1.printStackTrace();
		}
			
		datos= new String[clientes.size()][9];
		
		for(ClienteDTO clienteList : clientes) {
			datos[i][0]=String.valueOf(clienteList.getClienteId());
			datos[i][1]=clienteList.getNombre();
			datos[i][2]=clienteList.getNroIdentificacion();
			datos[i][3]=clienteList.getCategoriaFiscal();
			datos[i][4]=clienteList.getEmail();
			datos[i][5]=clienteList.getContrasenia();
			datos[i][6]=clienteList.getDireccion();
			datos[i][7]=clienteList.getTelefono();
			datos[i][8]=String.valueOf(clienteList.getCuentaCorriente().getLimiteCredito());
			i++;
		}
		model= new DefaultTableModel(datos,columnas);
		tblClientes= new JTable(model);		
		scrollBar = new JScrollPane();
		scrollBar.setViewportView(tblClientes);
		scrollBar.setBounds(35, 75, 425, 420);
		
		tblClientes.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = tblClientes.getSelectedRow(); //que fila elegiste
				idC=Integer.parseInt(datos[row][0]); //toma el id de l fila seleccionada
				
				txtNombre.setText(datos[row][1]);
		    	txtNroId.setText(datos[row][2]);
		    	txtpswd.setText(datos[row][5]);
		    	txtDireccion.setText(datos[row][6]);
		    	txtTelefono.setText(datos[row][7]);
		    	txtMail.setText(datos[row][4]);
		    	cbxCatF.setSelectedItem(datos[row][3]);
		    	txtLimiteCC.setText(datos[row][8]);
			}
		});
		cbxCatF= new JComboBox(categorias);
		cbxCatF.setBounds(761, 173, 150, 30);
		lblCatF= new JLabel("Categoróa fiscal: ");
		lblCatF.setBounds(531, 173, 130, 30);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setOpaque(false);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBounds(531,52 ,75 ,30 );
		btnBuscar.setForeground(Color.LIGHT_GRAY);
		btnAlta = new JButton("Alta");
		btnAlta.setOpaque(false);
		btnAlta.setContentAreaFilled(false);
		btnAlta.setBounds(498, 461, 131, 30);
		btnBaja = new JButton("Baja");
		btnBaja.setOpaque(false);
		btnBaja.setContentAreaFilled(false);
		btnBaja.setBounds(666, 461, 131, 30);
		btnModificar = new JButton("Modificar");
		btnModificar.setOpaque(false);
		btnModificar.setContentAreaFilled(false);
		btnModificar.setBounds(829, 461, 131, 30);
		btnAtras = new JButton("Atras");
		btnAtras.setBounds(20, 25, 80, 30);
		
		lblLimiteCC= new JLabel("Límite CC.: ");
		lblLimpiar = new JLabel("Limpiar");
		lblNombre = new JLabel("Nombre Cliente: ");
		lblNroId= new JLabel("Nro. Identificación*: "); 
		lblpswd = new JLabel("Contraseña: "); 
		lblCuentaC= new JLabel("Cuenta corriente ");
		lblDireccion = new JLabel("Dirección: ");
		lblTelefono= new JLabel("Teléfono: ");
		lblMail= new JLabel("E-mail*: ");
		lblOvservacion= new JLabel("Observaciones");
		
		txtLimiteCC = new JTextField();
		txtLimiteCC.setOpaque(false);
		txtNombre = new JTextField();
		txtNombre.setOpaque(false);
		txtNroId = new JTextField(); 
		txtNroId.setOpaque(false);
		txtpswd = new JTextField();
		txtpswd.setOpaque(false);
		txtDireccion = new JTextField();
		txtDireccion.setOpaque(false);
		txtTelefono = new JTextField();
		txtTelefono.setOpaque(false);
		txtMail = new JTextField();
		txtMail.setOpaque(false);
		
		lblLimpiar.setBounds(818, 52,80 ,30 );
		lblLimpiar.setForeground(Color.BLUE);
		lblLimpiar.addMouseListener(new MouseAdapter()   {   
	        public void mouseClicked(MouseEvent e)   
	        {   
	        	row=-1;
	        	txtNombre.setText(null);
	    		txtNroId.setText(null);
	    		txtpswd.setText(null);
	    		txtDireccion.setText(null);
	    		txtTelefono.setText(null);
	    		txtMail.setText(null);
	    		txtLimiteCC.setText(null);
	    		cbxCatF.setSelectedItem("CONSUMIDOR_FINAL");
	 
	    		datos2= new String[clientes.size()][9];
	    		int y=0;
	    		for(ClienteDTO clienteList : clientes) {
	    			datos2[y][0]=String.valueOf(clienteList.getClienteId());
	    			datos2[y][1]=clienteList.getNombre();
	    			datos2[y][2]=clienteList.getNroIdentificacion();
	    			datos2[y][3]=clienteList.getCategoriaFiscal();
	    			datos2[y][4]=clienteList.getEmail();
	    			datos2[y][5]=clienteList.getContrasenia();
	    			datos2[y][6]=clienteList.getDireccion();
	    			datos2[y][7]=clienteList.getTelefono();
	    			datos2[y][8]=String.valueOf(clienteList.getCuentaCorriente().getLimiteCredito());
	    			y++;
	    		}
	    		datos=datos2;
	    		model.setDataVector(datos, columnas);
	    		tblClientes.setModel(model);
	    		principal.repaint();
	        }
		});
		lblLimiteCC.setBounds(531,213 ,130 ,30 );
		lblNombre.setBounds(531,93 ,130 ,30 );
		lblNroId.setBounds(531,133 ,130 ,30 );
		lblMail.setBounds(531,254 ,130 ,30 );
		lblpswd.setBounds(531,294 ,130 ,30 );
		lblDireccion.setBounds(531,334 ,130 ,30 );
		lblTelefono.setBounds(531,374 ,130 ,30 );
		lblCuentaC.setBounds(578,415 ,130 ,30 );
		lblCuentaC.setForeground(Color.BLUE);
		lblCuentaC.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (row >= 0) {
					idC = Integer.parseInt(datos[row][0]);
					try {
						clAux = ClienteDelegate.getInstancia().buscarCliente(idC);
						frmCuentaC = new frmMovimientos(clAux.getCuentaCorriente().getItems());
						frmCuentaC.setVisible(true);
					} catch (GenericRemoteException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente para poder ver su cuenta corriente");
				}
			}
		});
		lblOvservacion.setBounds(768,415 ,130 ,30 );
		lblOvservacion.setForeground(Color.BLUE);
		lblOvservacion.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (row >= 0) {
					idC = Integer.parseInt(datos[row][0]);
					try {
						clAux = ClienteDelegate.getInstancia().buscarCliente(idC);
						frmObs = new frmObservaciones(clAux);
						frmObs.setVisible(true);
					} catch (GenericRemoteException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente para poder ver sus observaciones");
				}
			}
		});
		
		txtNombre.setBounds(761,93 ,150 ,30 );
		txtNroId.setBounds(761,133 ,150 ,30 );
		txtMail.setBounds(761,254 ,150 ,30 );
		txtpswd.setBounds(761,294 ,150 ,30 );
		txtDireccion.setBounds(761,334 ,150 ,30 );
		txtTelefono.setBounds(761,374 ,150 ,30 );
		txtLimiteCC.setBounds(761,213 ,150 ,30 );
				
		this.add(txtLimiteCC);
		this.add(lblLimiteCC);
		this.add(cbxCatF);
		this.add(lblCatF);
		this.add(btnBuscar);
		this.add(btnAlta);
		this.add(btnBaja);
		this.add(btnAtras);
		this.add(btnModificar);
		this.add(lblLimpiar);
		this.add(lblNombre);
		this.add(lblNroId);
		this.add(lblpswd);
		this.add(lblMail);
		this.add(lblDireccion);
		this.add(lblTelefono);
		this.add(lblCuentaC);
		this.add(lblOvservacion);
		this.add(txtNombre);
		this.add(txtNroId);
		this.add(txtMail);
		this.add(txtpswd);
		this.add(txtDireccion);
		this.add(txtTelefono);
		this.add(scrollBar);
	}
	
	private void AsignarEvetos(){
		btnAtras.addActionListener(this);
		btnBuscar.addActionListener(this);
		btnAlta.addActionListener(this);	
		btnModificar.addActionListener(this);
		btnBaja.addActionListener(this);
	}

	public void actionPerformed(ActionEvent click){
		
		if(click.getActionCommand().equals("Atras")){
			JPanel pnlA= new pnlAdministracion(principal);
			pnlA.setBounds(0, 0, 1000, 600);
			principal.remove(this);
			principal.getContentPane().add(pnlA);
			principal.repaint();
		}
		else{
			if(click.getActionCommand().equals("Alta")){
				if(!txtNombre.getText().equals("") && !txtpswd.getText().equals("") && !txtDireccion.getText().equals("") && !txtTelefono.getText().equals("")	&& !txtMail.getText().equals("")) {
					
					cltAlta= new ClienteDTO();
					ccAlta= new CuentaCorrienteDTO();
					
					cltAlta.setNombre(txtNombre.getText());
					cltAlta.setContrasenia(txtpswd.getText());
					cltAlta.setDireccion(txtDireccion.getText());
					cltAlta.setTelefono(txtTelefono.getText());
					cltAlta.setEmail(txtMail.getText());
					cltAlta.setNroIdentificacion(txtNroId.getText());
					cltAlta.setCategoriaFiscal(String.valueOf(cbxCatF.getSelectedItem()));
					ccAlta.setLimiteCredito(new BigDecimal(txtLimiteCC.getText()));
					cltAlta.setCuentaCorriente(ccAlta);
					cltAlta.setActivo(true);				
					if(!existeNroId(txtNroId.getText())){
						if(!existeEmail(txtMail.getText())) {
							try {
								int idAlta=ClienteDelegate.getInstancia().altaCliente(cltAlta);
								if(idAlta>0){
									JOptionPane.showMessageDialog(null,"Cliente dado de alta con éxito id: "+idAlta);
									JPanel pnlAdmC= new pnlAdminClientes(principal);
									pnlAdmC.setBounds(0, 0, 1000, 600);
									principal.remove(this);
									principal.getContentPane().add(pnlAdmC);
									principal.repaint();
								}
							} catch (GenericRemoteException e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(null,"No se pudo dar de alta el Cliente"); 
							}
						}
						else {
							JOptionPane.showMessageDialog(null,"El E-mail ingresado ya existe");
						}
					}
					else {
						JOptionPane.showMessageDialog(null,"El Nro. de identificación ingresado ya éxiste");
					}
					
				}
				else {
					JOptionPane.showMessageDialog(null,"Faltan completar campos"); 
				}
			}
			else{
				if(click.getActionCommand().equals("Modificar")){
					if(!txtNombre.getText().equals("") && !txtpswd.getText().equals("") && !txtDireccion.getText().equals("") && !txtTelefono.getText().equals("") && !txtMail.getText().equals("")) {
						idC=Integer.parseInt(datos[row][0]);
						try {
							clModif= ClienteDelegate.getInstancia().buscarCliente(idC);
							
							clModif.setNombre(txtNombre.getText());
							clModif.setContrasenia(txtpswd.getText());
							clModif.setDireccion(txtDireccion.getText());
							clModif.setTelefono(txtTelefono.getText());
							clModif.setEmail(txtMail.getText());
							clModif.setNroIdentificacion(txtNroId.getText());
							clModif.setCategoriaFiscal(String.valueOf(cbxCatF.getSelectedItem()));
							clModif.setActivo(true);
							clModif.getCuentaCorriente().setLimiteCredito(new BigDecimal(txtLimiteCC.getText()));
							ClienteDelegate.getInstancia().modificarCliente(clModif);
							
							JOptionPane.showMessageDialog(null, "Cliente modificado con éxito");
							JPanel pnlAdmC= new pnlAdminClientes(principal);
							pnlAdmC.setBounds(0, 0, 1000, 600);
							principal.remove(this);
							principal.getContentPane().add(pnlAdmC);
							principal.repaint();
							
						} catch (GenericRemoteException e) {
							JOptionPane.showMessageDialog(null, "No se pudo modificar el cliente");
							e.printStackTrace();
						}	
					}
				}
				else {
					if(click.getActionCommand().equals("Baja")){
						if(row>=0) {
							idC=Integer.parseInt(datos[row][0]);
							try {
	
								ClienteDelegate.getInstancia().bajaCliente(idC);
								
								JOptionPane.showMessageDialog(null, "Cliente dado de baja con éxito");
								
								JPanel pnlAdmC= new pnlAdminClientes(principal);
								pnlAdmC.setBounds(0, 0, 1000, 600);
								principal.remove(this);
								principal.getContentPane().add(pnlAdmC);
								principal.repaint();
								
							} catch (GenericRemoteException e) {
								JOptionPane.showMessageDialog(null, "No se pudo dar de baja el cliente");
								e.printStackTrace();
							}							
						}
						else {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente para dar de baja");
						}
					}
					else {
						if(click.getActionCommand().equals("Buscar")){
			
							String resp ="";
							resp = JOptionPane.showInputDialog("Escriba el Nro. de Id. a buscar: ");
							if(resp!="") {
								String [][] resBusqueda=buscarClientes(datos,resp);
								if (resBusqueda != null) {
									modeloBusqueda = new DefaultTableModel(resBusqueda, columnas);
									tblClientes.setModel(modeloBusqueda);
									datos = resBusqueda;
									principal.repaint();
								}
								else {
									JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Debe ingresar un nÃºmero de identificación para poder buscar");
							}						
						}
					}
				}
			}
		}
	}

	public void paint(Graphics g){
		fondo=new ImageIcon(getClass().getResource("/vistas/adminC3.jpg"));
		g.drawImage(fondo.getImage(),0, 0,994,580,null);
		//g.drawImage(fondo.getImage(),0, 0,1000,600,null);
		setOpaque(false);
		super.paint(g);
	}
	
	public String[][] buscarClientes(String[][] datos, String nroIdentif) {
		
		int filas=buscarCantC(datos, nroIdentif);
		if (filas > 0) {
			csReturn = new String[filas][9];
			int tamD = datos.length;
			int k = 0;
			for (int o = 0; o < tamD; o++) {
				if( (datos[o][2]).trim().equals(nroIdentif.trim())){
				//if ((Integer.parseInt(datos[o][2])) == (Integer.parseInt(nroIdentif))) {
					csReturn[k][0] = datos[o][0];
					csReturn[k][1] = datos[o][1];
					csReturn[k][2] = datos[o][2];
					csReturn[k][3] = datos[o][3];
					csReturn[k][4] = datos[o][4];
					csReturn[k][5] = datos[o][5];
					csReturn[k][6] = datos[o][6];
					csReturn[k][7] = datos[o][7];
					csReturn[k][8] = datos[o][8];
					k++;
				}
			}
		}
		else {
			csReturn =null;
		}
		return csReturn;
	}
	
	public int buscarCantC(String[][] datos, String nroIdentif) {
		
		int cant=0;
		int filas = datos.length;
		for(int o=0; o<filas;o++) {
			if((Integer.parseInt(datos[o][2])) == (Integer.parseInt(nroIdentif)) ) {
				cant++;		
			}
		}
		return cant;
	}
	public boolean existeNroId(String n) {
		boolean existe=false;
		int q,tamD;
		tamD=datos.length;
		for(q=0;q<tamD;q++) {
			if( (datos[q][2]).trim().equals(n.trim())){
				existe= true;
			}
		}
		return existe;
	}
	public boolean existeEmail(String e) {
		boolean existe=false;
		int q,tamD;
		tamD=datos.length;
		for(q=0;q<tamD;q++) {
			if( (datos[q][4]).trim().equals(e.trim())){
				existe= true;
			}
		}
		return existe;
	}
}
