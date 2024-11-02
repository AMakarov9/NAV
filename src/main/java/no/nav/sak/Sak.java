package no.nav.sak;

import no.nav.person.Person;

/**
 * Sak som skal bli behandlet av en saksbehandler. 
 * Lar saksbehandler hente ut informasjon om saken (person og resultat av kalkulasjon).
 * Saksbehandler kan enten godkjenne eller avslå saken.
 * 
 * @author Alexander Gran Makarov
 * @version 1.0
 */
public class Sak {

    public enum Status {
        UNDER_BEHANDLING, 
        GODKJENT, 
        AVSLÅTT, 
    }
    private static int antSaker = 1;
    private final Person person;
    private final int id; 
    private final String resultatAvKalkulasjon;
    private Status status;
    private String saksbehandler; 

    public Sak(Person person, String resultatAvKalkulasjon) {
        this.id = antSaker++;
        this.person = person;
        this.resultatAvKalkulasjon = resultatAvKalkulasjon;
        this.status = Status.UNDER_BEHANDLING;
    }

    /**
     * Skriver ut informasjon om personen og resultatet av kalkulasjonen.
     */
    public void hentInformasjon() {
        System.out.println("Saksnummer: " + id +"\n" 
                            +person.toString()+"\n"
                            +"Resultat av kalkulasjon: "+ resultatAvKalkulasjon+"\n");
    }

    /**
     * Godkjenner saken. 
     */
    public void godkjennSak(String navn) {
        this.saksbehandler = navn;
        this.status = Status.GODKJENT; 
    }

    /**
     * Avslår saken. 
     */
    public void avslåSak(String navn) {
        this.saksbehandler = navn;
        this.status = Status.AVSLÅTT; 
    }

    /**
     * Henter status på saken. 
     * @return status på saken. 
     */
    public Status hentStatus() {
        return status;
    }

    /**
     * Henter id på saken. 
     * @return id på saken. 
     */
    public int hentId() {
        return id;
    }

    /**
     * Henter saksbehandler som har behandlet saken. 
     * @return navn på saksbehandler. 
     */
    public String hentSaksbehandler() {
        if (saksbehandler == null) {
            return "Ikke behandlet";
        }
        return saksbehandler;
    }

}
