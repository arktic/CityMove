import javax.swing.*;



public class CityMoveWindow extends JFrame {
	Map map;

	public CityMoveWindow() {
		/* On construit notre map */
		map = new Map(30);
		add(map);

		/* On construit notre fenetre et on la resize a la taille de la map */
		build();
		resizeWindow(map);

		
		new Thread(new Runnable() {
			public void run() {
				while(true) {

					map.deplacementElementMobile();

					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {


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
	}





	private void resizeWindow(Map map2) {
		setSize(map2.largeur, map2.hauteur);

	}





	private void build() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setTitle("CityMove");
		setResizable(false);
		setVisible(true);
	}


}
