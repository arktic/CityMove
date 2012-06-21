import javax.swing.*;
import javax.swing.text.TabExpander;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;


public class CityMoveWindow extends JFrame {
	Map map;

	public CityMoveWindow() {
		map = new Map(30);
		add(map);
		build();

		new Thread(new Runnable() {
			public void run() {
				while(true) {
					
					map.deplacementElementMobile();
					
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {

								// TODO Auto-generated method stub
								repaint();

						}
					});
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		
		FeuPieton fp = new FeuPieton(EtatFeu.ROUGE);
		(new Thread(fp)).start();
		FeuPieton fp1 = new FeuPieton(EtatFeu.VERT);
		System.out.println("Avnt lancement fp1");
		(new Thread(fp1)).start();
		System.out.println("avnt ajout osbervers");
		fp.addObserver(fp1);
		System.out.println("Apres ajout observers");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Avant notif effectué");
		//fp.setChanged();
		fp.notifyObservers(EtatFeu.ROUGE);
		
		System.out.println("Notification effectué");
		//fp.notify();
		while(true) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(map.tabMapElement[10][3].myFeu.etat==EtatFeu.ROUGE) {
			map.tabMapElement[10][3].myFeu.etat=EtatFeu.VERT;
		}
		else
			map.tabMapElement[10][3].myFeu.etat=EtatFeu.ROUGE;
		}
	}


	


	private void build() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setTitle("CityMove");
		setResizable(true);
		setVisible(true);
	}


}
