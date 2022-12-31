package sistem;

import java.awt.*;
import java.awt.event.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Scanner;

public class EnergetskiSistem extends Frame {
	
	private int redovi;
	private int kolone;
	private int kapacitetBaterije;
	private Baterija  baterija;
	
	private ArrayList<Hidroelektrana> hidroelektrane = new ArrayList<>();
	private ArrayList<Mlin> mlinovi = new ArrayList<>();
	
	private Panel gornjiPanel = new Panel();
	private Button dodaj = new Button("Dodaj");
	
	private Panel donjiLeviPanel = new Panel();
	Label napunjenostBat = new Label("Kapacitet bat: ");
	
	
	private Panel donjiDesniPanel = new Panel();
	private CheckboxGroup grupa = new CheckboxGroup();
	private Checkbox hidro = new Checkbox("HidroElektrana",true,grupa);
	private Checkbox mlin = new Checkbox("Mlin",false,grupa);
	private Label izaberi = new Label("Izaberi: ");
	private Panel donjiPanel = new Panel();
	
	private MenuBar menibar = new MenuBar();
	private Menu akcija = new Menu("Akcija");
	private MenuItem ugasi = new MenuItem("Ugasi");
	
	
	public EnergetskiSistem(int x,int y,int k) {
		
		redovi = x;
		kolone = y;
		kapacitetBaterije = k;
		
		
		popuni(kapacitetBaterije);

		setBounds(500,300,500,500);
		setTitle("Energetski sistem");
		setResizable(false);
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				for(int i = 0;i < hidroelektrane.size();i++) {
					hidroelektrane.get(i).zaustavi();
				}
				for(int i = 0;i < mlinovi.size();i++) {
					mlinovi.get(i).kraj();
				}
				dispose();
			}
		});
		setVisible(true);
	}
	private void popuni(int kap) {
		Plac plac = new Plac(redovi, kolone);
		baterija = new Baterija(kap);
		//ovo je moje
		baterija.isprazni();
		
		gornjiPanel.add(dodaj);
		add(gornjiPanel,BorderLayout.NORTH);
		

		dodaj.addActionListener((ae)->{
			if(plac.kliknuto==true) {
				int i = plac.prethodna.indeks;
				plac.parcele.remove(plac.prethodna);
				plac.remove(plac.prethodna);
				//ovde ako uklanjam hidro ili mlin mora da ugasim nit;
					if(grupa.getSelectedCheckbox() == hidro) {
						Hidroelektrana H = new Hidroelektrana(baterija);
						
						H.indeks = i;
						H.x = plac.prethodna.x;
						H.y = plac.prethodna.y;
						H.vlasnik = plac;
						H.vlasnikSistem = this;
						
						plac.add(H, i);
						hidroelektrane.add(H);
					}
					else if(grupa.getSelectedCheckbox() == mlin) {
						Mlin M = new Mlin(baterija);
						
						M.indeks = i;
						M.x  = plac.prethodna.x;
						M.y = plac.prethodna.y;
						M.vlasnik = plac;
						M.vlasnikSistem = this;
						
						plac.add(M,i);
						mlinovi.add(M);
						
				}
				for(Hidroelektrana h:hidroelektrane) {
					h.azuriraj();
				}
				for(Mlin m:mlinovi) {
					m.azuriraj();
				}
				
				plac.revalidate();
				plac.kliknuto=false;
			}
		});
		
		add(plac);
		
		//
		donjiLeviPanel.add(napunjenostBat,BorderLayout.WEST);
		
		donjiPanel.setLayout(new GridLayout(1,2));
		donjiPanel.add(donjiLeviPanel);
		
		
		
		donjiDesniPanel.setLayout(new GridLayout(3,1));
		donjiDesniPanel.add(izaberi);
		donjiDesniPanel.add(hidro);
		donjiDesniPanel.add(mlin);
		donjiDesniPanel.setBackground(Color.GRAY);
		donjiPanel.add(donjiDesniPanel);
		
		add(donjiPanel,BorderLayout.SOUTH);
		
		akcija.add(ugasi);
		ugasi.setShortcut(new MenuShortcut(KeyEvent.VK_E));
		menibar.add(akcija);
		setMenuBar(menibar);
		
		ugasi.addActionListener((ae)->{
			for(int i = 0;i < hidroelektrane.size();i++) {
				hidroelektrane.get(i).zaustavi();
			}
			for(int i = 0;i < mlinovi.size();i++) {
				mlinovi.get(i).kraj();
			}
		});
		
	}
	public Baterija dohvBat() {return baterija;}
	public static void main(String[] args) {
		new EnergetskiSistem(10,10,100);
	}
}