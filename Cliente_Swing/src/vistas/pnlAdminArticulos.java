package vistas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
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

import businessDelegate.ArticuloDelegate;
import dto.ArticuloDTO;
import exception.GenericRemoteException;

public class pnlAdminArticulos extends JPanel implements ActionListener {

	private static final long serialVersionUID = 8124324534423655238L;

	ImageIcon fondo;
	JFrame principal;
	JTable tblArticulos;
	JButton btnBaja, btnAlta, btnModificar, btnAtras, btnBuscar;
	JLabel lblLimpiar, lblCodBarras, lblDescripcion, lblTamanio, lblUnidad, lblPrecioV, lblCantFCompra, lblCantOcupaU,
			lblPresentacion;
	JTextField txtCodBarras, txtDescripcion, txtTamanio, txtUnidad, txtPrecioV, txtCantFCompra, txtCantOcupaU,
			txtPresentacion;
	DefaultTableModel model, modeloBusqueda;
	List<ArticuloDTO> articulos;
	ArticuloDTO artTabla, artAlta, artBaja, artModif;
	String[][] datos, datos2, csReturn;
	String[] columnas = { "# Artículo", "Cod. de Barras", "Descripción", "Precio venta" };
	static DateTimeFormatter formatF = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	int cantC, i = 0, idA, row = -1;
	JScrollPane scrollBar;

	public pnlAdminArticulos(JFrame frm) {
		principal = frm;
		principal.setTitle("Administración - Artículos");
		Inicializar();
		AsignarEvetos();
		this.setVisible(true);
	}

