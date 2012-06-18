import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Timer;
import java.util.Vector;

import javax.swing.JPanel;


public class Map extends JPanel {

	protected int height;

	protected int width;

	protected static HashMap<BackgroundElement, Image> backgroundImage;
	
	
	protected Vector<ElementMobiles> tabElementMobile;

	/**
	 * le tableau de bloc qui compose la map
	 */
	protected MapElement tabMapElement[][];

	/**
	 * La taille en pixel d'un element (carré) de type MapElement
	 */
	protected int sizeElement;
	public Timer timer;


	/**
	 * @param height
	 * @param width
	 * @param sizeElem
	 */
	public Map(int height, int width, int sizeElem) {
		super();
		this.height = height;
		this.width = width;
		this.tabElementMobile = new Vector<ElementMobiles>();
		
		/* On s'occupe de charger les images associées aux background */
		backgroundImage = new HashMap<>();
		ajouterBackgroundImages();
		
		
		this.tabMapElement = new MapElement[width][height];
		setFocusable(true);
		setDoubleBuffered(true);
		
		/* On remplit notre nouvelle map de cases vides */
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
				try {
					
				
				tabMapElement[i][j].set(BackgroundElement.HERBE, null, TypeMobileElement.VIDE);
				}
				catch(Exception e) {};
			}
		}
		this.sizeElement = sizeElem;
		
		//timer = new Timer(5,this);
		//timer.start();
	}


	
	/**
	 * Ajoute toutes les images de background à notre HashMap
	 */
	private void ajouterBackgroundImages() {
		Image img;
		img = Toolkit.getDefaultToolkit().getImage("./Ressources/Map/herbe.jpg");
		backgroundImage.put(BackgroundElement.HERBE, img);
		System.out.println("Ajout de "+img+" a fashmap");
	}


	/* ACCESSEURS */
	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the tabVehicule
	 */
	public Vector<ElementMobiles> getTabVehicule() {
		return tabElementMobile;
	}

	/**
	 * @param tabVehicule the tabVehicule to set
	 */
	public void setTabVehicule(Vector<ElementMobiles> tabElementMobile) {
		this.tabElementMobile = tabElementMobile;
	}

	/**
	 * @return the tabMapElement
	 */
	public MapElement[][] getTabMapElement() {
		return tabMapElement;
	}

	/**
	 * @param tabMapElement the tabMapElement to set
	 */
	public void setTabMapElement(MapElement[][] tabMapElement) {
		this.tabMapElement = tabMapElement;
	}

	/**
	 * @return the sizeElement
	 */
	public int getSizeElement() {
		return sizeElement;
	}



	public int addVehicule(ElementMobiles monElementMobile) {
		tabElementMobile.add(monElementMobile);
		return 0;
	}

	public int removeVehicule(ElementMobiles monElementMobile) {
		tabElementMobile.remove(monElementMobile);
		return 0;
	}

	/**
	 * @param x la position sur les x de l'éléments que l'on veut obtenir
	 * @param y la position sur les y de l'éléments que l'on veut obtenir
	 * @return
	 */
	public MapElement getMapElement(int x,int y) {
		return tabMapElement[x][y];
	}
	
	
	/**
	 * @param tilesCoord coordonnées en  Tuiles dont on veut récupérer le MapElement
	 * @return
	 */
	public MapElement getMapElement(Coordonnee tilesCoord) {
		return tabMapElement[tilesCoord.getX()][tilesCoord.getY()];
	}

	
	
	
	
	/**
	 * Renvoie la position en tuiles d'une position en pixels
	 * @param pixelsPosition la position en pixels
	 * @return
	 */
	public Coordonnee getPositionInTiles(Coordonnee pixelsPosition) {
		Coordonnee tilesPosition = new Coordonnee(pixelsPosition.getX()%sizeElement,pixelsPosition.getY()%sizeElement);
		
		return tilesPosition;
	}
	
	
	
	
	
	
	
	
	
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D)g;

		for(int i=0 ; i < height ; i++ ){
			for(int j=0 ; j < width ; j++) {
				Image img;
				img = backgroundImage.get(tabMapElement[j][i]);
				
				System.out.println("PASSAGE BOUCLE, img = "+img+" tabMapElement[j][i] = " +tabMapElement[j][i]);
				g2d.drawImage( img, j*sizeElement, i*sizeElement, this);	
			}
		}


		//Affichage des autres composants
		//g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	
	
	
	
	
}