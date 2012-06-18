import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CityMoveWindow extends JFrame implements ActionListener{
	private JButton pieton = new JButton("Demande piéton");
	private JButton urgence = new JButton("Demande urgence");
	public CityMoveWindow (int x, int y) {
		super();
		build(x,y);
		setVisible(true);
	}
	
	public CityMoveWindow() {
		super();
		pieton.addActionListener(this);
		urgence.addActionListener(this);
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

	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		Image img = getToolkit().getImage("./Ressources/Map/herbe.jpg") ;
		JLabel image = new JLabel(new ImageIcon(img));
		panel.setBackground(Color.white);
		JLabel label = new JLabel("Résultat : Pas encore calculé");
		image.setBounds(800, 400, 100, 100);
		label.setBounds(40, 100, WIDTH, HEIGHT);
		panel.add(image);
		panel.add(label);
		panel.add(pieton);
		panel.add(urgence);
		return panel;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		 Object src = evt.getSource();
		    if (src == pieton) {
		    	CityMove.test.setDemande(EtatFeu.ROUGE);
		    } else if (src == urgence) {
		    	CityMove.test.setDemande(EtatFeu.VERT);
		      
		    }
		
	}
}
