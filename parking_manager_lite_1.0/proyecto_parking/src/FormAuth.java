import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Esta clase define el formulario de autentificaci�n 
 *
 * @author: Artzai San Jos� & Borja Mu�oz
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
     * Constructor para la creaci�n del formulario donde no se le pasa nada por par�metro
     * y crea los l�mites, introduce un t�tulo y llama al m�todo 'colocarComponentes'
    */
	public FormAuth() {
		this.setBounds(500, 400, 300, 180);
		this.setLayout(null);
		this.setTitle("Autentificaci�n");
		this.colocarComponentes();
		try {
			this.setIconImage(new ImageIcon(ImageIO.read(new File("src/imagenes/senal_parking.png"))).getImage());
		}
		catch (IOException e) {		
			e.printStackTrace();
		}
	}
	
	/**
     * M�todo que coloca los componentes del frame de autentificaci�n
     * Se le llama desde el constructor
    */
	private void colocarComponentes() {
		labelUser = new JLabel("Usuario de la BD:");
		labelUser.setBounds(30, 20, 150, 25);
		this.add(labelUser);
		txtUser = new JTextField("");
		txtUser.setBounds(160, 20, 100, 25);
		this.add(txtUser);
		
		labelPass = new JLabel("Contrase�a de la BD:");
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
		//Bot�n para acceder a la aplicaci�n
		if (ae.getSource() == btnAcceder) {
			//Comprueba que ha introducido los dos campos obligatorios
			if (txtUser.getText().trim().equals("") || txtPass.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "Introduce usuario y contrase�a", "Informaci�n", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				try {
					//Llamada al m�todo 'conectar' de la clase 'GestorBd'
					GestorBd.conectar(txtUser.getText(), txtPass.getText());
					//Ejecuta la ventana principal de la aplicaci�n
					FramePrincipal frame = new FramePrincipal();
					frame.setVisible(true);
					frame.setResizable(false);
					//Cierra esta ventana
					this.dispose();
				}
				catch (SQLException e) {
					JOptionPane.showMessageDialog(this, "Usuario o contrase�a incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				}					
			}
		}
		//Bot�n para salir del formulario
		if (ae.getSource() == btnSalir) {
			System.exit(0);
		}		
	}

}