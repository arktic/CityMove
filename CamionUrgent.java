
public class CamionUrgent extends Voiture implements IUrgent {


	IUrgent urg;

	public CamionUrgent(int posx, int posy, Direction dir) {
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
	 * Permet de demande au feu present devant nous de passer au vert
	 * @return 0
	 */
	public int intervenirFeu() {

		int i=0;
		Feu feu = null;
		Coordonnee coordSuivantes;

		/* On parcours les nbTilesATester a la recherche d'un feu. Si on en trouve un, on lui demande de changer au vert */
		while(i<nbTilesATester) {

			coordSuivantes = getNextTilesPosition(i+1,getDirection());

			/* On recupere le feu present a cet endroit, ou null */
			if (coordSuivantes.isOnMap())
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