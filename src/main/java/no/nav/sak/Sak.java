package no.nav.sak;

import no.nav.person.Person;

public class Sak {

    public enum Status {
        UNDER_BEHANDLING, 
        GODKJENT, 
        AVSLÅTT, 
    }
    private static int antSaker = 0;
    private Person person;
    private String resultatAvKalkulasjon;
    private int id; 
    private Status status;
    public Sak(Person person, String resultatAvKalkulasjon) {
        this.id = antSaker++;
        this.person = person;
        this.resultatAvKalkulasjon = resultatAvKalkulasjon;
        this.status = Status.UNDER_BEHANDLING;
    }

    public void hentInformasjon() {
        System.out.println("Saksnummer: " + id +"\n" 
                            +person.toString()+"\n"
                            +"Resultat av kalkulasjon: "+ resultatAvKalkulasjon);
    }

    public void godkjennSak() {
        this.status = Status.GODKJENT; 
    }

    public void avslåSak() {
        this.status = Status.AVSLÅTT; 
    }

    public Status hentStatus() {
        return status;
    }

}
