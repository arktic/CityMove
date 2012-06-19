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
	 * La taille en pixel d'un element (carr�) de type MapElement
	 */
	protected int sizeElement;
	
	private static int nbDefaultLigne = 15;
	private static int nbDefaultColonne = 15;

	
	
	
	
	/**
	 * Cr�� une map pr�d�fini
	 * sizeElem : la taille en pixel des tuiles qui la compose
	 */
	public Map(int sizeElem) {
		
		backgroundImage = new HashMap<>();
		this.nbColonnes = nbDefaultColonne;
		this.nbLignes = nbDefaultLigne;
		this.tabElementMobile = new Vector<ElementMobiles>();
		
		this.tabMapElement = new MapElement[nbLignes][nbColonnes];
		
		
		/* On ajoute notre base de donn�e d'images de Background � notre image */
		ajouterBackgroundImages();
		
		/* On remplit la map avec de l'herbe */
		remplirDefaultMap();

		
		CityMove.map=this;
		this.sizeElement = sizeElem;
		
		setFocusable(true);
		setDoubleBuffered(true);
	}
	
	
	
	
	
	
	
	
	
	
	
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
		
		
		/* On ajoute notre base de donn�e d'images de Background � notre image */
		ajouterBackgroundImages();
		
		/* On remplit la map avec de l'herbe */
		remplirDefaultMap();
		
		CityMove.map=this;
		this.sizeElement = sizeElem;
		
		setFocusable(true);
		setDoubleBuffered(true);
	}

	
	/**
	 * Ajoute toutes les images de background � notre HashMap
	 */
	private void ajouterBackgroundImages() {
		Image img;
		
		
		try {
			img = ImageIO.read(new File("./Ressources/Background/herbe.jpg"));
			backgroundImage.put(BackgroundElement.HERBE, img);
			
			img = ImageIO.read(new File("./Ressources/Background/route_nord.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_NORD, img);
			
			img = ImageIO.read(new File("./Ressources/Background/route_sud.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_SUD, img);
			
			img = ImageIO.read(new File("./Ressources/Background/route_est.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_EST, img);
			
			img = ImageIO.read(new File("./Ressources/Background/route_ouest.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_OUEST, img);
			
			img = ImageIO.read(new File("./Ressources/Background/route_nord_est.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_NORD_EST, img);
			
			img = ImageIO.read(new File("./Ressources/Background/route_nord_ouest.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_NORD_OUEST, img);
			
			img = ImageIO.read(new File("./Ressources/Background/route_sud_est.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_SUD_EST, img);
			
			img = ImageIO.read(new File("./Ressources/Background/route_sud_ouest.jpg"));
			backgroundImage.put(BackgroundElement.ROUTE_SUD_OUEST, img);
			
			
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

		g2d.drawImage(  backgroundImage.get(BackgroundElement.ROUTE_NORD), X1, Y1, this);	
		
		/*Toolkit.getDefaultToolkit().sync();
		g.dispose();
		*/
	}

	
	/**
	 * Remplit la map avec de l'herbe
	 */
	public void remplirDefaultMap () {
		/* On remplit notre nouvelle map de cases vides */
		for(int l=0; l<nbLignes; l++) {
			for(int c=0; c<nbColonnes; c++) {
				MapElement newMapElement;
					
				newMapElement = new MapElement(BackgroundElement.HERBE, null, TypeMobileElement.VIDE);
				tabMapElement[l][c] = newMapElement;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	/* ACCESSEURS */
	/**
	 * @return le nombre de tuiles en hauteur de la map
	 */
	public int getHauteurInTiles() {
		return nbLignes;
	}

	/**
	 * @return le nombre de tuiles en largeur de la map
	 */
	public int getLargeurInTiles() {
		return nbColonnes;
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
	 * @param x la position sur les x de l'�l�ments que l'on veut obtenir
	 * @param y la position sur les y de l'�l�ments que l'on veut obtenir
	 * @return
	 */
	public MapElement getMapElement(int x,int y) {
		return tabMapElement[x][y];
	}
	
	
	/**
	 * @param tilesCoord coordonn�es en  Tuiles dont on veut r�cup�rer le MapElement
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