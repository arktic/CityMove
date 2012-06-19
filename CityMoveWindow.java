import javax.swing.*;


public class CityMoveWindow extends JFrame{
	Map map;
	
	public CityMoveWindow() {
		map = new Map(22,33,30);
		add(map);
		build();
		
		
		
		for(int i=0;i<1000;i++) {
			map.X1+=1;
			map.Y1+=1;
			repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("X1 = "+map.X1+" Y1  ="+map.Y1);
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
