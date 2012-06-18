import javax.swing.*;


public class CityMoveWindow extends JFrame{
	
	public CityMoveWindow (int x, int y) {
		super();
		build(x,y);
		setVisible(true);
	}
	
	public CityMoveWindow() {
		super();
		build();
		setVisible(true);
	}
	
	private void build(int x, int y) {
		setTitle("CityMove");
		setSize(x,y);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(new Map(15,15,100));
		System.out.println("pasage build ac param");
	}

	private void build() {
		setTitle("CityMove");
		setSize(1336,768);
		setLayout(null);
		setLocationRelativeTo(null);
		setResizable(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.println("pasage build sans param");
		add (new Map(3,4,100));
	}
	
	
}
