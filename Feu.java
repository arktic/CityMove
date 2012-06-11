public abstract class Feu extends ElementFixe {

  /**
   * 
   * @param etat: l'etat demande par le feu
   */
	public Feu(EtatFeu etat) {
		super();
		
		this.etat = etat;
	}

protected EtatFeu etat;

  protected EtatFeu demande;

  protected Boolean carrefour;

  public void changerEtat(EtatFeu e) {
}

}