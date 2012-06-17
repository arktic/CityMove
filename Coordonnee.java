import java.lang.Object;

public class Coordonnee {
/*----- Attributs -----*/
	public int x;
	public int y;

/*----- Accesseurs -----*/
	/**
	 * @return Coordonnee x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * @return Coordonnee y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * 
	 * @param Coordonnee x
	 * @param Coordonnee y
	 */
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

/*----- Constructeurs -----*/
	public Coordonnee() {
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * @param Coordonne x
	 * @param Coordonne y
	 */
	public Coordonnee(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
/*----- Operateurs -----*/
	/**
	 * Test l'egalite entre deux objets
	 * @param Les coordonnees avec lesquels on veut tester l'egalite
	 * @return vrai ou faux
	 */
	public boolean equals(Coordonnee coordonnee) {
		boolean test = false;
		
		if(this.getX() == coordonnee.getX() && this.getY() == coordonnee.getY()) {
			test = true;
		}
		
		return test;
	}

/*----- Autres methodes -----*/
	/**
	 * @return La chaine de carracteres indiquant les coordonnees
	 */
	public String toString() {
		return "Coordonnee [x=" + x + ", y=" + y + "]";
	}
	
	/**
	 * Aditionne this et coord
	 * @param Coordonnees a ajouter a this
	 * @return Les nouvelles coordonnees
	 */
	public Coordonnee addition(Coordonnee coord) {
		Coordonnee ret = new Coordonnee(coord.x+this.x, coord.y+this.y);
		
		return ret;
	}
	
	/**
	 * Decale this.x de offset
	 * @param L'offset
	 * @return this
	 */
	public Coordonnee offsetX(int offset) {
		this.x += offset;
		
		return this;
	}
	
	/**
	 * Decale this.y de offset
	 * @param L'offset
	 * @return
	 */
	public Coordonnee offsetY(int offset) {
		this.y += offset;
		
		return this;
	}
	
	/**
	 * Indique si this est "atLeft" de c
	 * @param Coordonnee a tester
	 * @return:vrai ou faux
	 */
	public boolean atLeft(Coordonnee c) {
		return (c.x > this.x);
	}

	/**
	 * Indique si this est "atRight" de c
	 * @param Coordonnees a tester
	 * @return:vrai ou faux
	 */
	public boolean atRight(Coordonnee c) {
		return (c.x < this.x);
	}
	
	/**
	 * Indique si this est "onTop" de c
	 * @param Coordonnee a tester
	 * @return:vrai ou faux
	 */
	public boolean onTop(Coordonnee c){
		return (this.y < c.y);
	}
	
	/**
	 * Indique si this est "onBot" de c
	 * @param Coordonee a tester
	 * @return:vrai ou faux
	 */
	public boolean onBot(Coordonnee c){
		return (this.y > c.y);
	}
}