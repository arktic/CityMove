
public class Coordonnee {
	/*----- Attributs -----*/
	private int x;
	private int y;

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
	 * setteur
	 * @param Coordonnee x
	 * @param Coordonnee y
	 */
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * setter
	 * @param coord la coordonnee a recopier 
	 */
	public Coordonnee(Coordonnee coord) {
		this.x = coord.x;
		this.y = coord.y;
	}




	/*----- Constructeurs -----*/
	/**
	 * par default
	 */
	public Coordonnee() {
		this.x = 0;
		this.y = 0;
	}

	/** prenant les deux coordonnees separement
	 * @param Coordonne x
	 * @param Coordonne y
	 */
	public Coordonnee(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/*----- Operateurs -----*/
	/**
	 * Test l'egalite entre deux coordonnees
	 * @param Les coordonnees dont on veut tester l'egalite
	 * @return vrai ou faux
	 */
	public boolean equals(Coordonnee coordonnee) {

		return (x == coordonnee.getX() && y == coordonnee.getY());
	}

	/*----- Autres methodes -----*/

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
	 * Renvoie la coordonnee suivante, dependant de la direction dir
	 * @param dir la direction 
	 * @return la nouvelle coordonnee 
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


	/**
	 * reussit si la coordonnee appelante en tuile est visible sur la map
	 * @return
	 */
	public boolean isOnMap() {
		return ( x > -1 
				&& x < CityMove.map.getLargeurInTiles() 
				&& y > -1 
				&& y < CityMove.map.getHauteurInTiles()
				);
	}


	/**
	 * reussit si la coordonnee en tuiles appelant est dans une map plus grande d'une case que la map.
	 * Cela nous permet de continuer un deplacement une case en dehors de la map, pour avoir une sortie
	 * de la map non brutale (sinon la voiture disparaitrait au bord de la map)
	 * @return
	 */
	public boolean isOnDarkMap() {
		return ( x>-2 
				&& x<=CityMove.map.getLargeurInTiles() 
				&& y>-2 
				&& y<=CityMove.map.getHauteurInTiles()
				);
	}


	/**
	 * renvoie vrai si la coordonnee en pixels appelante est sur le bord d'une tuile
	 * @param d la direction, necessaire pour savoir si c'est l'abscisse ou l'ordonee qui nous interesse
	 * @return
	 */
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


	
	/**
	 * Renvoie vrai si la coordonnee en pixel appelante est incluse dans un des elements mobile de la map
	 * @return
	 */
	public boolean verifierElementMobile()
	{
		/* element que l'on va tester */
		ElementMobiles aTester=null;
		boolean occupe=false;		

		int indice=0;
		/* Tant qu'on a pas trouve un element mobile qui nous contient,
		 * et qu'on est pas a la fin du tableau, on continu  */
		while (!occupe && indice<CityMove.map.getSizeTabElementMobile()){
			/* On recupere l element qu'on va tester */
			aTester = CityMove.map.getTabElementMobileAt(indice);

			/* On test pour savoir si on est inclut dans l'element */
			occupe = aTester.contains(this);
			indice++;
		}

		return occupe;
	}






	/**
	 * @return La chaine de carracteres indiquant les coordonnees
	 */
	public String toString() {
		return "Coordonnee [x=" + x + ", y=" + y + "]";
	}


}