public abstract class Feu extends ElementFixe  {

	
	
	protected EtatFeu etat;

	private EtatFeu demande;

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
	
	public void setDemande(EtatFeu newDemande) {
		demande = newDemande;
		notifyObservers();
	}

}