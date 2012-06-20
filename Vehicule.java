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
	public boolean verifierDeplacement(Direction d) {
		boolean deplacementVerifie = false;
		
		boolean feuRouge = true;
		boolean vehiculePresent = true;
		boolean pietonPresent = true;
		
		feuRouge = verifierFeu(d);
		vehiculePresent = verifierElementMobile(TypeMobileElement.VEHICULE, d);
		pietonPresent = verifierElementMobile(TypeMobileElement.PIETON, d);
		
		if(!feuRouge && !vehiculePresent && !pietonPresent)
		{
			deplacementVerifie = true;
		}
		
		return deplacementVerifie;
	}

}