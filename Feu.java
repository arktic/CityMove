public abstract class Feu extends ElementFixe {

	
	
	protected EtatFeu etat;

	protected EtatFeu demande;

	protected Boolean carrefour;
	
	
	/**
	 * 
	 * @param etat: l'etat demande par le feu
	 */
	public Feu(EtatFeu etat) {
		super();

		this.etat = etat;
	}

	

	public void changerEtat(EtatFeu e) {
	}

}