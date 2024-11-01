package no.nav.hovedsystem;
import java.util.HashMap;
import no.nav.dagpenger.DagpengerKalkulator;
import no.nav.person.Person;
import no.nav.sak.Sak;
import no.nav.saksbehandler.Saksbehandler;

import java.util.ArrayList;

public class Hovedsystem {
    HashMap<String, Person> personRegister;
    DagpengerKalkulator dagpengerKalkulator;    
    ArrayList<Sak> innvilgetDagpenger;
    ArrayList<Sak> innvilgetDagPengerMedMaks;
    ArrayList<Sak> avslagDagpenger; 
    public Hovedsystem() {
        personRegister = new HashMap<>();
        dagpengerKalkulator = new DagpengerKalkulator();
        innvilgetDagpenger = new ArrayList<>();
        innvilgetDagPengerMedMaks = new ArrayList<>();
        avslagDagpenger = new ArrayList<>();
    }

    public void registrerPerson(String navn, String id) {
        Person person = new Person(navn, id);
        personRegister.put(id, person);
    }

    public void leggTilÅrslønnForPerson(String id, int år, double lønn) {
        Person person = personRegister.get(id);
        person.leggTilÅrslønn(år, lønn);
    }

    public void opprettSak(String id) {
        Person person = personRegister.get(id); 
        double resultat = dagpengerKalkulator.kalkulerDagsats(person);
        System.out.println(resultat);
        System.out.println(dagpengerKalkulator.hentMaksDagssats());
        if (resultat == 0) {
            avslagDagpenger.add(new Sak(person, "Har ikke rett på dagpenger"));	
        }
        else if (resultat < dagpengerKalkulator.hentMaksDagssats()) {
            innvilgetDagpenger.add(new Sak(person, "Har rett på " +resultat+ " kr"));

        }
        else {
            innvilgetDagPengerMedMaks.add(new Sak(person, "Har rett på dagpenger med maks dagsats"));
        }
        
    }

    public Sak hentSak(Saksbehandler.SaksbehandlerSpesialisering spesialisering) {
        switch (spesialisering) {
            case AVSLAG:
                if (avslagDagpenger.isEmpty()) {
                    return null;
                }
                return avslagDagpenger.removeFirst();
            case INNVILGET:
                if (innvilgetDagpenger.isEmpty()) {
                    return null;
                }
                return innvilgetDagpenger.removeFirst();
            case INNVILGET_MAKS:
                if (innvilgetDagPengerMedMaks.isEmpty()) {
                    return null;
                }
                return innvilgetDagPengerMedMaks.removeFirst();
            default:
                return null;
        }
    }
    
    
}
