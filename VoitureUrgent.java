public class VoitureUrgent extends Voiture implements IUrgent {

	
	IUrgent urg;
	
	public VoitureUrgent() {
		super();
		urg = new Urgent();
	}
	
	
	@Override
	public int allumerGirophare() {
		urg.allumerGirophare();
		return 0;
	}

	@Override
	public int stoperGirophare() {
		urg.stoperGirophare();
		return 0;
	}

	@Override
	public int intervenirFeu(Feu feu) {
		urg.intervenirFeu(feu);
		return 0;
	}
}