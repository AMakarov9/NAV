package no.nav.person;
import java.util.HashMap; 


/**
 * Klasse som representerer en person med navn, id og lønnsinformasjon. 
 * En person kan ha flere årslønner, og det er mulig å hente ut informasjon om disse.  
 * 
 * @author Alexander Gran Makarov
 * @version 1.0
 */
public class Person {

    private final String navn; 
    private final String id; 
    private HashMap<Integer, Double> lønnsOversikt; 
    private double sumÅrslønner; 
    private int nyesteÅr = 0;  


    // En konstruktør uten argumenter for enklere testing. 
    public Person() {
        this.navn = "Alexander";
        this.id = "0"; 
        this.lønnsOversikt =  new HashMap<Integer, Double>();
    }
    public Person(String navn, String id) {
        this.navn = navn; 
        this.id = id;  
        this.lønnsOversikt =  new HashMap<Integer, Double>();
    }

    
    /**
     * Registrerer nytt kalenderår og lønn.
     * @param åretForLønn kalenderår
     * @param årslønn årslønn for gitt år
     */
    public void leggTilÅrslønn(int åretForLønn, double årslønn) {
        this.sumÅrslønner+=årslønn;

        if (lønnsOversikt.containsKey(åretForLønn)) {
            sumÅrslønner+=Math.abs(årslønn-lønnsOversikt.get(åretForLønn)); 
            lønnsOversikt.replace(åretForLønn, årslønn); 
        }
        else {
            if (åretForLønn > this.nyesteÅr) {
                this.nyesteÅr = åretForLønn; 
            }
            this.lønnsOversikt.put(åretForLønn, årslønn); 
    
        }
    }
    /**
     * Henter årslønn for gitt år. 
     * @return årslønn
     */
    public double hentÅrslønnForÅr(int årstall) {
        return lønnsOversikt.get(årstall); 
    }

    /**
     * Returnerer summen av alle lønningene
     * @return summen av lønningene
     */
    public double hentSumAvLønninger() {
        // Ser at det aldri blir lagt til mer enn tre årslønninger, men legger likevel inn denne. 
        // Antar at det ikke vil bli lagt inn mindre heller. 
        // Hvis vi skal begynne å lagre flere lønninger vil dette fungere. 
        if (lønnsOversikt.size() > 3) {
            double sumAvTreSiste = lønnsOversikt.get(nyesteÅr) + lønnsOversikt.get(nyesteÅr-1) + lønnsOversikt.get(nyesteÅr-2);  
            return sumAvTreSiste; 
        }
        return sumÅrslønner; 
    }

    /**
     * Returnerer gjennomsnitt av de tre siste lønningene
     * @return Gjennomsnittelig lønn
     */
    public double hentGjennomSnittAvLønninger() {
        if (lønnsOversikt.size() > 3) {
            double sumAvTreSiste = lønnsOversikt.get(nyesteÅr) + lønnsOversikt.get(nyesteÅr-1) + lønnsOversikt.get(nyesteÅr-2);  
            return sumAvTreSiste/3; 
        }
        return sumÅrslønner/3;
    }

    /**
     * Henter nyeste årslønn. 
     * @return nyeste årslønn.
     */
    public double hentNyesteÅrslønn() {
        return lønnsOversikt.get(nyesteÅr); 
    }

    @Override 
    public String toString() {
        return "Person: " + navn + " med id: " + id +"\n"
                + "LoennsOversikt: " + lønnsOversikt.toString(); 
    }
}


