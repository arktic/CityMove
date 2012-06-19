import javax.swing.*;

public class CityMoveWindow extends JFrame{

	public CityMoveWindow() {
		Map map = new Map(4,2,100);
		add(map);
		build();
		
		
		for(int i=0;i<1000;i++) {
			map.X1+=10;
			map.Y1+=10;
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("X1 = "+map.X1+" Y1  ="+map.Y1);
		}
		
		
	}
	

	private void build() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setTitle("CityMove");
		setResizable(true);
		setVisible(true);
	}
}