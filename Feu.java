public abstract class Feu extends ElementFixe implements Observable {

  protected EtatFeu etat;

  protected EtatFeu demande;

  protected Boolean carrefour;

  public abstract void changerEtat(EtatFeu e);

}