package sistem;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Random;

public class Plac extends Panel {
	
	private int redovi;
	private int kolone;
	
	ArrayList<Parcela> parcele = new ArrayList<>();
	boolean kliknuto = false;
	Parcela prethodna;
	
	public Plac(int x, int y) {
		redovi = x;
		kolone = y;
		int brRed = 0;
		int brKol = 0;
		setLayout(new GridLayout(redovi,kolone,4,4));
		for(int i = 0;i < redovi*kolone;i++) {
			int br = new Random().nextInt(100);
			TravnataPovrs tp = new TravnataPovrs();
			VodenaPovrs vp = new VodenaPovrs();
			if(br<70) {
				tp.indeks = i;
				if(brKol % kolone == 0 && brKol != 0) {
					brRed++;
					brKol=0;
				}
				tp.y = brKol++;
				tp.x = brRed;
				
				add(tp);
				parcele.add(tp);
				tp.vlasnik = this;
			}
			else {
				vp.indeks = i;
				if(brKol % kolone == 0 && brKol != 0) {
					brRed++;
					brKol=0;
				}
				vp.y = brKol++;
				vp.x = brRed;
				
				add(vp);
				parcele.add(vp);
				vp.vlasnik = this;
			}		
		}
	}
	
	public void kliknuto(Parcela p) {
			if(prethodna == p) {
				p.setFont(prethodna.font);
				prethodna = null;
				kliknuto = false;
			}else {
				if(prethodna != null) {
					prethodna.setFont(prethodna.font);
				}
				prethodna = p;
				p.setFont(new Font("Serif",Font.BOLD,20));	
				kliknuto = true;
			}
	}
	public Parcela dohvParc(int red, int kol) {
		for(Parcela p:parcele) {
			if(p.x == red & p.y == kol) {
				return p;
			}
		}
		return null;
	}
}
