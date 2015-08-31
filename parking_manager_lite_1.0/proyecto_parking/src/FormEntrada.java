import java.awt.Color;
import java.awt.event.*;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Esta clase define el formulario de entrada de datos de cada coche 
 *
 * @author: Artzai San José & Borja Muñoz
 *
 * @version: 29/04/2014 v1.0
*/
@SuppressWarnings("serial")
public class FormEntrada extends JFrame implements ActionListener {
	
	//Atributos de la clase
	private JLabel labelMatricula, labelMarca, labelModelo, labelPiso, labelPlaza;	
	private JTextField txtMatricula, txtMarca, txtModelo;
	private JCheckBox chkMinusvalido;
	private JButton btnBuscarMeter, btnCancelar;
	private int[] plaza;	

	/**
     * Constructor que define el formulario de inserción de datos de cada coche. 
	 * Define el tamaño de la ventana y llama al método 'colocarComponentes'.
    */
	public FormEntrada() {
		this.setLayout(null);		
		this.setSize(250, 330);
		this.setResizable(false);
		this.setTitle("Nuevo");			
		this.colocarComponentes();
		try {
			this.setIconImage(new ImageIcon(ImageIO.read(new File("src/imagenes/senal_parking.png"))).getImage());
		}
		catch (IOException e) {		
			e.printStackTrace();
		}		
	}
	
	/**
     * Método donde se instancian y colocan todos los componentes que contendrá el formulario de entrada de datos. 
    */
	private void colocarComponentes() {
		labelMatricula = new JLabel("Matricula");
		labelMatricula.setBounds(40, 20, 100, 25);
		this.add(labelMatricula);
		txtMatricula = new JTextField();
		txtMatricula.setBounds(130, 20, 100, 25);
		this.add(txtMatricula);
		
		labelMarca = new JLabel("Marca");
		labelMarca.setBounds(40, 55, 100, 25);
		this.add(labelMarca);
		txtMarca = new JTextField();
		txtMarca.setBounds(130, 55, 100, 25);
		this.add(txtMarca);
			
		labelModelo = new JLabel("Modelo");
		labelModelo.setBounds(40, 90, 100, 25);
		this.add(labelModelo);
		txtModelo = new JTextField();
		txtModelo.setBounds(130, 90, 100, 25);
		this.add(txtModelo);
		
		labelPiso = new JLabel("Piso: ");
		labelPiso.setBounds(60, 140, 150, 25);
		this.add(labelPiso);
		
		labelPlaza = new JLabel("Plaza: ");
		labelPlaza.setBounds(120, 140, 100, 25);
		this.add(labelPlaza);		
		
		btnBuscarMeter = new JButton("Buscar Plaza");
		btnBuscarMeter.setBounds(45, 230, 150, 25);		
		btnBuscarMeter.addActionListener(this);
		btnBuscarMeter.setEnabled(true);
		this.add(btnBuscarMeter);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(45, 265, 150, 25);		
		btnCancelar.addActionListener(this);
		this.add(btnCancelar);
		
		chkMinusvalido = new JCheckBox("Minusvalido");
		chkMinusvalido.setBounds(70, 180, 100, 25);
		this.add(chkMinusvalido);		
	}

	public void actionPerformed(ActionEvent ae) {
		//Botón para buscar plaza y si hay plaza libre insertar coche
		if (ae.getSource() == btnBuscarMeter) {
			if (txtMatricula.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "La matricula es obligatoria", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else {
				if (!GestorParking.comprobarMatricula(txtMatricula.getText().trim().toUpperCase())) {
					JOptionPane.showMessageDialog(this, "La matrícula es inválida", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					if (GestorParking.buscarMatricula(txtMatricula.getText().trim().toUpperCase())) {
						JOptionPane.showMessageDialog(this, "La matrícula está repetida", "Advertencia", JOptionPane.WARNING_MESSAGE);
					}
					else {
						txtMarca.setEditable(false);
						txtMatricula.setEditable(false);
						txtModelo.setEditable(false);
						chkMinusvalido.setEnabled(false);
						//Reutilizamos el botón cambiandole el texto
						if (btnBuscarMeter.getText().equals("Buscar Plaza")) {
							plaza = GestorParking.buscarPlaza(chkMinusvalido.isSelected());
							if (plaza[0] != -1) {
								labelPiso.setText("Piso: "+String.valueOf(plaza[0] + 1));
								labelPlaza.setText("Plaza: "+String.valueOf(plaza[1] + 1));
								btnBuscarMeter.setText("Aparcar Coche");
							}
							else {
								labelPlaza.setVisible(false);
								labelPiso.setForeground(Color.RED);
								labelPiso.setText("     PARKING LLENO");
								btnBuscarMeter.setEnabled(false);
							}
						}
						//Cuando el texto del botón ha cambiado, el evento es diferente
						else {
							Coche coche = new Coche(txtMatricula.getText().toUpperCase(), txtMarca.getText(), txtModelo.getText(), chkMinusvalido.isSelected());
							//Pasamos el piso, la plaza, el coche y si va al log o no
							GestorParking.meterCoche(plaza[0], plaza[1], coche, true);
							//Dibuja el coche en el frame
							FramePrincipal.pintarCoche(plaza[0], plaza[1], true);
							//Inserta el coche en el array de coches
							GestorParking.insertarEnArray(coche, plaza[0] + 1, plaza[1] + 1, true);
							this.dispose();
						}
					}
				}
			}
			txtMatricula.requestFocus();
		}
		//Botón para cancelar y salir del formulario
		if (ae.getSource() == btnCancelar) {
			this.dispose();
		}
	}
	
}