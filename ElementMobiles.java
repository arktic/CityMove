import java.util.ArrayList;
import java.util.Random;


public abstract class ElementMobiles extends Elements {
	/*----- Attributs -----*/
	private Direction direction;
	/* Non utilise */
	private int vitesse;
	


	/**
	 * 
	 * @param posx la position en pixels en x
	 * @param posy la position en pixels en y
	 * @param dir la direction
	 */
	public ElementMobiles(int posx, int posy,Direction dir) {
		super(posx,posy);
		direction=dir;
	}



	/**
	 * fait se deplacer un elementmobile
	 * @return
	 */
	//TODO:synchronized ?
	synchronized public int seDeplacer() { 
		/* On retient notre direction courante */
		Direction directionPrise = getDirection();

		


		/* si on est sur le bord d'une tuile, il faut qu'on evalue nos possibilites de deplacement */
		if (getPosition().onEdgeofTile(directionPrise)) {

			/* Si on est sur la map, on determine notre nouvelle direction */
			if(CityMove.map.getPositionInTiles(getPosition()).isOnMap()) {
				directionPrise = choixDeplacement();
			}


			/* Notre nouvelle position, en pixel */
			Coordonnee newPixelPosition = getNextPosition(directionPrise);
			Coordonnee newPixelVerifPosition = getNextVerifPosition(directionPrise);
			/* Et celle en tuiles */
			Coordonnee newTilePosition = getNextTilesPosition(1, directionPrise);


			/* Si on est une voitureUrgente, on fait passer les feux devant nous a VERT */
			if (this instanceof VoitureUrgent) {
				VoitureUrgent vu = (VoitureUrgent) this;
				vu.intervenirFeu();
			}
			
			
			if(newTilePosition.isOnMap()) {
				/* Si on peut se rendre sur la tuile suivant */
				if(verifierDeplacement(newPixelVerifPosition)) {

					/* On recupere le mapElement de notre position actuelle */
					MapElement oldMapElement = CityMove.map.getMapElement(CityMove.map.getPositionInTiles(getPosition()));

					/* On actualise la position courante  et la nouvelle direction */
					setPosition(newPixelPosition);
					setDirection(directionPrise);
				}
				
			}
			/* Si on est pas sur la map, mais qu'on est sur le bord, il faut continuer a avancer dans la
			 * direction courante, afin d'avoir une voiture qui dispairait proprement et pas d'un coup au bord */
			else if(newTilePosition.isOnDarkMap()){

				/* Sortir visuellement le vehicule de la carte */

				MapElement oldMapElement = null;
				Coordonnee oldPosition = null;

				switch(directionPrise) {
				case NORD:
					oldPosition = new Coordonnee(getPosition().getX(),0);

					break;

				case SUD:
					oldPosition = new Coordonnee(getPosition().getX(),CityMove.map.getHauteur()-CityMove.map.sizeElement);
					break;

				case EST:
					oldPosition = new Coordonnee(0,getPosition().getY());

					break;
				case OUEST:
					oldPosition = new Coordonnee(CityMove.map.getLargeur()-CityMove.map.sizeElement,getPosition().getY());
					break;

				}

				setPosition(newPixelPosition);

			} else {
				/* Si on est ni sur la map, ni sur son contour, on est totalement sortie,
				 * on se supprime des elementsMobiles de la map */
				CityMove.map.removeElementMobile(this);
			}
		}
		/* si on est pas sur le bord, on continue dans notre direction actuelle */
		else {

			/* Notre nouvelle position, en pixel */
			Coordonnee newPixelPosition = getNextPosition(directionPrise);
			setPosition(newPixelPosition);
		}
		return 0;
	}





	/*----- ACCESSEURS ----*/
	synchronized public  Direction getDirection() {
		return direction;
	}



	synchronized public void setDirection(Direction direction) {
		this.direction = direction;
	}



	synchronized public  int getVitesse() {
		return vitesse;
	}



	synchronized public void setVitesse(int vit) {
		this.vitesse = vit;
	}









	public int stoper() {
		setVitesse(0);
		return 0;
	}

	/**
	 * 
	 * @param check si les coordonees passees sont accessible pour un element mobile
	 * @return Oui ou Non
	 */
	public abstract boolean verifierDeplacement(Coordonnee coordonnee);

	/**
	 * 
	 * @param pixelPosition : la coordonnee en pixel que l'on test
	 * @return si il y a un element de ce type devant
	 */
	public boolean verifierElementMobile( Coordonnee pixelPosition)
	{
		ElementMobiles aTester=null;
		boolean occupe=false;		
				
		int indice=0;
		while (!occupe && indice<CityMove.map.getSizeTabElementMobile()){
			aTester = CityMove.map.getTabElementMobileAt(indice);

			/* Il ne faut se prendre en compte soi meme! */
			if(this!=aTester) {
				occupe = aTester.contains(pixelPosition);
			}
			indice++;
		}

		return occupe;
	}

	
	/**
	 * Test si la pixelPosition est inclut dans la position occupes par un element mobile
	 * @param pixelPosition
	 * @return
	 */
	public boolean contains(Coordonnee pixelPosition) {
		Coordonnee tileaTester = CityMove.map.getPositionInTiles(pixelPosition);
		Coordonnee currentPositionTile = CityMove.map.getPositionInTiles(getPosition());
		Coordonnee nextPosition = CityMove.map.getPositionInTiles(getPosition()).getNextCoordonnee(getDirection());
		
		/* On test deux cases : celle du vehicule actuellement, et sa suivante, afin d'etre sur 
		 * de ne pas y aller si il est entrain lui aussi */
		return tileaTester.equals(currentPositionTile) 
				|| tileaTester.equals(nextPosition);
	}



