
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
	//TODO

		this.x = x;
		this.y = y;
	}
	public Coordonnee(Coordonnee coord) {
		this.x = coord.x;
		this.y = coord.y;
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
		
		return (x == coordonnee.getX() && y == coordonnee.getY());
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

	public boolean isOnMap() {
		//System.out.println("CiyMove collonne: " + CityMove.map.nbColonnes);
		//System.out.println("lignes: " + CityMove.map.nbLignes);
		return ( x > -1 
				&& x < CityMove.map.getLargeurInTiles() 
				&& y > -1 
				&& y < CityMove.map.getHauteurInTiles()
				);
	}

	public boolean isOnDarkMap() {
		/*return (  posInTiles.getX()*map.sizeElement>= -1//map.sizeElement
				&& posInTiles.getX()*map.sizeElement< map.getLargeurInTiles()-2//*map.sizeElement 
				&& posInTiles.getY()*map.sizeElement>= -1//map.sizeElement
				&& posInTiles.getY()*map.sizeElement< map.getHauteurInTiles()-2); */
		return ( x>-2 
				&& x<=CityMove.map.getLargeurInTiles() 
				&& y>-2 
				&& y<=CityMove.map.getHauteurInTiles()
				);
	}

	public boolean onEdgeofTile(Direction d) {
		switch(d) {
		case NORD:
			return (getY())%CityMove.map.sizeElement==0;
		case SUD:
			return (getY())%CityMove.map.sizeElement==0;
		case EST:
			return (getX())%CityMove.map.sizeElement==0;
		case OUEST:
			return (getX())%CityMove.map.sizeElement==0;
		default:
			return true;
		
		}
		
	
	}
	
	
	public boolean verifierElementMobile()
	{
		ElementMobiles aTester=null;
		boolean occupe=false;		
				
		int indice=0;
		while (!occupe && indice<CityMove.map.getSizeTabElementMobile()){
			aTester = CityMove.map.getTabElementMobileAt(indice);

			
				occupe = aTester.contains(this);
				//System.out.println("indice :"+indice+" occupe ="+occupe);
			
			indice++;
		}

		return occupe;
	}
	

}