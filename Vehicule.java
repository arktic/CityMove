/** 
 *  Classe abstraite,
 *  permet de regrouper les vehicules
 */

abstract class Vehicule extends ElementMobiles 
{
	
	public Vehicule(int posx,int posy,Direction dir) {
		super(posx,posy,dir);
	}
	
	
	
	/**
	 * @param d : Direction pour laquelle on veut verifier que le deplacement est possible
	 * @return Oui ou non
	 */
	public boolean verifierDeplacement(Coordonnee pixelCoord) {
		boolean deplacementVerifie = false;		
		/* test si il y a un feu present */
		boolean feuRouge = verifierFeu(CityMove.map.getPositionInTiles(pixelCoord));
		boolean mobileElementPresent = verifierElementMobile(pixelCoord);
		
		if(!feuRouge && !mobileElementPresent)
		{
			deplacementVerifie = true;
		}
		
		return deplacementVerifie;
	}

}