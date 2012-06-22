
public class Elements {
/*----- Attributs -----*/
	private Coordonnee position;
	
	
	
/*----- Constructeurs -----*/
	public Elements() {
		position = new Coordonnee();
	}
	
	public Elements(int posx,int posy) {
		position = new Coordonnee(posx,posy);
	}

	
	
/*----- Accesseurs -----*/
	
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

}