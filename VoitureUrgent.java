public class VoitureUrgent extends Voiture implements IUrgent {


	IUrgent urg;

	public VoitureUrgent(int posx, int posy, Direction dir) {
		super(posx,posy,dir);
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
	 * Permet de demande au feu pr�sents devant nous de passer au vert
	 * @return
	 */
	public int intervenirFeu() {

		int i=0;
		Feu feu = null;
		Coordonnee coordSuivantes;

		/* On parcours les nbTilesATester � la recherche d'un feu. Si on en trouve un, on lui demande de changer au vert */
		while(i<nbTilesATester) {

			coordSuivantes = getNextTilesPosition(i+1,getDirection());

			/* On r�cup�re le feu pr�sent � cet endroit, ou null */
			feu = CityMove.map.getMapElement(coordSuivantes).getMyFeu();


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