public abstract class Feu extends ElementFixe {

  protected EtatFeu etat;

  protected EtatFeu demande;

  protected Boolean carrefour;

  public abstract void changerEtat(EtatFeu e);

}