package no.nav.hovedsystem;
import java.util.ArrayList;
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
 * Systemet holder også oversikt over antall ubehandlede og behandlede saker.
 * 
 * @author Alexander Gran Makarov
 * @version 1.0
 */
public class Hovedsystem {
    private HashMap<String, Person> personRegister;
    private DagpengerKalkulator dagpengerKalkulator; 
    private HashMap<SaksbehandlerSpesialisering, LinkedList<Sak>> ubehandledeSaker;
    private int AntallUbehandledeSaker;
    private ArrayList<Sak> behandledeSaker;
    public Hovedsystem() {
        this.personRegister = new HashMap<>();
        this.dagpengerKalkulator = new DagpengerKalkulator();
        this.ubehandledeSaker = new HashMap<>();
        for (SaksbehandlerSpesialisering spesialisering : SaksbehandlerSpesialisering.values()) {
            ubehandledeSaker.put(spesialisering, new LinkedList<Sak>());
        }
        this.behandledeSaker = new ArrayList<>();
    }

    /**
     * Registrerer en person i systemet.
     * @param navn til personen
     * @param id til personen (feks fødselsnummer)
     */
    public void registrerPerson(String navn, String id) {
        if (personRegister.containsKey(id)) {
            System.out.println("Personen er allerede registrert");
        }
        else {
            Person person = new Person(navn, id);
            personRegister.put(id, person);
        }
    }

    /**
     * Legger til en årslønn for en person.
     * @param id til personen
     * @param år kalenderår
     * @param lønn årslønn
     */
    public void leggTilÅrslønnForPerson(String id, int år, double lønn) {
        if (!personRegister.containsKey(id)) {
            System.out.println("Personen er ikke registrert");
        }
        else {
            Person person = personRegister.get(id);
            person.leggTilÅrslønn(år, lønn);
        }
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
            ubehandledeSaker.get(SaksbehandlerSpesialisering.AVSLAG).add(new Sak(person, "Har ikke rett paa dagpenger"));	
        }
        else if (resultat < dagpengerKalkulator.hentMaksDagssats()) {
            ubehandledeSaker.get(SaksbehandlerSpesialisering.INNVILGET).add(new Sak(person, "Har rett paa " +resultat+ " kr"));

        }
        else {
            ubehandledeSaker.get(SaksbehandlerSpesialisering.INNVILGET_MAKS).add(new Sak(person, "Har rett paa dagpenger med maks dagsats"));
        }

        AntallUbehandledeSaker++;
    }

    /**
     * Henter en sak basert på saksbehandlerens spesialisering.
     * @param spesialisering til saksbehandleren
     * @return sak som samsvarer med saksbehandlerens spesialisering
     */
    public Sak hentSak(Saksbehandler.SaksbehandlerSpesialisering spesialisering) {
        if (ubehandledeSaker.get(spesialisering).isEmpty()) {
            return null;
        }
        AntallUbehandledeSaker--;
        return ubehandledeSaker.get(spesialisering).removeFirst();
    }

    /**
     * Legger til en sak i listen over behandlede saker.
     * @param sak som skal legges til
     */
    public void leggTilBehandletSak(Sak sak) {
        if (sak.hentStatus() == Sak.Status.UNDER_BEHANDLING) {
            System.out.println("Sak har ikke blitt behandlet");
        }
        else {
            behandledeSaker.add(sak);
        }  
    }


    /**
     * Henter antall personer i systemet.
     * @return antall personer
     */
    public int hentAntallPersoner() {
        return personRegister.size();
    }

    /**
     * Henter antall saker som er ubehandlet.
     * @return antall ubehandlede saker
     */
    public int hentAntallUbehandledeSaker() {
        return AntallUbehandledeSaker;
    }

    /**
     * Henter antall behandlede saker.
     * @return antall behandlede saker
     */
    public int hentAntallBehandledeSaker() {
        return behandledeSaker.size();
    }
    
}
