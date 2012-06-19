import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;


import javax.imageio.ImageIO;
import javax.swing.*;



public class Map extends JPanel{

	protected int nbLignes;

	protected int nbColonnes;

	
	public int X1 = 0;
	public int Y1 = 0;
	
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
	

	
	/**
	 * @param heightInTiles
	 * @param widthInTiles
	 * @param sizeElem
	 */
	public Map(int nbLignes, int nbColonnes, int sizeElem) {
		
		backgroundImage = new HashMap<>();
		this.nbColonnes = nbColonnes;
		this.nbLignes = nbLignes;
		this.tabElementMobile = new Vector<ElementMobiles>();
		
		this.tabMapElement = new MapElement[nbLignes][nbColonnes];
		
		
		/* On ajoute notre base de donnée d'images de Background à notre image */
		ajouterBackgroundImages();
		
		/* On remplit la map avec de l'herbe */
		remplirDefaultMap();
		
		CityMove.map=this;
		this.sizeElement = sizeElem;
		
		setFocusable(true);
		setDoubleBuffered(true);
	}

	
	/**
	 * Ajoute toutes les images de background à notre HashMap
	 */
	private void ajouterBackgroundImages() {
		Image img;
		
		
		try {
			img = ImageIO.read(new File("./Ressources/Background/herbe.jpg"));
			backgroundImage.put(BackgroundElement.HERBE, img);
			
			img = ImageIO.read(new File("./Ressources/Background/muraille.jpg"));
			backgroundImage.put(BackgroundElement.MURRAILLE, img);
			
			img = ImageIO.read(new File("./Ressources/Background/route_nord.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_NORD, img);
			
			img = ImageIO.read(new File("./Ressources/Background/route_sud.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_SUD, img);
			
			
		} catch (IOException e) {
			System.out.println("Erreur lors du chargment des images...");
			e.printStackTrace();
		}
		
		
		
	}



	
	
	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D)g;

		for(int c=0 ; c < nbColonnes ; c++ ){
			for(int l=0 ; l < nbLignes ; l++) {
				Image img;
				img = backgroundImage.get(tabMapElement[l][c].myBackgroundElement);
				
				g2d.drawImage( img, c*sizeElement, l*sizeElement, this);	
			}
		}

		
		
		/*Toolkit.getDefaultToolkit().sync();
		g.dispose();
		*/
	}

	public void remplirDefaultMap () {
		/* On remplit notre nouvelle map de cases vides */
		for(int i=0; i<nbLignes; i++) {
			for(int j=0; j<nbColonnes; j++) {
				MapElement newMapElement;
					if (j==0) {
					newMapElement = new MapElement(BackgroundElement.ROUTE_NORD, null, TypeMobileElement.VIDE);
					}
					else {
						newMapElement = new MapElement(BackgroundElement.ROUTE_SUD, null, TypeMobileElement.VIDE);
					}
				tabMapElement[i][j] = newMapElement;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	/* ACCESSEURS */
	/**
	 * @return the height
	 */
	public int getHauteurInTiles() {
		return nbLignes;
	}

	/**
	 * @param height the height to set
	 */
	/*	public void setHeight(int height) {
		this.height = height;
	}*/

	/**
	 * @return the width
	 */
	/*public int getWidth() {
		return width;
	}*/

	/**
	 * @param width the width to set
	 */
	/*public void setWidth(int width) {
		this.width = width;
	}*/

	/**
	 * @return the tabVehicule
	 */
	/*	public Vector<ElementMobiles> getTabVehicule() {
		return tabElementMobile;
	}*/

	/**
	 * @param tabVehicule the tabVehicule to set
	 */
	/*public void setTabVehicule(Vector<ElementMobiles> tabElementMobile) {
		this.tabElementMobile = tabElementMobile;
	}*/

	/**
	 * @return the tabMapElement
	 */
	/*public MapElement[][] getTabMapElement() {
		return tabMapElement;
	}*/

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


	
}