import java.lang.Object;

public class Coordonnee {

	public int x;

	public int y;
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	

	public void set(int x, int y) {
	//TODO
	}

	public Coordonnee(Coordonnee coord) {
		this.x = coord.x;
		this.y = coord.y;
	}
	
	
	
	/**
	 * @param x
	 * @param y
	 */
	public Coordonnee(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coordonnee() {
		this.x = 0;
		this.y = 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Coordonnee other = (Coordonnee) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Coordonnee [x=" + x + ", y=" + y + "]";
	}
	
	/**
	 * Aditionne this et coord
	 * @param coord
	 * @return
	 */
	public Coordonnee addition(Coordonnee coord) {
		Coordonnee ret= new Coordonnee(coord.x+this.x,coord.y+this.y);
		return ret;
	}
	
	/**
	 * Decale this.x de offset
	 * @param x
	 * @return
	 */
	public Coordonnee offsetX(int offset) {
		this.x+=offset;
		return this;
	}
	
	/**
	 * Decale this.y de offset
	 * @param offset
	 * @return
	 */
	public Coordonnee offsetY(int offset) {
		this.y+=offset;
		return this;
	}
	
	/**
	 * Indique si this est "atLeft" de c
	 * @param c
	 * @return:vrai ou faux
	 */
	public boolean atLeft(Coordonnee c) {
		return (c.x> this.x);
	}

	/**
	 * Indique si this est "atRight" de c
	 * @param c
	 * @return:vrai ou faux
	 */
	public boolean atRight(Coordonnee c) {
		return (c.x< this.x);
	}
	
	/**
	 * Indique si this est "onTop" de c
	 * @param c
	 * @return:vrai ou faux
	 */
	public boolean onTop(Coordonnee c){
		return (this.y < c.y);
	}
	
	/**
	 * Indique si this est "onBot" de c
	 * @param c
	 * @return:vrai ou faux
	 */
	public boolean onBot(Coordonnee c){
		return (this.y > c.y);
	}
	
	
	
	/**
	 * Renvoie la coordonnee après déplacement de 1 de la coordonnée appelante, suivant la direction
	 * @param dir la direction 
	 * @return la nouvelle coordonnée
	 */
	public Coordonnee getNextCoordonnee(Direction dir) {
		switch(dir){
		case NORD:
			return new Coordonnee(this.offsetY(-1));
		case SUD:
			return new Coordonnee(this.offsetY(1)); 
		case EST:
			return new Coordonnee(this.offsetX(1));
		case OUEST:
			return new Coordonnee(this.offsetX(-1));
		default:
			return new Coordonnee(this);
			
		}	
	}
	
  
}