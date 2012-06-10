import java.util.Vector;

public class Map {

	protected int height;

	protected int width;

	protected Vector<Vehicule> tabVehicule;

<<<<<<< HEAD
	/**
	 * le tableau de bloc qui compose la map
	 */
	protected MapElement tabMapElement[][];
=======
	protected MapElement tabMapElement[];
>>>>>>> 7c94697c68e4045781f7094144aa7a4b0bfdcd2f

	/**
	 * La taille en pixel d'un element de type MapElement
	 */
	protected int sizeElement;

<<<<<<< HEAD


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
		this.tabMapElement = new MapElement[width][height];
		this.sizeElement = sizeElem;
	}
=======
	public int Afficher() {
		return 0;
	}

	public int addVehicule(Vehicule monVehicule) {
		return 0;
	}

  public int removeVehicule(Vehicule monVehicule) {
	  	return 0;
  }
>>>>>>> 7c94697c68e4045781f7094144aa7a4b0bfdcd2f



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

	/**
	 * @param sizeElement the sizeElement to set
	 */
	public void setSizeElement(int sizeElement) {
		this.sizeElement = sizeElement;
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

	public int Afficher() {
		return 0;
	}
}