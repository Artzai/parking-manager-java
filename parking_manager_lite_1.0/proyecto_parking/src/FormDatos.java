import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Esta clase define el formulario de datos de los coches aparcados en el parking
 *
 * @author: Artzai San José & Borja Muñoz
 *
 * @version: 29/04/2014 v1.0
*/
@SuppressWarnings("serial")
public class FormDatos extends JFrame implements ActionListener {
	
	//Atributos de la clase
	private JLabel labelMatricula, labelMarca, labelModelo, labelPlaza, labelMinusvalido;	
	private JButton btnSacar, btnCerrar;
	private int piso, plaza;
	private ImageIcon imgMinusvalido;	

	/**
     * Constructor que define el formulario que muestra los datos de cada coche.
     * Pasaremos los parámetros de posición.
	 * Define el tamaño del formulario.
	 * Finalmente llama al método 'colocarComponentes'.
	 *
     * @param piso El parámetro piso define la planta en la que se aparcará el coche
     *
     * @param plaza El parámetro plaza define la plaza en la que se aparcará el coche
    */
	public FormDatos(int piso, int plaza) {
		this.setLayout(null);
		this.setSize(250, 300);
		this.setTitle("Datos");
		this.setResizable(false);				
		this.piso = piso;
		this.plaza = plaza;
		this.colocarComponentes();
		try {
			this.setIconImage(new ImageIcon(ImageIO.read(new File("src/imagenes/senal_parking.png"))).getImage());
		}
		catch (IOException e) {		
			e.printStackTrace();
		}
	}
	
	/**
     * Método donde se instancian y colocan todos los componentes que contendrá el formulario de datos. 
    */
	private void colocarComponentes() {
		labelMatricula = new JLabel("Matricula: "+GestorParking.obtenerCoche(piso, plaza).getMatricula());
		labelMatricula.setBounds(40, 20, 150, 25);
		this.add(labelMatricula);
		
		labelMarca = new JLabel("Marca: "+GestorParking.obtenerCoche(piso, plaza).getMarca());
		labelMarca.setBounds(40, 55, 100, 25);
		this.add(labelMarca);		
			
		labelModelo = new JLabel("Modelo: "+GestorParking.obtenerCoche(piso, plaza).getModelo());
		labelModelo.setBounds(40, 90, 100, 25);
		this.add(labelModelo);
		
		labelPlaza = new JLabel("Plaza: "+(piso + 1)+" - "+(plaza + 1));
		labelPlaza.setBounds(70, 130, 100, 25);
		this.add(labelPlaza);		
		
		btnSacar = new JButton("Sacar");
		btnSacar.setBounds(20, 230, 100, 25);
		this.add(btnSacar);
		btnSacar.addActionListener(this);
		
		btnCerrar = new JButton("Cerrar");
		btnCerrar.setBounds(130, 230, 100, 25);
		this.add(btnCerrar);
		btnCerrar.addActionListener(this);
		
		this.imgMinusvalido = new ImageIcon("src/imagenes/minusvalido.gif");
		labelMinusvalido = new JLabel(imgMinusvalido);
		labelMinusvalido.setBounds(95, 160, 50, 50);
		this.add(labelMinusvalido);
		labelMinusvalido.setVisible(false);
		//Si la plaza obtenida es de minusválido la imagen de minusválido se hace visible
		if (GestorParking.obtenerCoche(piso, plaza).isMinusvalido()) {
			labelMinusvalido.setVisible(true);
		}
	}

	public void actionPerformed(ActionEvent ae) {
		//Botón que se utiliza para sacar el coche del aparcamiento
		if (ae.getSource() == btnSacar) {
			FormSalida fSalida = new FormSalida(piso, plaza);
			fSalida.setLocationRelativeTo(this);
			fSalida.setVisible(true);	
			this.dispose();
		}	
		//Botón que se utiliza para cerrar el formulario de datos
		if (ae.getSource() == btnCerrar) {
			this.dispose();
		}
	}
	
}