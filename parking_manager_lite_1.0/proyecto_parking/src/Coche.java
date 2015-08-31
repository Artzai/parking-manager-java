import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Esta clase define los elementos de tipo coche que se utilizarán para gestionar el Parking
 *
 * @author: Artzai San José & Borja Muñoz
 *
 * @version: 29/04/2014 v1.0
*/
public class Coche {
	
	//Atributos de la clase
	private String matricula, marca, modelo, fechaEntrada = "";
	private boolean minusvalido;
	private GregorianCalendar calendario;
	private SimpleDateFormat formatoFecha;

	/**
     * Constructor para la creación de coches que provienen de la base de datos
	 *
     * @param matricula El parámetro matrícula define el número de la matrícula
     * @param marca El parámetro marca define la marca del coche
     * @param modelo El parámetro modelo define el modelo del coche
     * @param minusvalido El parámetro minusvalido define el si el coche es de conductor minusválido o no
     * @param fechaEnt El fechaEntrada define la fecha de entrada que le asignaremos desde la base de datos
    */
	public Coche(String matricula, String marca, String modelo, boolean minusvalido, String fechaEnt) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;				
		this.minusvalido = minusvalido;
		this.fechaEntrada = fechaEnt;
	}	
	
	/**
     * Constructor para la creación de coches introducidos por el usuario
	 *
     * @param matricula El parámetro matrícula define el número de la matrícula
     * @param marca El parámetro marca define la marca del coche
     * @param modelo El parámetro modelo define el modelo del coche
     * @param minusvalido El parámetro minusvalido define el si el coche es de conductor minusválido o no
    */
	public Coche(String matricula, String marca, String modelo, boolean minusvalido) {
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;				
		this.minusvalido = minusvalido;
		//Creamos el calendario donde meteremos los datos de la fecha
		this.calendario = new GregorianCalendar();
		//Formato de hora que asignamos
		this.formatoFecha = new SimpleDateFormat("dd/MM/yyyy,HH:mm:ss");
		//Obtenemos la hora actual del sistema
		this.fechaEntrada = formatoFecha.format(calendario.getTime());
	}

	/**
     * Método que devuelve si el coche es de minusválido
	 *
     * @return Devuelve 'true' si el coche es de minusválidos y 'False' si no lo es
    */
	public boolean isMinusvalido() {
		return minusvalido;
	}
	
	/**
     * Método que devuelve la marca del coche
	 *
     * @return Devuelve un String con la marca
    */
	public String getMarca() {
		return marca;
	}
	
	/**
     * Método que devuelve el modelo del coche
	 *
     * @return Devuelve un String con el modelo
    */
	public String getModelo() {
		return modelo;
	}
	
	/**
     * Método que devuelve la matrícula
	 *
     * @return Devuelve un String la matrícula
    */
	public String getMatricula() {
		return matricula;
	}	
	
	/**
     * Método que devuelve la fecha de aparcamiento
	 *
     * @return Devuelve un String con la hora
    */
	public String getFechaEntrada() {
		return fechaEntrada;
	}
	
	/**
     * Método para asignar si es minusválido
	 *
     * @param minusvalido El parámetro minusválido define mediante un boolean si es minusválido o no
    */
	public void setMinusvalido(boolean minusv) {
		this.minusvalido = minusv;
	}
	
	/**
     * Método que devuelve la fecha de aparcamiento y la matrícula 
	 *
     * @return Devuelve un String con la hora y la matricula
    */
	public String toString() {
		return fechaEntrada+","+matricula;
	}	

}