	/**
	 * 
	 * @param coord : Direction dans laquelle on veut verifier la presence d'un feu
	 * @return Vrai ou faux
	 */
	public boolean verifierFeu(Coordonnee coord)
	{
		boolean isRouge = false;

		if(coord.isOnMap()) {
			MapElement monMapElementVerifie = CityMove.map.getMapElement(coord);
			Feu monFeuVerifie = monMapElementVerifie.getMyFeu();

			if(monFeuVerifie != null && monFeuVerifie.getEtat() == EtatFeu.ROUGE) {
				isRouge = true;
			}
		}

		return isRouge;
	}

	/**
	 * renvoie la position suivante en pixel du vehicule
	 * @param direction
	 * @return
	 */
	public Coordonnee getNextPosition(Direction direction) {
		/* On recupere notre position courante */
		Coordonnee maPosition = getPosition();

		/* On renvoie les cordonnees suivantes, qui dependent de notre position et de notre direction */
		switch (direction){
		case NORD :
			return new Coordonnee(maPosition.getX(), maPosition.getY()-1);
		case SUD :
			return new Coordonnee(maPosition.getX(), maPosition.getY()+1);
		case EST :
			return new Coordonnee(maPosition.getX()+1, maPosition.getY());
		case OUEST :
			return new Coordonnee(maPosition.getX()-1, maPosition.getY());
		default : // Si ce n'est aucun de ceux la, on renvoie la position courante (immobile)
			return new Coordonnee(maPosition.getX(), maPosition.getY());
		}
	}
	
	
	/**
	 * renvoie la prochaine case a tester : c'est celle une case aprs nous.
	 * @param direction
	 * @return
	 */
	public Coordonnee getNextVerifPosition(Direction direction) {
		/* On recupere notre position courante */
		Coordonnee maPosition = getPosition();

		/* On renvoie les cordonnees suivantes, qui dependent de notre position et de notre direction */
		switch (direction){
		case NORD :
			return new Coordonnee(maPosition.getX(), maPosition.getY()-1);
		case SUD :
			return new Coordonnee(maPosition.getX(), maPosition.getY()+1+CityMove.map.sizeElement);
		case EST :
			return new Coordonnee(maPosition.getX()+1+CityMove.map.sizeElement, maPosition.getY());
		case OUEST :
			return new Coordonnee(maPosition.getX()-1, maPosition.getY());
		default : // Si ce n'est aucun de ceux la, on renvoie la position courante (immobile)
			return new Coordonnee(maPosition.getX(), maPosition.getY());
		}
	}

	/**
	 * 
	 * @param nbTiles
	 * @param direction
	 * @return La case situŽ plusieurs cases en avance de la case actuelle
	 */
	public Coordonnee getNextTilesPosition(int nbTiles, Direction direction) {
		/* On recupere notre position courante, en tuiles, sur la map */
		Coordonnee tilesPosition = CityMove.map.getPositionInTiles(getPosition());
		
		/* On renvoie la tuile suivante, qui depend de notre position et de notre diretion */
		switch (direction){
		case NORD :
			return new Coordonnee(tilesPosition.getX(), tilesPosition.getY()-nbTiles);
		case SUD :
			return new Coordonnee(tilesPosition.getX(), tilesPosition.getY()+nbTiles);
		case EST :
			return new Coordonnee(tilesPosition.getX()+nbTiles, tilesPosition.getY());
		case OUEST :
			return new Coordonnee(tilesPosition.getX()-nbTiles, tilesPosition.getY());
		default : // Si ce n'est aucun de ceux la, on renvoie la position courante (immobile)
			return new Coordonnee(tilesPosition.getX(), tilesPosition.getY());
		}
	}

	/**
	 * Choisit aleatoirement une direction parmis celles qui lui sont proposees
	 * @return La direction choisie
	 */
	public Direction choixDeplacement() {
		Direction maDirection = Direction.AUCUNE;

		/* On recupere notre mapElement correspondant a notre ElementMobile */
		Coordonnee posInTiles = CityMove.map.getPositionInTiles(getPosition());
		MapElement monMapElement = CityMove.map.getMapElement(posInTiles);


		/* On recupere le tableau de possibilites de ce mapElement puis on stock la taille du tableau */
		ArrayList<Direction> tab = monMapElement.getPossibilities();
		int taille = tab.size();

		/* On genere un nommbre aleatoire compris entre 0 et la taile de mon tableau -> c'est la que ce fait le choix */
		Random generator = new Random();

		if(taille!=0){
			int choix = generator.nextInt(taille);
			maDirection = tab.get(choix);
		}
		else
			maDirection = Direction.AUCUNE;



		return maDirection;
	}

}