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
	 * Fait une demande pour le passage au vert du feu desire,
	 * Fait appel a la fonction de la classe abstraite Urgent
	 * @param Le feu dont on veut qu'il passe au vert
	 */
	public int intervenirFeu(Feu feu) {
		camionUrgent.intervenirFeu(feu);
		
		return 0;
	}
}