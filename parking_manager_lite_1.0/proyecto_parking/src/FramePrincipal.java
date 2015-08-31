import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

/**
 * Esta clase define la ventana principal de la aplicación.
 *
 * @author: Artzai San José & Borja Muñoz
 *
 * @version: 29/04/2014 v1.0
*/
@SuppressWarnings("serial")
public class FramePrincipal extends JFrame implements ActionListener, MouseListener, WindowListener {
	
	//Atributos de clase
	private JButton btnEntrada, btnAdelante, btnDetras, btnCerrar, btnBuscar;
	private JLabel labelPlanta, labelPiso1, labelPiso2, labelPiso3;
	private static JLabel labelCandado1, labelCandado2, labelCandado3, arrayLabels[][], labelSideMenu;
	private JPanel panel1, panel2, panel3;
	private int pisoActual = 1, pisoAnterior = 1;
	private static ImageIcon candadoAbierto, candadoCerrado, imagenCoche;
	private Cursor cursorMano;

	/**
     * Constructor que define las propiedades de la ventana principal,
  	 *
     * asigna el tamaño y la posición de la ventana.
	 *
	 * Llama al método 'colocarComponentes'.    
    */
	public FramePrincipal() {
		this.setBounds(200, 200, 905, 625);
		this.setLayout(null);
		this.setTitle("Parking Manager Lite (v1.0)");
		this.colocarComponentes();
		GestorParking.llenarDesdeBD();
		try {
			this.setIconImage(new ImageIcon(ImageIO.read(new File("src/imagenes/senal_parking.png"))).getImage());
		}
		catch (IOException e) {		
			e.printStackTrace();
		}
		this.addWindowListener(this);
	}
	
