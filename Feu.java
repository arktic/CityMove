
public abstract class Feu extends ElementFixe  {

	
	
	protected EtatFeu etat;
	protected EtatFeu demande;
	protected boolean busy;
	
	
	/**
	 * 
	 * @param etat: l'etat demande par le feu
	 */
	public Feu(EtatFeu etat) {
		super();
		busy = false; // indique si le feu est en attente de réponse du carrefour
		this.etat = etat;
		this.demande = EtatFeu.ROUGE;
		
	}

	synchronized public EtatFeu getEtat() {
		return etat;
	}
	
	synchronized public boolean getBusy() {
		return busy;
	}
	
	synchronized public void setEtat(EtatFeu e) {
		etat = e;
	}
	
	synchronized public void setBusy(boolean b) {
		busy = b;
	}

//	public void changerEtat(EtatFeu e) {}
	
	public void setDemande(EtatFeu newDemande) {
			demande = newDemande;
			setChanged();
			notifyObservers();
	}

}