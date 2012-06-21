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
		
		/* Booléen permettant de tester le déplacement */
		
		/* test si il y a un feu présent */
		boolean feuRouge = verifierFeu(d);
		boolean vehiculePresent = verifierElementMobile(TypeMobileElement.VEHICULE, d);
		boolean pietonPresent = verifierElementMobile(TypeMobileElement.PIETON, d);
		
	
		//System.out.println("feurouge = "+feuRouge+" vehiculepresent = "+vehiculePresent+" pietonpresent : "+pietonPresent);
		if(!feuRouge && !vehiculePresent && !pietonPresent)
		{
			deplacementVerifie = true;
		}
		
		return deplacementVerifie;
	}

}