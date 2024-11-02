package no.nav.saksbehandler;
import no.nav.hovedsystem.Hovedsystem;
import java.util.ArrayList;
import no.nav.sak.Sak;
import java.util.Scanner; 

/**
 * Saksbehandler som kan hente og avgjøre saker gjennom hovedsystemet.
 * Saksbehandleren kan hente en sak basert på spesialisering, og avgjøre saken basert på input.
 * 
 * @author Alexander Gran Makarov 
 * @version 1.0
 */
public class Saksbehandler {
    
    /**
     * Spesialiseringer som saksbehandleren kan ha.
     */
    public enum SaksbehandlerSpesialisering {
        AVSLAG, 
        INNVILGET, 
        INNVILGET_MAKS
    }
    private String navn; 
    private ArrayList<Sak> saker;
    private final SaksbehandlerSpesialisering spesialisering;
    private Hovedsystem hovedsystem;
    public Saksbehandler(String navn, SaksbehandlerSpesialisering spesialisering, Hovedsystem hovedsystem) {
        this.navn = navn;
        this.spesialisering = spesialisering;
        this.hovedsystem = hovedsystem;
        this.saker = new ArrayList<>();
    }
  
    /**
     * Henter en tilgjengelig sak basert på saksbehandlerens spesialisering.
     * @return sak som ble hentet
     */
    public Sak hentTilgjengeligSak() {

        System.out.println(this.navn+" proever aa hente en sak "+"\n");
        Sak sak = hovedsystem.hentSak(this.spesialisering);
        
        // Printer informasjon for demoen. 
        if (sak == null) {
            System.out.println("Ingen tilgjengelige saker, "+this.navn+" tar kaffepause"+"\n");
            return null; 
        }
        else { 
            System.out.println("Sak med Id: "+sak.hentId()+" hentet av saksbehandler "+this.navn+"\n");
            saker.add(sak);
            return sak; 
        }
    };
    
    
    /**
     * Avgjør en sak som saksbehandleren har hentet basert på input.
     * @param sc Scanner som skal lese input
     * @return sak som ble avgjort
     */
    public Sak avgjørSak(Scanner sc) {
        
        // Printer informasjon for demoen.
        System.out.println(this.navn+" skal avgjoere en sak");
        if (saker.isEmpty()) {
            System.out.println(this.navn + "er ferdig med alle hentede saker, tar ispause pa taket");
            return null; 
        }
        else {
            Sak sak = saker.removeFirst();
            // Scanner sc = new Scanner(System.in);
            System.out.println("Saksinformasjon: \n");
            sak.hentInformasjon();
            System.out.println("Vil du godkjenne saken? j for ja, n for nei");
            
            if(sc.hasNextLine()) {
                String avgjørelse = sc.nextLine();

                while (!avgjørelse.equals("j") && !avgjørelse.equals("n")) {
                    System.out.println("Ugyldig input, skriv j for ja, n for nei");
                    avgjørelse = sc.nextLine();
                }
                if (avgjørelse.equals("j")) {
                    System.out.println("Sak "+sak.hentId()+" godkjent av saksbehandler "+this.navn);
                    sak.godkjennSak(this.navn);
                    hovedsystem.leggTilBehandletSak(sak);
                }
                else {
                    System.out.println("Sak "+sak.hentId()+" avslaatt av saksbehandler "+this.navn);
                    sak.avslåSak(this.navn);
                    hovedsystem.leggTilBehandletSak(sak);
                }
                // sc.close();
                return sak;
            } else {
                // sc.close();
                System.out.println("Input ble ikke gitt");;
            }
            return null;
        }
    }

    /**
     * Henter antall saker som saksbehandleren har hentet (mest for testing).
     * @return antall saker
     */
    public int hentAntallHentedeSaker() {
        return saker.size();
    }
}
