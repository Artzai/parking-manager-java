import java.sql.*;

/**
 * Esta clase gestiona las operaciones que se realizan contra la base de datos.
 *
 * @author: Artzai San Jos� & Borja Mu�oz
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
     * M�todo necesario para conectarse al Driver y poder usar MySQL.
    */
    public static void conectar(String user, String pass) throws SQLException {
        conexionMYSQL = DriverManager.getConnection(bd, user, pass);
        conexion();         
    }
    
    /**
     * M�todo para establecer la conexi�n con la base de datos.
    */
    private static void conexion() {
        try {
            stmt = (Statement) conexionMYSQL.createStatement();
            hacerSelect();
        } catch (SQLException e) {
            System.out.println("Error: Conexi�n incorrecta.");
            e.printStackTrace();
        }
    }
    
    /**
     * M�todo para hacer el commit en la base de datos
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
     * M�todo que devuelve el ResultSet
     * 
     * @return Devuelve el ResultSet con los datos de la �ltima select
    */
    public static ResultSet getRs() {
		return rs;
	}
    
    /**
     * M�todo para hacer el insert en la base de datos
     * 
     * @param matricula La matr�cula del coche a insertar
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
	 * M�todo para hacer select de la base de datos
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
     * M�todo para hacer deletes de la base de datos
     * 
     * @param matricula La matr�cula del coche a eliminar
    */
    public static void hacerDelete(String matricula){
    	try {
    	   /* Borramos el coche cuya matr�cula le pasamos */
    	   String sql = "DELETE FROM coches WHERE matricula='"+matricula+"'";
    	   stmt.executeUpdate(sql);
    	}
    	catch (Exception e) {
    	   e.printStackTrace();
    	}
    }    
    
    /**
     * M�todo para cerrar el ResultSet de la conexi�n
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
     * M�todo para cerrar la conexi�n a la base de datos
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