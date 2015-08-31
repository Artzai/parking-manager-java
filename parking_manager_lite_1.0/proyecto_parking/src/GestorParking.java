import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Esta clase gestiona las operaciones del parking.
 *
 * @author: Artzai San José & Borja Muñoz
 *
 * @version: 29/04/2014 v1.0
*/
public class GestorParking {
	
	//Atributos de clase
	private static Coche[][] arrayCoches = new Coche[3][20];
	private static int contInsertarEliminar = 0;
	private static Coche [] arrayInsertDelete = new Coche[10];
	private static boolean[] insertOdelete = new boolean[10];
	private static String[] plazas = new String[10];
	
	/**
	 * Método que importa los coches existentes en la base de datos y los guarda en el array de coches
	*/
	public static void llenarDesdeBD() {	
		try {
			while (GestorBd.getRs().next()) {
				int x = Integer.parseInt(GestorBd.getRs().getString("plaza").substring(0, 1));
				int y = Integer.parseInt(GestorBd.getRs().getString("plaza").substring(1));
				arrayCoches[x - 1][y - 1] = new Coche(GestorBd.getRs().getString("matricula"), GestorBd.getRs().getString("marca"), GestorBd.getRs().getString("modelo"), Boolean.parseBoolean(GestorBd.getRs().getString("minusvalido")), GestorBd.getRs().getString("fecha_entrada"));
				meterCoche(x - 1, y - 1, arrayCoches[x - 1][y - 1], false);
				FramePrincipal.pintarCoche(x - 1, y - 1, true);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Método para eliminar un coche del parking
	 * 
	 * @param matricula La matrícula del coche que se eliminará
	 * @param fecha La fecha de entrada o salida del coche 
	 * @param importe El precio total a pagar por el cliente
	*/
	public static void sacarCoche(String matricula, String fecha, String importe) {
		boolean sacado = false;
		for (int i = 0; i < arrayCoches.length; i++) {
			for (int j = 0; j < arrayCoches[i].length; j++) {
				if (arrayCoches[i][j] != null) {
					if (arrayCoches[i][j].getMatricula().equals(matricula)) {
						guardarLog(fecha+","+arrayCoches[i][j].getMatricula()+",salida"+" ("+importe+")");
						arrayCoches[i][j] = null;
						sacado = true;
						break;
					}	
				}
			}
			if (sacado) {
				break;
			}
		}
	}
	
	/**
	 * Método que devuelve el objeto coche de la plaza especificada
	 * 
	 * @param piso Piso del coche que se busca
	 * @param plaza Plaza del coche que se busca
	 * 
	 * @return Devuelve un objeto de tipo coche
	*/
	public static Coche obtenerCoche(int piso, int plaza) {		
		return arrayCoches[piso][plaza];
	}
	
	/**
	 * Método para introducir objetos de tipo coche en el array de coches
	 * 
	 * @param piso Piso en el que se meterá el coche
	 * @param plaza Plaza en la que se meterá el coche
	 * @param coche El coche que se introducirá
	 * @param log Indica si la operación se guardará en el log o no
	*/
	public static void meterCoche(int piso, int plaza, Coche coche, boolean log) {
		arrayCoches[piso][plaza] = coche;
		if (log) {
			guardarLog(coche.toString()+",entrada");	
		}
	}
	
	/**
	 * Método para buscar plazas libres en el parking
	 * 
	 * @param minusvalido Indica si se requiere una plaza de minusválido
	 * 
	 * @return Devuelve un array de int de 2 posiciones con el piso y la plaza asignadas al nuevo coche
	*/
	public static int[] buscarPlaza(boolean minusvalido) {
		int[] pisoPlaza = new int [2];
		boolean encontrada = false;
		if (minusvalido) {
			for (int i = 0; i < arrayCoches.length; i++) {
				for (int j = 0; j < 3; j++) {
					if (arrayCoches[i][j] == null) {
						encontrada = true;
						pisoPlaza[0] = i;
						pisoPlaza[1] = j;
						break;
					}
				}
				if (encontrada) {
					break;
				}
			}
			if (!encontrada) {
				buscarPlaza(false);
			}
		}
		else {
			for (int i = 0; i < arrayCoches.length; i++) {
				for (int j = 3; j < arrayCoches[i].length; j++) {
					if (arrayCoches[i][j] == null) {						
						encontrada = true;
						pisoPlaza[0] = i;
						pisoPlaza[1] = j;
						break;
					}
				}
				if (encontrada) {
					break;
				}
			}
		}
		if (!encontrada) {
			pisoPlaza[0] = -1;
			pisoPlaza[1] = -1;
		}
		return pisoPlaza;
	}
	
	/**
	 * Método para comprobar la validez de matrículas
	 * 
	 * @param matricula La matrícula a validar
	 * 
	 * @return Devuelve si la matrícula es válida o no lo es
	*/
	public static boolean comprobarMatricula(String matricula) {
		boolean valida = false;
		//La matrícula debe ser XX0000XX o 0000XXX para ser válida
	    if (matricula.matches("[0-9]{4}[a-zA-Z]{3}") || matricula.matches("[a-zA-Z]{2}[0-9]{4}[a-zA-Z]{2}")) {
	    	valida = true;
	    }
	    return valida;
	}
	
	/**
	 * Método para guardar las operaciones realizadas en el log
	 * 
	 * @param linea La línea que se guardará en el log
	*/
	public static void guardarLog(String linea) {
		try {
			BufferedWriter fS=new BufferedWriter(new FileWriter("src/log.txt",true));			
			fS.write(linea);	
			fS.newLine();			
			fS.close();
		}
		catch (IOException ioE) {
			System.out.println(ioE.getMessage());
		}
	}
	
	/**
	 * Método para calcular el precio total a pagar por el cliente
	 * 
	 * @param entrada La fecha de entrada al parking
	 * @param salida La fecha de salida del parking
	 * 
	 * @return Devuelve el importe total a pagar por el cliente
	*/
	public static double calcularPrecio(String entrada, String salida){
		double precio=0;		
		GregorianCalendar fecha1 = new GregorianCalendar(Integer.parseInt(entrada.substring(6,10)),Integer.parseInt(entrada.substring(3,5)) - 1,Integer.parseInt(entrada.substring(0,2)),Integer.parseInt(entrada.substring(11,13)),Integer.parseInt(entrada.substring(14,16)),Integer.parseInt(entrada.substring(17)));	
		GregorianCalendar fecha2 = new GregorianCalendar(Integer.parseInt(salida.substring(6,10)),Integer.parseInt(salida.substring(3,5)) - 1,Integer.parseInt(salida.substring(0,2)),Integer.parseInt(salida.substring(11,13)),Integer.parseInt(salida.substring(14,16)),Integer.parseInt(salida.substring(17)));	
		//Se usa long porque el número se pasa de int
		long total = ((fecha2.getTimeInMillis() - fecha1.getTimeInMillis()) / 60000);		
		final double precioMinuto = 0.034;
		precio = (total*precioMinuto);	
		//Obligamos a pagar 0.2€ sólo por entrar			
		precio += 0.2;		
		return precio;
	}
	
	/**
	 * Método para buscar matrículas en el array de coches
	 * 
	 * @param matricula La matrícula a buscar
	 * 
	 * @return Devuelve true si la matrícula existe en el array
	*/
	public static boolean buscarMatricula(String matricula) {
		boolean encontrada = false;
		for (int i = 0; i < arrayCoches.length; i++) {
			for (int j = 0; j < arrayCoches[i].length; j++) {
				if (arrayCoches[i][j] != null) {
					if (arrayCoches[i][j].getMatricula().equals(matricula)) {
						encontrada = true;
						break;
					}
				}
			}
			if (encontrada) {
				break;
			}
		}
		return encontrada;
	}
	
	/**
	 * Método para guardar las últimas 10 entradas y salidas en un array
	 * 
	 * @param coche El objeto de tipo coche que entra o sale
	 * @param piso El piso del coche que entra o sale
	 * @param plaza La plaza ocupada por el coche que entra o sale
	 * @param insertar Indica si se trata de una entrada(true) o una salida(false)
	*/
	public static void insertarEnArray(Coche coche, int piso, int plaza, boolean insertar) {
		arrayInsertDelete[contInsertarEliminar] = coche;
		if (insertar) {
			insertOdelete[contInsertarEliminar] = true;
		}
		else {
			insertOdelete[contInsertarEliminar] = false;
		}
		plazas[contInsertarEliminar] = piso+""+plaza;			
		if(contInsertarEliminar==9){
			hacerInsertDelete();
		}
		if(contInsertarEliminar < 9){
			contInsertarEliminar++;
		}
		else {
			contInsertarEliminar=0;
		}	
	}
	
	/**
	 * Método que hace el insert o delete de las últimas 10 entradas o salidas en la base de datos
	*/
	public static void hacerInsertDelete() {
		for(int i=0; i < arrayInsertDelete.length; i++){
			if (arrayInsertDelete[i] != null) {
				if (insertOdelete[i]) {
					GestorBd.insertarBD(arrayInsertDelete[i].getMatricula(), arrayInsertDelete[i].getMarca(), arrayInsertDelete[i].getModelo(), arrayInsertDelete[i].isMinusvalido(), arrayInsertDelete[i].getFechaEntrada(), plazas[i]);				
				}
				else {
					GestorBd.hacerDelete(arrayInsertDelete[i].getMatricula());
				}
			}
		}	
		for(int i=0; i < arrayInsertDelete.length; i++){
			arrayInsertDelete[i] = null;
			insertOdelete[i] = false;
		}
		GestorBd.hacerCommit();
	}
	
	
	
}