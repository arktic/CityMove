public class Urgent implements IUrgent {

	protected Boolean etatGirophare;

	@Override
	public int allumerGirophare() {
		etatGirophare=true;
		return 0;
	}

	@Override
	public int stoperGirophare() {
		etatGirophare=false;
		return 0;
	}

	
	/**
	 * 
	 * @param feu: feu sur lequel on doit intervenir
	 * @return
	 */
	@Override
	public int intervenirFeu(Feu feu) {
		
		/* On dit au feu que l'on veut qu'il passe au VERT */
		if(feu!=null) {
			//System.out.println("ENVOIE DEMANDE A FEU: "+feu);
			feu.setEtatAndNotify(EtatFeu.VERT);		
		}
		return 0;
	}




}