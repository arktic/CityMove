public class FeuHybride extends FeuTemps {

	public FeuHybride(EtatFeu etat) {
		super(etat);
		// TODO Auto-generated constructor stub
	}

	@Override 
	public void run() {
			// TODO Auto-generated method stub
		while(true) {
			if(getEtat() == EtatFeu.VERT) {
				try {
					this.wait(vert_time);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.setDemande(EtatFeu.ROUGE);
			}
			else {
					try {
						this.wait(red_time);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				this.setDemande(EtatFeu.VERT);
			}
			setBusy(true);
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // attente de la réponse du carrefour	
			setBusy(false);
		}		
	}
}