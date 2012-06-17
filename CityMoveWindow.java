import javax.swing.*;
import java.awt.*;

public class CityMoveWindow extends JFrame{
	
	public CityMoveWindow (int x, int y) {
		super();
		build(x,y);
	}
	
	public CityMoveWindow() {
		super();
		build();
	}
	
	private void build(int x, int y) {
		setTitle("CityMove");
		setSize(x,y);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void build() {
		setTitle("CityMove");
		setSize(1336,768);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
