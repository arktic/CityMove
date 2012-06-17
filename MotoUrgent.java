public class MotoUrgent extends Moto implements IUrgent {

	IUrgent urg;

	

	public MotoUrgent() {
		super();
		urg = new Urgent();
	}


	@Override
	public int allumerGirophare() {
		urg.allumerGirophare();
		return 0;
	}

	@Override
	public int stoperGirophare() {
		urg.stoperGirophare();
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
			
			/* Et on lui demande de passer au vert */
			intervenirFeu(feu);
			i++;
		}



		return 0;
	}


	@Override
	public int intervenirFeu(Feu feu) {

		urg.intervenirFeu(feu);


		return 0;
	}
}