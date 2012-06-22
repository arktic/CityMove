// non implemente
public class Pieton extends ElementMobiles {

	protected Boolean sexe;

	public Pieton(int posx, int posy,Direction dir) {
		super(posx,posy,dir);
	}


	@Override
	public boolean verifierDeplacement(Coordonnee coordonnee) {
		return false;
	}

}