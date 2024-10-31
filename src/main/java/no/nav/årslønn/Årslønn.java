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

    public void settInnÅrslønn(int åretForLønn, double årslønn) {
        this.sumÅrslønner+=årslønn;

        if (åretForLønn > this.nyesteÅr) {
            this.nyesteÅr = åretForLønn; 
        }
        this.år.put(åretForLønn, årslønn); 
    }

    public double hentÅrslønnForÅr(int årstall) {
        return år.get(årstall); 
    }

    /**
     * Returnerer summen av alle lønningene
     * @return summen av lønningene
     */
    public double hentSumAvLønninger() {
        return sumÅrslønner; 
    }

    public double hentNyesteÅrslønn() {
        return år.get(nyesteÅr); 
    }

}
