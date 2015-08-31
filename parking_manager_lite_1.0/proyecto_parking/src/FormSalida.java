import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Esta clase define el formulario de salida donde se confirmará la salida de un coche
 * Mostrará también el precio a pagar por el cliente
 *
 * @author: Artzai San José & Borja Muñoz
 *
 * @version: 29/04/2014 v1.0
*/
@SuppressWarnings("serial")
public class FormSalida extends JFrame implements ActionListener {
	
	//Atributos de clase
	private JLabel labelMatricula, labelMarca, labelModelo, horaEntrada, horaSalida, labelImporte;
	private int piso, plaza;
	private JButton btnConfirmar, btnCancelar;
	private SimpleDateFormat formatoFecha;
	private String fechaActual;
	private GregorianCalendar calendario;
	private DecimalFormat df = new DecimalFormat("0.00");
	
	/**
     * Constructor que define el formulario de salida definitivo para sacar el coche. 
	 * Pasamos por parámetro las posiciones de piso y plaza.
	 * Por otra parte el constructor define los bordes del frame.
	 * Crea un calendario y asigna la hora actual del sistema.
	 * Llama al método 'calcularPrecio' de la clase 'GestorParking' y muestra la cantidad a pagar.
	 * Llama al método colocarComponentes 
	 *
	 * @param piso El parámetro piso define el piso del coche que se eliminará
	 * @param plaza El parámetro plaza define el número de la plaza del coche que se eliminará
    */
	public FormSalida(int piso, int plaza) {
		this.setLayout(null);
		this.setSize(250, 300);
		this.setTitle("Salida");
		this.setResizable(false);		
		this.piso = piso;
		this.plaza = plaza;
		try {
			this.setIconImage(new ImageIcon(ImageIO.read(new File("src/imagenes/senal_parking.png"))).getImage());
		}
		catch (IOException e) {		
			e.printStackTrace();
		}
		this.colocarComponentes();
		GestorParking.calcularPrecio(horaEntrada.getText().substring(12), horaSalida.getText().substring(11));		
	}
	
	/**
	 * Método que instancia y coloca todos los componentes que componen el formulario de salida
	*/
	private void colocarComponentes() {
		this.calendario = new GregorianCalendar();
		this.formatoFecha = new SimpleDateFormat("dd/MM/yyyy,HH:mm:ss");
		this.fechaActual = formatoFecha.format(calendario.getTime());
		
		labelMatricula = new JLabel("Matricula: "+GestorParking.obtenerCoche(piso, plaza).getMatricula());
		labelMatricula.setBounds(40, 20, 150, 25);
		this.add(labelMatricula);		
		
		labelMarca = new JLabel("Marca: "+GestorParking.obtenerCoche(piso, plaza).getMarca());
		labelMarca.setBounds(40, 55, 100, 25);
		this.add(labelMarca);		
			
		labelModelo = new JLabel("Modelo: "+GestorParking.obtenerCoche(piso, plaza).getModelo());
		labelModelo.setBounds(40, 90, 100, 25);
		this.add(labelModelo);		
		
		horaEntrada = new JLabel("F. entrada: "+GestorParking.obtenerCoche(piso, plaza).getFechaEntrada());
		horaEntrada.setBounds(40, 125, 200, 25);
		this.add(horaEntrada);
		
		horaSalida = new JLabel("F. salida: "+this.fechaActual);
		horaSalida.setBounds(40, 160, 200, 25);
		this.add(horaSalida);
		
		labelImporte = new JLabel("Importe: "+df.format(GestorParking.calcularPrecio(GestorParking.obtenerCoche(piso, plaza).getFechaEntrada(), this.fechaActual))+"€");
		labelImporte.setBounds(85, 195, 150, 25);
		labelImporte.setForeground(Color.RED);
		this.add(labelImporte);
		
		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(20, 230, 100, 25);
		btnConfirmar.addActionListener(this);
		this.add(btnConfirmar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(130, 230, 100, 25);
		btnCancelar.addActionListener(this);
		this.add(btnCancelar);
	}
	
	public void actionPerformed(ActionEvent ae) {
		//Botón que confirma la salida de un coche del aparcamiento
		if (ae.getSource() == btnConfirmar) {			
			FramePrincipal.pintarCoche(piso, plaza, false);
			GestorParking.insertarEnArray(GestorParking.obtenerCoche(piso, plaza), piso, plaza, false);
			GestorParking.sacarCoche(GestorParking.obtenerCoche(piso, plaza).getMatricula(), fechaActual, labelImporte.getText());
			this.dispose();
		}	
		//Botón que cancela la salida
		if (ae.getSource() == btnCancelar) {
			this.dispose();
		}
	}

}