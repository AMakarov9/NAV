package no.nav.årslønn;
import java.util.HashMap;

/**
 * Representeren en person sin lønn et kalenderår.
 * Holder på informasjon som hvilket år lønnen tilhører, og selve lønnen det kalenderåret.
 *
 * @author Emil Elton Nilsen
 * @version 1.0
 */


 /**
  * Representerer all lønnsinformasjon for en person.
  * Holder informasjon om lønn for hvert kalenderår som har blitt gitt.  
  * 
  */
public class Årslønn {

    private HashMap<Integer, Double> år; 
    private double sumÅrslønner; 
    private int nyesteÅr = 0;  
    public Årslønn() {
        this.år =  new HashMap<Integer, Double>();
    }
    /**
     * Registrerer nytt kalenderår og lønn.
     * @param åretForLønn kalenderår
     * @param årslønn årslønn for gitt år
     */


    
    public void settInnÅrslønn(int åretForLønn, double årslønn) {
        this.sumÅrslønner+=årslønn;

        if (åretForLønn > this.nyesteÅr) {
            this.nyesteÅr = åretForLønn; 
        }
        this.år.put(åretForLønn, årslønn); 
    }

    /**
     * Henter årslønn for gitt år. 
     * @return årslønn
     */
    public double hentÅrslønnForÅr(int årstall) {
        return år.get(årstall); 
    }

    /**
     * Returnerer summen av alle lønningene
     * @return summen av lønningene
     */
    public double hentSumAvLønninger() {
        // Ser at det aldri blir lagt til mer enn tre årslønninger, men legger likevel inn denne. 
        // Hvis vi skal begynne å lagre flere lønninger vil dette fungere. 
        if (år.size() > 3) {
            double sumAvTreSiste = år.get(nyesteÅr) + år.get(nyesteÅr-1) + år.get(nyesteÅr-2);  
            return sumAvTreSiste; 
        }
        return sumÅrslønner; 
    }

    /**
     * Returnerer gjennomsnitt av de tre siste lønningene
     * @return Gjennomsnittelig lønn
     */
    public double hentGjennomSnittAvLønninger() {
        if (år.size() > 3) {
            double sumAvTreSiste = år.get(nyesteÅr) + år.get(nyesteÅr-1) + år.get(nyesteÅr-2);  
            return sumAvTreSiste/3; 
        }
        return sumÅrslønner/3;
    }

    /**
     * Henter nyeste årslønn. 
     * @return nyeste årslønn.
     */
    public double hentNyesteÅrslønn() {
        return år.get(nyesteÅr); 
    }

}
