package sistem;

import java.awt.Color;

public class Hidroelektrana extends Proizvodjac implements Runnable{
	
	private int brVode = 0;
	private Thread thread;
	
	public Hidroelektrana(Baterija b) {
		super('H',Color.BLUE,1500,b);
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public synchronized boolean proizvodnjaEnergije() {
		if(brVode == 0)return false;
		else {
			vlasnikSistem.dohvBat().napuni(brVode);
			return true;
		}
	}
	public void azuriraj() {
		brVode=0;
		if(vlasnik.dohvParc(x+1, y) instanceof VodenaPovrs)brVode++;
		if(vlasnik.dohvParc(x-1, y) instanceof VodenaPovrs)brVode++;
		if(vlasnik.dohvParc(x, y-1) instanceof VodenaPovrs)brVode++;
		if(vlasnik.dohvParc(x, y+1) instanceof VodenaPovrs)brVode++;
		if(vlasnik.dohvParc(x+1, y-1) instanceof VodenaPovrs)brVode++;
		if(vlasnik.dohvParc(x+1, y+1) instanceof VodenaPovrs)brVode++;
		if(vlasnik.dohvParc(x-1, y-1) instanceof VodenaPovrs)brVode++;
		if(vlasnik.dohvParc(x-1, y+1) instanceof VodenaPovrs)brVode++;
	}
	
	
	@Override
	public void zaustavi() {thread.interrupt();}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
			
				Thread.sleep(ukupnoVreme());
				boolean uspeh = proizvodnjaEnergije();
				synchronized (vlasnikSistem) {
					vlasnikSistem.napunjenostBat.setText("Kapacitet baterije je: "
							+vlasnikSistem.dohvBat().dohvTrE()+"/"+vlasnikSistem.dohvBat().dohvKap());
					vlasnikSistem.napunjenostBat.revalidate();
				}
				Color prev = getForeground();
				if(uspeh) {
					setForeground(Color.RED);
				}
				Thread.sleep(300);
				setForeground(prev);
				
			} 
		}catch (InterruptedException e) {setForeground(Color.WHITE);}
	}
}
