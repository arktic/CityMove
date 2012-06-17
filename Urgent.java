public class Urgent implements IUrgent {

	protected Boolean etatGirophare;

	public int allumerGirophare() {
		etatGirophare=true;
		return 0;
	}

	public int stoperGirophare() {
		etatGirophare=false;
		return 0;
	}

	
	/**
	 * 
	 * @param feu Le feu sur lequel intervenir
	 * @return
	 */
	public int intervenirFeu(Feu feu) {
		
		if(feu!=null) {
			feu.demande = EtatFeu.VERT;
		}
		
		return 0;
	}


}