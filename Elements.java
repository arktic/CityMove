import java.util.Observable;

public class Elements extends Observable {
/*----- Attributs -----*/
	private String nom;
	private Coordonnee position;
	private String pathImg;
	
/*----- Constructeurs -----*/
	public Elements() {
		nom = "No name";
		position = new Coordonnee();
		pathImg = "No image";
	}
	
	public Elements(int posx,int posy) {
		position = new Coordonnee(posx,posy);
	}

/*----- Accesseurs -----*/
	/**
	 * @return Le nom de l'objet appelant
	 */
	synchronized public String getNom() {
		return this.nom;
	}
	
	/**
	 * @param Le nom a donner a l'objet
	 */
	synchronized public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * @return La position de l'objet appelant
	 */
	synchronized public Coordonnee getPosition() {
		return this.position;
	}
	
	/**
	 * @param La position a donner a l'objet
	 */
	synchronized public void setPosition(Coordonnee position) {
		this.position = position;
	}

	/**
	 * @return Le chemin d'acces a limage de l'objet appelant
	 */
	synchronized public String getPathImg() {
		return this.pathImg;
	}
	
	/**
	 * @param Le chemin d'acces a l'image que l'on veut attribuer a l'objet
	 */
	synchronized public void setPathImg(String pathImg) {
		this.pathImg = pathImg;
	}
	
/*----- Autres methodes -----*/
	public int Placer() {
		return 0;
	}

	public int Afficher() {
		return 0;
	}

}