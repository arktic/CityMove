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

	public int intervenirFeu() {

		return 0;
	}

}