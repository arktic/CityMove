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
	public boolean verifierDeplacement(Coordonnee coord) {
		boolean deplacementVerifie = false;

		
		/* test si il y a un feu présent */
		boolean feuRouge = verifierFeu(coord);
		boolean vehiculePresent = verifierElementMobile(TypeMobileElement.VEHICULE, coord);
		boolean pietonPresent = verifierElementMobile(TypeMobileElement.PIETON, coord);
		
		
		if(!feuRouge && !vehiculePresent && !pietonPresent)
		{
			deplacementVerifie = true;
		}
		
		return deplacementVerifie;
	}

}