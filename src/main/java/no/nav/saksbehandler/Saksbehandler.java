package no.nav.saksbehandler;
import no.nav.hovedsystem.Hovedsystem;
import java.util.ArrayList;
import no.nav.sak.Sak;
import java.util.Scanner; 
public class Saksbehandler {
    
    public enum SaksbehandlerSpesialisering {
        AVSLAG, 
        INNVILGET, 
        INNVILGET_MAKS
    }


    String navn; 
    ArrayList<Sak> saker;
    SaksbehandlerSpesialisering spesialisering;
    Hovedsystem hovedsystem;
    public Saksbehandler(String navn, SaksbehandlerSpesialisering spesialisering, Hovedsystem hovedsystem) {
        this.navn = navn;
        this.spesialisering = spesialisering;
        this.hovedsystem = hovedsystem;
        this.saker = new ArrayList<>();
    }
  

    public void hentTilgjengeligSak() {
        Sak sak = hovedsystem.hentSak(this.spesialisering);
        if (sak == null) {
            System.out.println("Kaffepause");
        }
        else {
            saker.add(sak);
        }
    };
    
    public void avgjørSak() {
        if (saker.isEmpty()) {
            System.out.println("Ispause pa taket");
        }
        else {
            Sak sak = saker.remove(0);
            Scanner sc = new Scanner(System.in);
            sak.hentInformasjon();
            System.out.println("Godkjenn sak? (j/n)");
            String avgjørelse = sc.nextLine();
            if (avgjørelse.equals("j")) {
                sak.godkjennSak();
            }
            else {
                sak.avslåSak();
            }
        }

    };
}