	/**
     * Método donde se instancian y colocan todos los componentes que contendrá el formulario de datos. 
    */
	private void colocarComponentes() {
		candadoAbierto = new ImageIcon("src/imagenes/open_lock.png");
		candadoCerrado = new ImageIcon("src/imagenes/closed_lock.png");
		imagenCoche = new ImageIcon("src/imagenes/coche.png");
		cursorMano = new Cursor(Cursor.HAND_CURSOR);
		
		//Cada piso del parking irá representado en un JPanel distinto
		panel1 = new JPanel();		
		panel1.setBounds(132, 0, 767, 600);
		panel1.setLayout(null);			
		this.add(panel1);
		
		panel2 = new JPanel();
		panel2.setBounds(132, 0, 767, 600);
		panel2.setLayout(null);
		this.add(panel2);
		
		panel3 = new JPanel();
		panel3.setBounds(132, 0, 767, 600);
		panel3.setLayout(null);		
		this.add(panel3);		
		
		try {			
			labelSideMenu = new JLabel(new ImageIcon(ImageIO.read(new File("src/imagenes/sideMenu_clean.png"))));
			
			labelPiso1 = new JLabel(new ImageIcon(ImageIO.read(new File("src/imagenes/background_pane1.png"))));
			labelPiso2 = new JLabel(new ImageIcon(ImageIO.read(new File("src/imagenes/background_pane2.png"))));
			labelPiso3 = new JLabel(new ImageIcon(ImageIO.read(new File("src/imagenes/background_pane3.png"))));			
			
			btnEntrada = new JButton(new ImageIcon(ImageIO.read(new File("src/imagenes/btn_insertar.png"))));
			btnAdelante = new JButton(new ImageIcon(ImageIO.read(new File("src/imagenes/btn_dcha.png"))));
			btnDetras = new JButton(new ImageIcon(ImageIO.read(new File("src/imagenes/btn_izq.png"))));
			btnCerrar = new JButton(new ImageIcon(ImageIO.read(new File("src/imagenes/btn_salir.png"))));
			btnBuscar = new JButton(new ImageIcon(ImageIO.read(new File("src/imagenes/btn_buscar.png"))));
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		labelCandado1 = new JLabel(candadoAbierto);		
		labelCandado1.setBounds(70, 295, 13, 18);
		labelCandado1.setToolTipText("Libre");
		this.add(labelCandado1);
		
		labelCandado2 = new JLabel(candadoAbierto);
		labelCandado2.setBounds(70, 322, 13, 18);
		labelCandado2.setToolTipText("Libre");
		this.add(labelCandado2);
		
		labelCandado3 = new JLabel(candadoAbierto);
		labelCandado3.setBounds(70, 349, 13, 18);
		labelCandado3.setToolTipText("Libre");
		this.add(labelCandado3);
		
		labelPiso1.setBounds(-66, 0, 900, 600);		
		labelPiso2.setBounds(-66, 0, 900, 600);			
		labelPiso3.setBounds(-66, 0, 900, 600);			
				
		labelPlanta = new JLabel();
		labelPlanta.setText("1");
		labelPlanta.setFont(labelPlanta.getFont().deriveFont(20,20));
		labelPlanta.setBounds(60, 208, 20, 50);
		labelPlanta.setForeground(Color.WHITE);
		this.add(labelPlanta);		
		
		btnEntrada.setBounds(69, 142, 40, 40);
		btnEntrada.setBorder(null);
		btnEntrada.setFocusPainted(false);
		btnEntrada.addActionListener(this);
		btnEntrada.setToolTipText("Nuevo cliente");
		btnEntrada.setCursor(cursorMano);
		this.add(btnEntrada);	
		
		btnAdelante.setBounds(85, 222, 26, 26);
		btnAdelante.setBorder(null);
		btnAdelante.setFocusPainted(false);	
		btnAdelante.addActionListener(this);
		btnAdelante.setToolTipText("Piso siguiente");
		btnAdelante.setCursor(cursorMano);
		this.add(btnAdelante);		
		
		btnDetras.setBounds(19, 222, 26, 26);
		btnDetras.setBorder(null);
		btnDetras.setFocusPainted(false);
		btnDetras.setToolTipText("Piso anterior");
		btnDetras.setCursor(cursorMano);
		this.add(btnDetras);		
		
		btnBuscar.setBounds(75, 394, 26, 26);
		btnBuscar.setBorder(null);
		btnBuscar.setFocusPainted(false);		
		btnBuscar.setToolTipText("Buscar por matrícula");
		btnBuscar.setCursor(cursorMano);
		btnBuscar.addActionListener(this);
		this.add(btnBuscar);
		
		btnCerrar.setBounds(73, 446, 26, 26);
		btnCerrar.setBorder(null);
		btnCerrar.setFocusPainted(false);
		btnCerrar.addActionListener(this);
		btnCerrar.setToolTipText("Cerrar aplicación");
		btnCerrar.setCursor(cursorMano);
		this.add(btnCerrar);
		
		//Creación de las labels donde irán los coches
		arrayLabels = new JLabel[3][20];
		int horizontal = 2;
		int vertical = 85;
		for (int i = 0; i < arrayLabels.length; i++) {
			for (int j = 0; j < arrayLabels[i].length; j++) {
				arrayLabels[i][j] = new JLabel(imagenCoche);		
				arrayLabels[i][j].setBounds(horizontal, vertical, 50, 100);
				arrayLabels[i][j].setVisible(false);
				arrayLabels[i][j].addMouseListener(this);
				arrayLabels[i][j].setCursor(cursorMano);
				arrayLabels[i][j].setOpaque(true);
				arrayLabels[i][j].setToolTipText("Mostrar datos");				
				if (i == 0) {
					panel1.add(arrayLabels[i][j]);
				}
				if (i == 1) {
					panel2.add(arrayLabels[i][j]);
				}
				if (i == 2) {
					panel3.add(arrayLabels[i][j]);
				}
				horizontal += 69;
				if (j == 9) {
					vertical = 415;
					horizontal = 2;
				}
			}
			vertical = 85;
			horizontal = 2;
		}		
		labelSideMenu.setBounds(0, 0, 130, 600);
		this.add(labelSideMenu);
		panel1.add(labelPiso1);
		panel2.add(labelPiso2);
		panel3.add(labelPiso3);
		comprobarPisos();
		cambiarPiso();
	}

	/**
     * Método para buscar una matrícula entre los coches aparcados
	 *
     * @param matricula El parámetro matricula define la matrícula que se buscará
    */
	public void buscarMatricula(String matricula) {
		boolean encontrado = false;
		for (int i = 0; i < arrayLabels.length; i++) {
			for (int j = 0; j < arrayLabels[i].length; j++) {
				if (GestorParking.obtenerCoche(i, j) != null) {
					if (GestorParking.obtenerCoche(i, j).getMatricula().toUpperCase().equals(matricula)) {
						FormDatos formDatos = new FormDatos(i, j);						
						formDatos.setLocationRelativeTo(this);
						formDatos.setVisible(true);
						encontrado = true;
						break;
					}
				}
			}
			if (encontrado) {
				break;
			}
		}
		if (!encontrado) {
			JOptionPane.showMessageDialog(this, "No se encontró la matrícula", "Información", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
     * Método para comprobar que los pisos están llenos o no
	 *
     * @param piso El parámetro piso define el piso que se quiere analizar
     * 
     * @return boolean Devuelve si el piso está lleno o no 
    */
	public static boolean isPisoLleno(int piso) {
		boolean lleno = true;
		for (int i = 0; i < arrayLabels[piso].length; i++) {
			if (!arrayLabels[piso][i].isVisible()) {
				lleno = false;
			}
		}
		return lleno;
	}
	
	/**
     * Método para cambiar los iconos a los candados del menú en función de si están llenos o no
    */
	public static void comprobarPisos() {
		if (isPisoLleno(0)) {
			labelCandado1.setIcon(candadoCerrado);
			labelCandado1.setToolTipText("Lleno");
		}
		else {
			labelCandado1.setIcon(candadoAbierto);
			labelCandado1.setToolTipText("Libre");
		}
		if (isPisoLleno(1)) {
			labelCandado2.setIcon(candadoCerrado);
			labelCandado2.setToolTipText("Lleno");
		}
		else {
			labelCandado2.setIcon(candadoAbierto);
			labelCandado2.setToolTipText("Libre");
		}
		if (isPisoLleno(2)) {
			labelCandado3.setIcon(candadoCerrado);
			labelCandado3.setToolTipText("Lleno");		}
		else {
			labelCandado3.setIcon(candadoAbierto);
			labelCandado3.setToolTipText("Libre");
		}
	}
	
	/**
	 * Método para mostrar u ocultar coches
	 *
	 * @param piso En qúe piso se mostrará u ocultará el coche
	 * @param plaza  En qúe plaza se mostrará u ocultará el coche
	 * @param pintado Indica si el coche se mostrará u ocultará
	*/
	public static void pintarCoche(int piso, int plaza, boolean pintado) {	
		arrayLabels[piso][plaza].setVisible(pintado);
		comprobarPisos();
	}
	
	/**
	 * Método para cambiar la vista de los paneles de piso
	*/
	public void cambiarPiso() {
		switch (pisoActual) {		
			case 1: 		
				panel2.setVisible(false);
				panel1.setVisible(true);
				btnDetras.setVisible(false);
				btnDetras.removeActionListener(this);
				this.repaint();
			break;			
			case 2:
				panel1.setVisible(false);
				panel3.setVisible(false);
				panel2.setVisible(true);
				if (pisoAnterior == 1) {
					btnDetras.setVisible(true);
					btnDetras.addActionListener(this);
				}
				else {
					btnAdelante.setVisible(true);
					btnAdelante.addActionListener(this);
				}				
				this.repaint();
			break;
			case 3:
				panel1.setVisible(false);
				panel2.setVisible(false);
				panel3.setVisible(true);
				btnAdelante.setVisible(false);
				btnAdelante.removeActionListener(this);
				this.repaint();
			break;
		}
		labelPlanta.setText(String.valueOf(pisoActual));
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnEntrada) {
			FormEntrada fEntrada = new FormEntrada();
			fEntrada.setLocationRelativeTo(this);
			fEntrada.setVisible(true);
		}	
		if (ae.getSource() == btnCerrar) {
			int opcion = JOptionPane.showConfirmDialog(this, "¿Cerrar la aplicación?", "Confirmación", JOptionPane.YES_NO_OPTION);			
			if (opcion == 0) {
				GestorParking.hacerInsertDelete();
				GestorBd.hacerCommit();
				GestorBd.cerrarRs();
				GestorBd.cerrarConexion();
				System.exit(0);
			}
		}
		if (ae.getSource() == btnAdelante) {			
			pisoAnterior = pisoActual;
			pisoActual++;	
			cambiarPiso();
			
		}
		if (ae.getSource() == btnDetras) {			
			pisoAnterior = pisoActual;
			pisoActual--;
			cambiarPiso();			
		}
		if (ae.getSource() == btnBuscar) {
			String matricula = null;				
			matricula = JOptionPane.showInputDialog(this, "Introduzca una matrícula", "Búsqueda", JOptionPane.QUESTION_MESSAGE);			
			if (matricula != null) {
				if (matricula.trim().equals("")) {
					JOptionPane.showMessageDialog(this, "No has introducido nada", "Error", JOptionPane.ERROR_MESSAGE);	
					
				}
				else {	
					if (!GestorParking.comprobarMatricula(matricula)) {
						JOptionPane.showMessageDialog(this, "La matrícula es inválida", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						this.buscarMatricula(matricula.toUpperCase());	
					}
				}
			}			
		}	
	}

	/**
	 * Clics sobre labels de coches
	*/
	@Override
	public void mouseClicked(MouseEvent arg0) {
		for (int i = 0; i < arrayLabels.length; i++) {
			for (int j = 0; j < arrayLabels[i].length; j++) {
				if (arg0.getSource() == arrayLabels[i][j]) {
					FormDatos formDatos = new FormDatos(i, j);
					formDatos.setLocationRelativeTo(this);
					formDatos.setVisible(true);
				}
			}
		}		
	}
	
	/**
	 * Controla el cierre de ventana y hace los cambios en la base de datos
	*/
	@Override
	public void windowClosing(WindowEvent e) {	
		GestorParking.hacerInsertDelete();
		GestorBd.hacerCommit();
		GestorBd.cerrarRs();
		GestorBd.cerrarConexion();
		System.exit(0);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {		
	}

	@Override
	public void windowActivated(WindowEvent e) {		
	}

	@Override
	public void windowClosed(WindowEvent e) {				
	}

	@Override
	public void windowDeactivated(WindowEvent e) {		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {		
	}

	@Override
	public void windowIconified(WindowEvent e) {	
	}

	@Override
	public void windowOpened(WindowEvent e) {		
	}
	
}
