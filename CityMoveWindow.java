import javax.swing.*;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;


public class CityMoveWindow extends JFrame{
	Map map;
	
	public CityMoveWindow() {
		map = new Map(30);
		add(map);
		build();
		
		new Thread(new Runnable() {
			public void run() {
				while(true) {
					//map.X1+=1;
					if(map.Y1 < 0)	map.Y1 = 300;
						map.Y1-=1;
					System.out.println("Y1 = "+map.Y1);
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
