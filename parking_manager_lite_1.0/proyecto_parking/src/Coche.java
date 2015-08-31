import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Esta clase define los elementos de tipo coche que se utilizar�n para gestionar el Parking
 *
 * @author: Artzai San Jos� & Borja Mu�oz
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
     * Constructor para la creaci�n de coches que provienen de la base de datos
	 *
     * @param matricula El par�metro matr�cula define el n�mero de la matr�cula
     * @param marca El par�metro marca define la marca del coche
     * @param modelo El par�metro modelo define el modelo del coche
     * @param minusvalido El par�metro minusvalido define el si el coche es de conductor minusv�lido o no
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
     * Constructor para la creaci�n de coches introducidos por el usuario
	 *
     * @param matricula El par�metro matr�cula define el n�mero de la matr�cula
     * @param marca El par�metro marca define la marca del coche
     * @param modelo El par�metro modelo define el modelo del coche
     * @param minusvalido El par�metro minusvalido define el si el coche es de conductor minusv�lido o no
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
     * M�todo que devuelve si el coche es de minusv�lido
	 *
     * @return Devuelve 'true' si el coche es de minusv�lidos y 'False' si no lo es
    */
	public boolean isMinusvalido() {
		return minusvalido;
	}
	
	/**
     * M�todo que devuelve la marca del coche
	 *
     * @return Devuelve un String con la marca
    */
	public String getMarca() {
		return marca;
	}
	
	/**
     * M�todo que devuelve el modelo del coche
	 *
     * @return Devuelve un String con el modelo
    */
	public String getModelo() {
		return modelo;
	}
	
	/**
     * M�todo que devuelve la matr�cula
	 *
     * @return Devuelve un String la matr�cula
    */
	public String getMatricula() {
		return matricula;
	}	
	
	/**
     * M�todo que devuelve la fecha de aparcamiento
	 *
     * @return Devuelve un String con la hora
    */
	public String getFechaEntrada() {
		return fechaEntrada;
	}
	
	/**
     * M�todo para asignar si es minusv�lido
	 *
     * @param minusvalido El par�metro minusv�lido define mediante un boolean si es minusv�lido o no
    */
	public void setMinusvalido(boolean minusv) {
		this.minusvalido = minusv;
	}
	
	/**
     * M�todo que devuelve la fecha de aparcamiento y la matr�cula 
	 *
     * @return Devuelve un String con la hora y la matricula
    */
	public String toString() {
		return fechaEntrada+","+matricula;
	}	

}