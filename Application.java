import java.awt.*;
import javax.swing.*;

public final class Application {

	/**
	 * @param args
	 */
	public static Map map =  new Map(600,480,10);
	public static JFrame window = new JFrame();
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On crée une nouvelle instance de notre JWindow
				window.setTitle("CityMove");
				window.setSize(800, 600);//On lui donne une taille pour qu'on puisse la voir
				window.setVisible(true);//On la rend visible
				window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});

	}

}
