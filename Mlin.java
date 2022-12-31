package sistem;

import java.awt.Color;
import java.util.Random;

public class Mlin extends Potrosac implements Runnable {
	
	private Thread thread;
	private int Ovreme = 1000;
	private int brTrave = 0;
	
	public Mlin(Baterija bat) {
		super('M', Color.ORANGE, bat);
		thread = new Thread(this);
		thread.start();
	}
	public void azuriraj() {
		brTrave=0;
		if(vlasnik.dohvParc(x+1, y) instanceof TravnataPovrs)brTrave++;
		if(vlasnik.dohvParc(x-1, y) instanceof TravnataPovrs)brTrave++;
		if(vlasnik.dohvParc(x, y-1) instanceof TravnataPovrs)brTrave++;
		if(vlasnik.dohvParc(x, y+1) instanceof TravnataPovrs)brTrave++;
		if(vlasnik.dohvParc(x+1, y-1) instanceof TravnataPovrs)brTrave++;
		if(vlasnik.dohvParc(x+1, y+1) instanceof TravnataPovrs)brTrave++;
		if(vlasnik.dohvParc(x-1, y-1) instanceof TravnataPovrs)brTrave++;
		if(vlasnik.dohvParc(x-1, y+1) instanceof TravnataPovrs)brTrave++;
	}
	
	@Override
	public synchronized int potrosnja() {
		int vr = new Random().nextInt(1000);
		baterija.napuni(-brTrave*3);
		if(baterija.dohvTrE() < 0) baterija.isprazni();
		if(brTrave == 0) return 0;
		return Ovreme + vr + brTrave*500;

	}
	public void kraj() {thread.interrupt();}
	
	@Override
	public void run() {
		try {
			while(!Thread.interrupted()) {
				Color prev = getForeground();
				if(baterija.puna()) {
					if(brTrave != 0) {
						Thread.sleep(1000);
						setForeground(Color.MAGENTA);
						Thread.sleep(potrosnja());
						synchronized (vlasnikSistem) {
							vlasnikSistem.napunjenostBat.setText("Kapacitet baterije je: "
									+vlasnikSistem.dohvBat().dohvTrE()+"/"+vlasnikSistem.dohvBat().dohvKap());
							vlasnikSistem.napunjenostBat.revalidate();
						}
					}
				}
				setForeground(prev);
			}
		}catch(InterruptedException e) {setForeground(Color.WHITE);}
		
	}

}
