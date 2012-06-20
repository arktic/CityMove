

public class Pieton extends ElementMobiles {

	protected Boolean sexe;



	public void demandeFeu(Feu feu) {
		if((feu.getClass().equals(FeuPieton.class) || feu.getClass().equals(FeuHybride.class)) && feu.getBusy() == false) {
			feu.notify();
		}
	}


	@Override
	public boolean verifierDeplacement(Direction d) {
		boolean deplacementVerifie = true;

		Coordonnee coordonneeVerifiee = getNextTilesPosition(1, d);
		MapElement monMapElementVerifie = CityMove.map.getMapElement(coordonneeVerifiee);

		if(monMapElementVerifie.isRoutePieton())
		{
			Coordonnee maCoordonnee = CityMove.map.getPositionInTiles(getPosition());
			MapElement monMapElement = CityMove.map.getMapElement(maCoordonnee);
			Feu feuVerifie = monMapElement.getMyFeu();

			if(feuVerifie != null) {
				EtatFeu etatFeuVerifie = feuVerifie.getEtat();

				if(etatFeuVerifie == EtatFeu.VERT) {
					deplacementVerifie = false;
				}
			}
		}

		return deplacementVerifie;
	}

}