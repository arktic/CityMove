import javax.swing.*;

public final class CityMove {

	public static Map map =  new Map(600,480,10);
	public static CityMoveWindow window = new CityMoveWindow();
	
	private int nombreVoiture;
	
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				window.setVisible(true);
			}
		});

	}

}