	private void Inicializar() {

		this.setLayout(null);
		this.setSize(1000, 600);

		try {
			articulos = ArticuloDelegate.getInstancia().findAllArticulos();
		} catch (GenericRemoteException e) {
			e.printStackTrace();
		}

		datos = new String[articulos.size()][9];

		for (ArticuloDTO articuloList : articulos) {
			datos[i][0] = String.valueOf(articuloList.getArticuloId());
			datos[i][1] = articuloList.getCodigoBarras();
			datos[i][2] = articuloList.getDescripcion();
			datos[i][3] = String.valueOf(articuloList.getPrecioVenta());
			datos[i][4] = articuloList.getPresentacion();
			datos[i][5] = String.valueOf(articuloList.getTamanio());
			datos[i][6] = articuloList.getUnidad();
			datos[i][7] = String.valueOf(articuloList.getCantFijaCompra());
			datos[i][8] = String.valueOf(articuloList.getCantOcupaUbicacion());

			i++;
		}

		model = new DefaultTableModel(datos, columnas);
		tblArticulos = new JTable(model);
		scrollBar = new JScrollPane();
		scrollBar.setViewportView(tblArticulos);
		scrollBar.setBounds(35, 75, 425, 420);

		tblArticulos.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = tblArticulos.getSelectedRow();
				idA = Integer.parseInt(datos[row][0]);

				txtCodBarras.setText(datos[row][1]);
				txtDescripcion.setText(datos[row][2]);
				txtPrecioV.setText(datos[row][3]);
				txtPresentacion.setText(datos[row][4]);
				txtTamanio.setText(datos[row][5]);
				txtUnidad.setText(datos[row][6]);
				txtCantFCompra.setText(datos[row][7]);
				txtCantOcupaU.setText(datos[row][8]);
			}
		});

		btnBuscar = new JButton("Buscar");
		btnBuscar.setOpaque(false);
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setBounds(529, 51, 75, 30);
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

		lblLimpiar = new JLabel("Limpiar");
		lblCodBarras = new JLabel("Código de Barras: ");
		lblDescripcion = new JLabel("Descripción: ");
		lblTamanio = new JLabel("Tamaño: ");
		lblUnidad = new JLabel("Unidad artículo: ");
		lblPrecioV = new JLabel("Precio venta: ");
		lblCantFCompra = new JLabel("Cantidad fija compra: ");
		lblCantOcupaU = new JLabel("Cantidad ocupa ubicación: ");
		lblPresentacion = new JLabel("Presentación: ");

		txtCodBarras = new JTextField();
		txtCodBarras.setOpaque(false);
		txtPresentacion = new JTextField();
		txtPresentacion.setOpaque(false);
		txtDescripcion = new JTextField();
		txtDescripcion.setOpaque(false);
		txtTamanio = new JTextField();
		txtTamanio.setOpaque(false);
		txtUnidad = new JTextField();
		txtUnidad.setOpaque(false);
		txtPrecioV = new JTextField();
		txtPrecioV.setOpaque(false);
		txtCantFCompra = new JTextField();
		txtCantFCompra.setOpaque(false);
		txtCantOcupaU = new JTextField();
		txtCantOcupaU.setOpaque(false);

		lblLimpiar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				row = -1;
				txtCodBarras.setText(null);
				txtDescripcion.setText(null);
				txtTamanio.setText(null);
				txtUnidad.setText(null);
				txtPrecioV.setText(null);
				txtCantFCompra.setText(null);
				txtPresentacion.setText(null);
				txtCantOcupaU.setText(null);

				datos2 = new String[articulos.size()][9];
				int y = 0;
				for (ArticuloDTO articuloList : articulos) {
					datos2[y][0] = String.valueOf(articuloList.getArticuloId());
					datos2[y][1] = articuloList.getCodigoBarras();
					datos2[y][2] = articuloList.getDescripcion();
					datos2[y][3] = String.valueOf(articuloList.getPrecioVenta());
					datos2[y][4] = articuloList.getPresentacion();
					datos2[y][5] = String.valueOf(articuloList.getTamanio());
					datos2[y][6] = articuloList.getUnidad();
					datos2[y][7] = String.valueOf(articuloList.getCantFijaCompra());
					datos2[y][8] = String.valueOf(articuloList.getCantOcupaUbicacion());

					y++;
				}
				datos = datos2;
				model.setDataVector(datos, columnas);
				tblArticulos.setModel(model);
				principal.repaint();
			}
		});

		lblCodBarras.setBounds(529, 105, 130, 30);
		lblDescripcion.setBounds(529, 145, 130, 30);
		lblTamanio.setBounds(529, 185, 130, 30);
		lblUnidad.setBounds(529, 225, 130, 30);
		lblPrecioV.setBounds(529, 265, 130, 30);
		lblPresentacion.setBounds(529, 305, 130, 30);
		lblCantFCompra.setBounds(529, 345, 130, 30);
		lblCantOcupaU.setBounds(529, 385, 170, 30);

		txtCodBarras.setBounds(769, 105, 150, 30);
		txtDescripcion.setBounds(769, 145, 150, 30);
		txtTamanio.setBounds(769, 185, 150, 30);
		txtUnidad.setBounds(769, 225, 150, 30);
		txtPrecioV.setBounds(769, 265, 150, 30);
		txtPresentacion.setBounds(769, 305, 150, 30);
		txtCantFCompra.setBounds(769, 345, 150, 30);
		txtCantOcupaU.setBounds(769, 385, 150, 30);

		lblLimpiar.setBounds(816, 51, 80, 30);
		lblLimpiar.setForeground(Color.BLUE);

		this.add(btnAlta);
		this.add(btnBaja);
		this.add(btnAtras);
		this.add(btnModificar);
		this.add(btnBuscar);
		this.add(scrollBar);
		this.add(lblLimpiar);
		this.add(lblCodBarras);
		this.add(lblDescripcion);
		this.add(lblTamanio);
		this.add(lblUnidad);
		this.add(lblPrecioV);
		this.add(lblCantFCompra);
		this.add(lblCantOcupaU);
		this.add(txtCodBarras);
		this.add(txtDescripcion);
		this.add(txtTamanio);
		this.add(txtUnidad);
		this.add(txtPrecioV);
		this.add(txtCantFCompra);
		this.add(txtCantOcupaU);
		this.add(txtCantOcupaU);
		this.add(lblPresentacion);
		this.add(txtPresentacion);
	}

	private void AsignarEvetos() {
		btnAtras.addActionListener(this);
		btnAlta.addActionListener(this);
		btnModificar.addActionListener(this);
		btnBaja.addActionListener(this);
		btnBuscar.addActionListener(this);
	}

	public void actionPerformed(ActionEvent click) {
		if (click.getActionCommand().equals("Atras")) {
			JPanel pnlA = new pnlAdministracion(principal);
			pnlA.setBounds(0, 0, 1000, 600);
			principal.remove(this);
			principal.getContentPane().add(pnlA);
			principal.repaint();
		} else {
			if (click.getActionCommand().equals("Alta")) {
				if (!txtCodBarras.getText().equals("") && !txtDescripcion.getText().equals("")
						&& !txtTamanio.getText().equals("") && !txtUnidad.getText().equals("")
						&& !txtPrecioV.getText().equals("") && !txtCantFCompra.getText().equals("")
						&& !txtCantOcupaU.getText().equals("") && !txtPresentacion.getText().equals("")) {
					
					if (isNumeric(txtTamanio.getText())) {
						if (isNumeric(txtCantOcupaU.getText())) {
							if (isNumeric(txtCantFCompra.getText())) {
								artAlta = new ArticuloDTO();
								artAlta.setCodigoBarras(txtCodBarras.getText());
								artAlta.setDescripcion(txtDescripcion.getText());
								artAlta.setTamanio(Integer.parseInt(txtTamanio.getText()));
								artAlta.setUnidad(txtUnidad.getText());
								artAlta.setPrecioVenta(new BigDecimal(txtPrecioV.getText()));
								artAlta.setCantFijaCompra(Integer.parseInt(txtCantFCompra.getText()));
								artAlta.setCantOcupaUbicacion(Integer.parseInt(txtCantOcupaU.getText()));
								artAlta.setPresentacion(txtPresentacion.getText());
							} else {
								JOptionPane.showMessageDialog(null, "La cantidad fija compra ingresada no es valida");
							}
						} else {
							JOptionPane.showMessageDialog(null, "La cantidad ocupa ubicación ingresada no es valida");
						}
					} else {
						JOptionPane.showMessageDialog(null, "El tamaño ingresado no es valido");
					}
			
					if (!existeCodB(txtCodBarras.getText()) && isNumeric(txtTamanio.getText()) && isNumeric(txtCantOcupaU.getText()) 
							&& isNumeric(txtCantFCompra.getText())) {
						try {
							ArticuloDelegate.getInstancia().altaArticulo(artAlta);

							JPanel pnlAdmA = new pnlAdminArticulos(principal);
							pnlAdmA.setBounds(0, 0, 1000, 600);
							principal.remove(this);
							principal.getContentPane().add(pnlAdmA);
							principal.repaint();

							JOptionPane.showMessageDialog(null, "Artículo dado de alta con éxito");
						} catch (GenericRemoteException e) {
							e.printStackTrace();
							JOptionPane.showMessageDialog(null, "No se pudo dar de alta el artículo");
						}
					} else {
						JOptionPane.showMessageDialog(null, "El Código de Barras ingresado ya existe");
					}
				} else {
					JOptionPane.showMessageDialog(null, "Faltan completar campos");
				}
			} else {
				if (click.getActionCommand().equals("Modificar")) {
					if (row < 0) {
						JOptionPane.showMessageDialog(null, "Seleccione un artículo antes de modificar");
					} else {
						if (!txtCodBarras.getText().equals("") && !txtDescripcion.getText().equals("")
								&& !txtTamanio.getText().equals("") && !txtUnidad.getText().equals("")
								&& !txtPrecioV.getText().equals("") && !txtCantFCompra.getText().equals("")
								&& !txtCantOcupaU.getText().equals("") && !txtPresentacion.getText().equals("")) {

							idA = Integer.parseInt(datos[row][0]);
							try {
								artModif = ArticuloDelegate.getInstancia().buscarArticulo(idA);

								artModif.setCodigoBarras(txtCodBarras.getText());
								artModif.setDescripcion(txtDescripcion.getText());
								artModif.setTamanio(Integer.parseInt(txtTamanio.getText()));
								artModif.setUnidad(txtUnidad.getText());
								artModif.setPrecioVenta(new BigDecimal(txtPrecioV.getText()));
								artModif.setCantFijaCompra(Integer.parseInt(txtCantFCompra.getText()));
								artModif.setCantOcupaUbicacion(Integer.parseInt(txtCantOcupaU.getText()));
								artModif.setPresentacion(txtPresentacion.getText());

								ArticuloDelegate.getInstancia().modificarArticulo(artModif);

								JOptionPane.showMessageDialog(null, "Artículo modificado con éxito");
								JPanel pnlAdmA = new pnlAdminArticulos(principal);
								pnlAdmA.setBounds(0, 0, 1000, 600);
								principal.remove(this);
								principal.getContentPane().add(pnlAdmA);
								principal.repaint();

							} catch (GenericRemoteException e) {
								JOptionPane.showMessageDialog(null, "No se pudo modificar el artículo");
								e.printStackTrace();
							}
						}
					}
				} else {
					if (click.getActionCommand().equals("Baja")) {
						if (row >= 0) {
							idA = Integer.parseInt(datos[row][0]);

							try {

								ArticuloDelegate.getInstancia().bajaArticulo(idA);

								JOptionPane.showMessageDialog(null, "Artículo dado de baja con éxito");

								JPanel pnlAdmA = new pnlAdminArticulos(principal);
								pnlAdmA.setBounds(0, 0, 1000, 600);
								principal.remove(this);
								principal.getContentPane().add(pnlAdmA);
								principal.repaint();

							} catch (GenericRemoteException e) {
								JOptionPane.showMessageDialog(null, "No se pudo dar de baja el artículo");
								e.printStackTrace();
							}
						} else {
							JOptionPane.showMessageDialog(null, "Debe seleccionar un artículo para dar de baja");
						}
					} else {
						if (click.getActionCommand().equals("Buscar")) {
							String resp = "";
							resp = JOptionPane.showInputDialog("Escriba el Código de Barras a buscar: ");
							if (resp != "") {
								String[][] resBusqueda = buscarArticulos(datos, resp);
								if (resBusqueda != null) {
									modeloBusqueda = new DefaultTableModel(resBusqueda, columnas);
									tblArticulos.setModel(modeloBusqueda);
									datos = resBusqueda;
									principal.repaint();
								} else {
									JOptionPane.showMessageDialog(null, "No se encontraron coincidencias");
								}
							} else {
								JOptionPane.showMessageDialog(null,
										"Debe ingresar un Código de Barras para poder buscar");
							}
						}
					}
				}
			}
		}
	}

	public void paint(Graphics g) {
		fondo = new ImageIcon(getClass().getResource("/vistas/adminA.jpg"));
		g.drawImage(fondo.getImage(), 0, 0, 994, 580, null);
		// g.drawImage(fondo.getImage(),0, 0,1000,600,null);
		setOpaque(false);
		super.paint(g);
	}

	public String[][] buscarArticulos(String[][] datos, String nroCodBarras) {

		int filas = buscarCantA(datos, nroCodBarras);
		if (filas > 0) {
			csReturn = new String[filas][9];
			int tamD = datos.length;
			int k = 0;
			for (int o = 0; o < tamD; o++) {
				if ((datos[o][1]).trim().equals(nroCodBarras.trim())) {
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
		} else {
			csReturn = null;
		}
		return csReturn;
	}

	public int buscarCantA(String[][] datos, String nroCodBarras) {

		int cant = 0;
		int filas = datos.length;
		for (int o = 0; o < filas; o++) {
			if ((datos[o][1]).trim().equals(nroCodBarras.trim())) {
				cant++;
			}
		}
		return cant;
	}

	public boolean existeCodB(String e) {
		boolean existe = false;
		int q, tamD;
		tamD = datos.length;
		for (q = 0; q < tamD; q++) {
			if ((datos[q][1]).trim().equals(e.trim())) {
				existe = true;
			}
		}
		return existe;
	}
	public static boolean isNumeric(String str)
	{
	  return str.matches("-?\\d+(\\.\\d+)?");  
	}
}
