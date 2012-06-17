public class Urgent implements IUrgent {

	protected Boolean etatGirophare;

	public int allumerGirophare() {
		etatGirophare=true;
		return 0;
	}

	public int stoperGirophare() {
		//System.out.println("TEST APPELER STOPPERGIROPGHARE DS URGENT");
		etatGirophare=false;
		return 0;
	}

	
	/**
	 * 
	 * @param feu: feu sur lequel on doit intervenir
	 * @return
	 */
	public int intervenirFeu(Feu feu) {
		
		/* On dit au feu que l'on veut qu'il passe au VERT */
		if(feu!=null) {
			if(feu.etat!=EtatFeu.VERT) {
				feu.setDemande(EtatFeu.VERT);
			}
			
		}
		
		return 0;
	}


}