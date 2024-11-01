package no.nav.hovedsystem;
import java.util.HashMap;
import no.nav.dagpenger.DagpengerKalkulator;
import no.nav.person.Person;
import no.nav.sak.Sak;
import no.nav.saksbehandler.Saksbehandler;
import no.nav.saksbehandler.Saksbehandler.SaksbehandlerSpesialisering;

import java.util.LinkedList;

/**
 * Hovedsystem som håndterer registrering av personer, beregning av dagpenger og opprettelse av saker.
 * Saksbehandlere kan interagere med systemet og hente ut saker som skal behandles.
 *     Dersom en saksbehandler etterspør en sak, vil systemet returnere en sak basert på saksbehandlerens spesialisering.
 *     Dersom det ikke finnes flere saker som samsvarer med saksbehandlerens spesialisering, vil systemet returnere null.
 */
public class Hovedsystem {
    HashMap<String, Person> personRegister;
    DagpengerKalkulator dagpengerKalkulator; 

    HashMap<SaksbehandlerSpesialisering, LinkedList<Sak>> saker;
    public Hovedsystem() {
        this.personRegister = new HashMap<>();
        this.dagpengerKalkulator = new DagpengerKalkulator();
        this.saker = new HashMap<>();
        for (SaksbehandlerSpesialisering spesialisering : SaksbehandlerSpesialisering.values()) {
            saker.put(spesialisering, new LinkedList<Sak>());
        }
    }

    /**
     * Registrerer en person i systemet.
     * @param navn til personen
     * @param id til personen (feks fødselsnummer)
     */
    public void registrerPerson(String navn, String id) {
        Person person = new Person(navn, id);
        personRegister.put(id, person);
    }

    /**
     * Legger til en årslønn for en person.
     * @param id til personen
     * @param år kalenderår
     * @param lønn årslønn
     */
    public void leggTilÅrslønnForPerson(String id, int år, double lønn) {
        Person person = personRegister.get(id);
        person.leggTilÅrslønn(år, lønn);
    }

    /**
     * Oppretter en sak for en person basert på personens id.
     * Saken blir lagt inn i register avhengig av resultatet av kalkulasjonen
     * @param id til personen
     */
    public void opprettSak(String id) {
        Person person = personRegister.get(id); 
        double resultat = dagpengerKalkulator.kalkulerDagsats(person);
        if (resultat == 0) {
            saker.get(SaksbehandlerSpesialisering.AVSLAG).add(new Sak(person, "Har ikke rett på dagpenger"));	
        }
        else if (resultat < dagpengerKalkulator.hentMaksDagssats()) {
            saker.get(SaksbehandlerSpesialisering.INNVILGET).add(new Sak(person, "Har rett på " +resultat+ " kr"));

        }
        else {
            saker.get(SaksbehandlerSpesialisering.INNVILGET_MAKS).add(new Sak(person, "Har rett på dagpenger med maks dagsats"));
        }
        
    }

    /**
     * Henter en sak basert på saksbehandlerens spesialisering.
     * @param spesialisering til saksbehandleren
     * @return sak som samsvarer med saksbehandlerens spesialisering
     */
    public Sak hentSak(Saksbehandler.SaksbehandlerSpesialisering spesialisering) {
        if (saker.get(spesialisering).isEmpty()) {
            return null;
        }
        return saker.get(spesialisering).removeFirst();
    }
    
    
}
