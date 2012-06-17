/** 
 *  Classe abstraite,
 *  permet de regrouper les vehicules
 */
public abstract class Vehicule extends ElementMobiles 
{
	@Override
	/* Pas besoin */
	public abstract int verifierDeplacement(Direction d);
}