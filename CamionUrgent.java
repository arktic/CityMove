public class CamionUrgent extends Camion implements IUrgent {
/*----- Attributs -----*/
	IUrgent camionUrgent;

/*----- Constructeurs -----*/
	public CamionUrgent() {
		super();
		camionUrgent = new Urgent();
	}
	
/*----- Autres methodes -----*/
	/**
	 * Allume le Giropahare,
	 * Fait appel a la fonction de la classe abstraite Urgent
	 */
	public int allumerGirophare() {
		camionUrgent.allumerGirophare();
		
		return 0;
	}

	/**
	 * Eteint le girophare,
	 * Fait appel a la fonction de la calsse abstraite Urgent
	 */
	public int stoperGirophare() {
		camionUrgent.stoperGirophare();
		
		return 0;
	}

	/**
	 * Permet de demande au feu présents devant nous de passer au vert
	 * @return
	 */
	public int intervenirFeu() {

		int i=0;
		Feu feu = null;
		Coordonnee coordSuivantes;

		/* On parcours les nbTilesATester à la recherche d'un feu. Si on en trouve un, on lui demande de changer au vert */
		while(i<nbTilesATester) {

			coordSuivantes = getNextTilesPosition(i+1,direction);

			/* On récupère le feu présent à cet endroit, ou null */
			feu = CityMove.map.getMapElement(coordSuivantes).getMyFeu();


			intervenirFeu(feu);
			i++;
		}
		return 0;
	}


	
	 /** Fait une demande pour le passage au vert du feu desire,
	 * Fait appel a la fonction de la classe abstraite Urgent
	 * @param Le feu dont on veut qu'il passe au vert
	 */
@Override
	public int intervenirFeu(Feu feu) {
		camionUrgent.intervenirFeu(feu);
		
		return 0;
	}
}