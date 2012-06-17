import java.util.Vector;

public class Map {

	protected int height;

	protected int width;

	protected Vector<Vehicule> tabVehicule;

	/**
	 * le tableau de bloc qui compose la map
	 */
	protected MapElement tabMapElement[][];

	/**
	 * La taille en pixel d'un element (carré) de type MapElement
	 */
	protected int sizeElement;


	/**
	 * @param height
	 * @param width
	 * @param sizeElem
	 */
	public Map(int height, int width, int sizeElem) {
		super();
		this.height = height;
		this.width = width;
		this.tabVehicule = new Vector<Vehicule>();
		//A remplir ac du vide, herbe ?
		this.tabMapElement = new MapElement[width][height];
		
		/* On remplit notre nouvelle map de cases vides */
		for(int i=0; i<width; i++) {
			for(int j=0; j<height; j++) {
			//	tabMapElement[i][j].set(BackgroundElement.HERBE, null, TypeMobileElement.VIDE);
			}
		}
		this.sizeElement = sizeElem;
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
	public Vector<Vehicule> getTabVehicule() {
		return tabVehicule;
	}

	/**
	 * @param tabVehicule the tabVehicule to set
	 */
	public void setTabVehicule(Vector<Vehicule> tabVehicule) {
		this.tabVehicule = tabVehicule;
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



	public int addVehicule(Vehicule monVehicule) {
		tabVehicule.add(monVehicule);
		return 0;
	}

	public int removeVehicule(Vehicule monVehicule) {
		tabVehicule.remove(monVehicule);
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

	public int Afficher() {
		
		return 0;
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