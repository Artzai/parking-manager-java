import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Esta clase define el formulario de autentificación 
 *
 * @author: Artzai San José & Borja Muñoz
 *
 * @version: 29/04/2014 v1.0
*/
@SuppressWarnings("serial")
public class FormAuth extends JFrame implements ActionListener {
	
	//Atributos de la clase
	private JLabel labelUser, labelPass;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JButton btnAcceder, btnSalir;

	/**
     * Constructor para la creación del formulario donde no se le pasa nada por parámetro
     * y crea los límites, introduce un título y llama al método 'colocarComponentes'
    */
	public FormAuth() {
		this.setBounds(500, 400, 300, 180);
		this.setLayout(null);
		this.setTitle("Autentificación");
		this.colocarComponentes();
		try {
			this.setIconImage(new ImageIcon(ImageIO.read(new File("src/imagenes/senal_parking.png"))).getImage());
		}
		catch (IOException e) {		
			e.printStackTrace();
		}
	}
	
	/**
     * Método que coloca los componentes del frame de autentificación
     * Se le llama desde el constructor
    */
	private void colocarComponentes() {
		labelUser = new JLabel("Usuario de la BD:");
		labelUser.setBounds(30, 20, 150, 25);
		this.add(labelUser);
		txtUser = new JTextField("");
		txtUser.setBounds(160, 20, 100, 25);
		this.add(txtUser);
		
		labelPass = new JLabel("Contraseña de la BD:");
		labelPass.setBounds(30, 60, 150, 25);
		this.add(labelPass);
		txtPass = new JPasswordField("");
		txtPass.setBounds(160, 60, 100, 25);
		this.add(txtPass);
		
		btnAcceder = new JButton("Acceder");
		btnAcceder.setBounds(30, 115, 100, 25);		
		btnAcceder.addActionListener(this);
		this.add(btnAcceder);
		this.getRootPane().setDefaultButton(btnAcceder);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(150, 115, 100, 25);		
		btnSalir.addActionListener(this);
		this.add(btnSalir);		
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent ae) {
		//Botón para acceder a la aplicación
		if (ae.getSource() == btnAcceder) {
			//Comprueba que ha introducido los dos campos obligatorios
			if (txtUser.getText().trim().equals("") || txtPass.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Introduce usuario y contraseña", "Información", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				try {
					//Llamada al método 'conectar' de la clase 'GestorBd'
					GestorBd.conectar(txtUser.getText(), txtPass.getText());
					//Ejecuta la ventana principal de la aplicación
					FramePrincipal frame = new FramePrincipal();
					frame.setVisible(true);
					frame.setResizable(false);
					//Cierra esta ventana
					this.dispose();
				}
				catch (SQLException e) {
					JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				}					
			}
		}
		//Botón para salir del formulario
		if (ae.getSource() == btnSalir) {
			System.exit(0);
		}		
	}

}