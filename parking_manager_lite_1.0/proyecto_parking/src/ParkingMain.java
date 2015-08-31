import javax.swing.*;

/**
 * Esta clase est� hecha exclusivamente para contener el m�todo main del programa.
 *
 * @author: Artzai San Jos� & Borja Mu�oz
 *
 * @version: 29/04/2014 v1.0
*/
public class ParkingMain {
	
	/**
	 * M�todo main del programa
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//Modifica el 'look & feel' del programa
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		FormAuth frame = new FormAuth();
		frame.setVisible(true);
		frame.setResizable(false);
	}

}