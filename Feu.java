import javax.imageio.ImageIO;

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

	

	public void changerEtat(EtatFeu e) {
		
	}
	
	public void setDemande(EtatFeu newDemande) {
		if(carrefour == true) {
			demande = newDemande;
			setChanged();
			notifyObservers();
		}
	}

}