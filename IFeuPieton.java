public interface IFeuPieton extends Runnable {

  public void changerEtat(EtatFeu e);
  public void run();
}