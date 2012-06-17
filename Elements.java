import java.util.Observable;

public class Elements extends Observable {
/*----- Attributs -----*/
	protected String nom;
	protected Coordonnee position;
	protected String pathImg;
	
/*----- Constructeurs -----*/
	public Elements() {
		nom = "No name";
		position = new Coordonnee();
		pathImg = "No image";
	}

/*----- Accesseurs -----*/
	/**
	 * @return Le nom de l'objet appelant
	 */
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * @param Le nom a donner a l'objet
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * @return La position de l'objet appelant
	 */
	public Coordonnee getPosition() {
		return this.position;
	}
	
	/**
	 * @param La position a donner a l'objet
	 */
	public void setPosition(Coordonnee position) {
		this.position = position;
	}

	/**
	 * @return Le chemin d'acces a limage de l'objet appelant
	 */
	public String getPathImg() {
		return this.pathImg;
	}
	
	/**
	 * @param Le chemin d'acces a l'image que l'on veut attribuer a l'objet
	 */
	public void setPathImg(String pathImg) {
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