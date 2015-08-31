import java.sql.*;

/**
 * Esta clase gestiona las operaciones que se realizan contra la base de datos.
 *
 * @author: Artzai San José & Borja Muñoz
 *
 * @version: 29/04/2014 v1.0
*/
public class GestorBd {
	
	//Atributos de clase
	private static Connection conexionMYSQL = null;
	private static Statement stmt = null;
	private static String bd = "jdbc:mysql://localhost/parking"; // Ruta del servidor + Nombre de BD.
	private static ResultSet rs;
	
	/**
     * Método necesario para conectarse al Driver y poder usar MySQL.
    */
    public static void conectar(String user, String pass) throws SQLException {
        conexionMYSQL = DriverManager.getConnection(bd, user, pass);
        conexion();         
    }
    
    /**
     * Método para establecer la conexión con la base de datos.
    */
    private static void conexion() {
        try {
            stmt = (Statement) conexionMYSQL.createStatement();
            hacerSelect();
        } catch (SQLException e) {
            System.out.println("Error: Conexión incorrecta.");
            e.printStackTrace();
        }
    }
    
    /**
     * Método para hacer el commit en la base de datos
    */
    public static void hacerCommit() {
    	try {
			stmt.execute("commit");
		} 
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
    /**
     * Método que devuelve el ResultSet
     * 
     * @return Devuelve el ResultSet con los datos de la última select
    */
    public static ResultSet getRs() {
		return rs;
	}
    
    /**
     * Método para hacer el insert en la base de datos
     * 
     * @param matricula La matrícula del coche a insertar
     * @param marca La marca del coche a insertar
     * @param modelo El modelo del coche a insertar
     * @param minusvalido Indica si el coche ocupa plaza de minusvalido
     * @param fecha La fecha de entrada del coche
     * @param plaza La plaza ocupada por el coche a insertar
    */
	public static void insertarBD(String matricula, String marca, String modelo, boolean minusvalido, String fecha, String plaza){
    	try {
			String sql = "INSERT INTO coches (matricula,marca,modelo,minusvalido,fecha_entrada,plaza)";
				sql += "VALUES(";
				sql += "'"+matricula+"',";
				sql += "'"+marca+"',";
				sql += "'"+modelo+"',";
				sql += "'"+String.valueOf(minusvalido)+"',";
				sql += "'"+fecha+"',";
				sql += "'"+plaza+"')";
			stmt.executeUpdate(sql);
		}
    	catch (SQLException sqlexception) {
    		sqlexception.printStackTrace();
		}
    	catch (Exception exception) {
			exception.printStackTrace();
		}
    }
    
	/**
	 * Método para hacer select de la base de datos
	*/
    public static void hacerSelect(){
    	try {	    	
			String sql = "SELECT * FROM coches";
			rs = stmt.executeQuery(sql);			
		} 
    	catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Método para hacer deletes de la base de datos
     * 
     * @param matricula La matrícula del coche a eliminar
    */
    public static void hacerDelete(String matricula){
    	try {
    	   /* Borramos el coche cuya matrícula le pasamos */
    	   String sql = "DELETE FROM coches WHERE matricula='"+matricula+"'";
    	   stmt.executeUpdate(sql);
    	}
    	catch (Exception e) {
    	   e.printStackTrace();
    	}
    }    
    
    /**
     * Método para cerrar el ResultSet de la conexión
    */
    public static void cerrarRs() {
        if (rs != null) {
            try {
                rs.close();
            } 
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Método para cerrar la conexión a la base de datos
    */
    public static void cerrarConexion() {
        try {
        	conexionMYSQL.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}