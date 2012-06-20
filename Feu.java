
public abstract class Feu extends ElementFixe  {

	
	
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
		this.demande = EtatFeu.ROUGE;
		this.carrefour = false;
		
	}

	public EtatFeu getMyEtat() {
		return etat;
	}

//	public void changerEtat(EtatFeu e) {}
	
	public void setDemande(EtatFeu newDemande) {
		//TODO : non nécessaire, un feu est tjrs rajouter à un carrefour.
		if(carrefour == true) {
			demande = newDemande;
			setChanged();
			/* On informe notre carrefour que l'on souhaite obtenir cette couleur */
			notifyObservers(demande);
		}
	}

}