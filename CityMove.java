import javax.swing.*;

public final class CityMove {
	public static FeuPieton test = new FeuPieton(EtatFeu.ROUGE);
	public static Map map =  new Map(600,480,10);
	public static CityMoveWindow window = new CityMoveWindow();
	private static VoitureUrgent vu = new VoitureUrgent();
	private static VoitureUrgent vu2 = new VoitureUrgent();
	private int nombreVoiture;
	
	
	public static void main(String[] args){
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				window.setVisible(true);
			}
		});
	
	//	test.run();
		vu.allumerGirophare();
		vu2.stoperGirophare();
	//	System.out.println("etat vu2: " + vu2.getEtatGiro());
	//	System.out.println("etat vu: " + vu.getEtatGiro());

	}

}
